package NeuralNetwork;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;

public class GA {
	private ArrayList<Network> population;
	private ArrayList<Specie> species = new ArrayList<Specie>();
	private static int globalInnovationNumber = 0;//latest innovation.
	private String name;
	private int maxPop;
	private double globalChampion = 0;
	private int championCounter =0;
	
	private boolean printAllGenerations = true;
	private int generation = 0;
	private double generationAverageFitness=0;
	
	private Fitness fitnessFuncion = new noFitness();
	private int inputs = 0;

	//constants
	public static final double deathRatio =0.5;
	public static final double weightRange = 5;
	static final double c1 = 1;//value of excess for distance
	static final double c2 = 1;//value of disjoint for distance
	static final double c3 = 0.6*(1/weightRange);//value of average weight difference
	static final double Dt = 1;// max distance from the representator of a specie, to enter in that specie.
	static final double perturbanceRange = 0.05/weightRange;//range of a perturbance mutation of a weight;
	//Probability
	static final double pDisabledP = 0.65;//probability that a gene is disabled, if one of the parent has it disabled
	static final double pDisable = 0;//probability that a gene is randomly disabled
	static final double weightMutation = 0.8;//probability that a offspring has his genome weights mutated
	static final double weightPerturbed = 0.9;//in case the offspring has his genome weights mutated, this is the probability that it will just be perturbed, and not randomly replaced.
	static final double newConnection = 0.2;//probability of mutating a new, random, connection.
	static final double newNeuron = 0.05;//probability of mutatinc a new, random, neuron.
	
	public GA(String name, int popSize, int inputLayers, int outputLayers){
		inputs=inputLayers+1;
		population = new ArrayList<Network>();
		this.name = name;
		maxPop = popSize;
		for(int i = 0;i<popSize;i++){
			population.add(new Network(inputLayers, outputLayers));
		}
		if(popSize>0)
			globalInnovationNumber = population.get(0).getGenome().size();
		setUpDirectory();
		Specie.setGA(this);
	}
	private void setUpDirectory() {
		new File("./src/NeuralNetwork/NNFiles/"+name).mkdir();
		new File("./src/NeuralNetwork/NNFiles/"+name+"/champions").mkdir();
		
	}
	public ArrayList<Network> getPopulation() {
		return population;
	}
	public void evolve(double target){
		//evolve untill reached a targer
		while(globalChampion<target){
			evolve();
			if(generation >=10000){
				System.exit(0);
			}
			
		}
	}
	public void evolve(){
		//put everybody in the right specie.
		sortSpecies();
		generationAverageFitness = 0;

		for(Specie s:species){
			calculateFitness(s);
			generationAverageFitness += s.getSummedFitness();
		}
		generationAverageFitness= generationAverageFitness / maxPop;

		//print  generation
		printGlobalChampion();
		if(printAllGenerations){
			new File("./src/NeuralNetwork/NNFiles/"+name+"/Generation"+generation).mkdir();
			printAll();
		}
		
		System.out.println("generation "+generation);
		System.out.println("Population: "+population.size());
		System.out.println("Average Fitness "+generationAverageFitness);
		System.out.println("Best fitness "+globalChampion);
		System.out.print("\n\n\n");
		//--------------------------------------------------
		
		//evolve next generation------------------------------
		generation++;
		population = new ArrayList<Network>();
		//Natural Selection
		double totalFitness=0;
		for(Specie s:species){
			s.survive(deathRatio);
			s.calculateSpecieFitness();
			
			totalFitness += s.getSharedFitness();
		}
		
		
		
		//make each spece reproduce accordingly.
		for(Specie s:species){
			//calculate ofspring size for every specie
			s.setOffspringSize((int)Math.round(maxPop*(s.getSharedFitness()/totalFitness)));
			s.reproduce();
			population.addAll(s.getPop());
		}
		
	}
	private void printAll() {
		for(Specie s :species){
			s.write(generation, name);
		}
		
	}
	private void printGlobalChampion() {
		new File("./src/NeuralNetwork/NNFiles/"+name+"/champions").mkdirs();
		Network genChampion = null;
		for(Network n:population){
				if(genChampion == null|| n.getFitness()>(genChampion.getFitness())){
					genChampion = n;
				}
			}
		if(genChampion.getFitness() > globalChampion){
			globalChampion = genChampion.getFitness();
			genChampion.write("/"+name+"/champions"+"/champion"+championCounter);
			championCounter++;
			}
		}
	private void calculateFitness(Specie specie) {
		//calculate fitness and adjustedFitness, and shared fitness of the whole specie, and the champion
		for(Network n:specie.getPop()){
			n.setFitness(fitnessFuncion.getFitness(n));
			n.adjustFitness();
			if(specie.getChampion() == null || specie.getChampion().getFitness()<n.getFitness()){
				specie.setChampion(n);
			}
		}
		specie.calculateSpecieFitness();
	}
	public void sortSpecies(){
		ArrayList<Network> rep = new ArrayList<Network>();
		for (Specie s:species){//go trough pre-existing species
			if(s.size()>0){
				Random r = new Random();
				int temp = r.nextInt(s.size());
				rep.add(s.get(temp));
				s.clear();
			}
		}
		//sort population
		outerloop:
		for(Network n:population){
			for(int i=0;i<rep.size();i++){
				if(distance(n,rep.get(i))<=Dt){
					species.get(i).add(n);
					n.setSpecie(species.get(i));
					continue outerloop;
				}
			}
			rep.add(n);
			Specie temp = new Specie();
			temp.setOffspringSize(maxPop);
			species.add(temp);
			temp.add(n);
			n.setSpecie(temp);
		}
		//check if any specie is empty
		ArrayList<Specie> toRemove = new ArrayList<Specie>();
		for(Specie s : species){
			if(s.getPop().size()<=0){
				toRemove.add(s);
			}
		}
		species.removeAll(toRemove);
	}
	
	public static double distance (Network n1, Network n2){
		
		ArrayList<Gene> l1 = new ArrayList<Gene>();
		ArrayList<Gene> l2 = new ArrayList<Gene>();
		for(Gene g:n1.getGenome()){//this 2 loops create 2 lists, l1 and l2, containings genes, having their innovation number as index/
			if(l1.size()<g.getInnovation()){//increase l1, if l1 is too small
				int increaseOf = g.getInnovation()-l1.size();
				for(int i=0;i<=increaseOf;i++){
					l1.add(null);
				}
			}
			l1.set(g.getInnovation()-1, g);
		}for(Gene g:n2.getGenome()){//this 2 loops create 2 lists, l1 and l2, containings genes, having their innovation number as index/
			if(l2.size()<g.getInnovation()){
				int increaseOf = g.getInnovation()-l2.size();
				for(int i=0;i<=increaseOf;i++){
					l2.add(null);
				}
			}
			l2.set(g.getInnovation()-1, g);
		}
		double w =0;
		int matching =0;
		int excess =0;
		int disjoint =0;
		for(int i = 0;i < Math.min(l1.size(), l2.size());i++){
			if(l1.get(i)!= l2.get(i) && (l1.get(i) == null || l2.get(i) == null)){
				disjoint++;
			}else if(l1.get(i)!= null && l2.get(i)!= null){
				matching++;
				w += Math.abs(l1.get(i).getWeight() - l2.get(i).getWeight());
			}
		}
		if(matching >0)
			w = w/matching;
		else
			w = Integer.MAX_VALUE;
		for(int i = Math.min(l1.size(), l2.size()); i<Math.max(l1.size(), l2.size());i++ ){
			if(l1.size()>l2.size()){
				if(l1.get(i)!= null)
					disjoint++;
			}else{
				if(l2.get(i)!= null)
					disjoint++;
			}
		}
		int N = Math.min(n1.getGenome().size(),n2.getGenome().size());
		//N=1;
		//System.out.println("Distance: "+((c1*excess/N)+(c2*disjoint/N)+(c3*w))+" Weight: "+w+" E: "+excess+" D: "+disjoint+" matching: "+matching);
		return (c1*excess/N)+(c2*disjoint/N)+(c3*w);
	}
	public Network crossOver(Network parent1,Network parent2){
		if(parent1==null || parent2==null)
			System.out.println("Parent is null");
		
		ArrayList<Gene> l1 = new ArrayList<Gene>();
		ArrayList<Gene> l2 = new ArrayList<Gene>();
		for(Gene g:parent1.getGenome()){//this 2 loops create 2 lists, l1 and l2, containings genes, having their innovation number as index/
			if(l1.size()<=g.getInnovation()){//increase l1, if l1 is too small
				int increaseOf = g.getInnovation()-l1.size();
				for(int i=0;i<=increaseOf;i++){
					l1.add(null);
				}
			}
			l1.set(g.getInnovation()-1, g);
		}for(Gene g:parent2.getGenome()){//this 2 loops create 2 lists, l1 and l2, containings genes, having their innovation number as index/
			if(l2.size()<=g.getInnovation()){
				int increaseOf = g.getInnovation()-l2.size();
				for(int i=0;i<=increaseOf;i++){
					l2.add(null);
				}
			}
			l2.set(g.getInnovation()-1, g);
		}
		
		
		/*cross over*/
		ArrayList<Gene> genome = new ArrayList<Gene>();
		Random r = new Random();
		for(int i=0;i<Math.min(l1.size(), l2.size());i++){//going trough all the genes
			if(l1.get(i)!=null || l2.get(i)!= null){//case 3 filter (dont run if none of the parents has gene with innovation number i).
				
				//case 1: both parent has this gene
				if(l1.get(i)!=null && l2.get(i)!=null){
					if(r.nextBoolean())
						genome.add(l1.get(i));
					else
						genome.add(l2.get(i));
					}
				//case 2: only one parent has this gene
				//case 2.1: only parent1 has this gene
				else if(l1.get(i)!=null){
						if(parent1.getFitness()>=parent2.getFitness())
							genome.add(l1.get(i));
				}
				//case 2.2 only parent 2 has this gene
				else if(l2.get(i)!= null){
					if(parent2.getFitness()>parent1.getFitness())
						genome.add(l2.get(i));
				}
				if(((l1.get(i)==null ||!l1.get(i).isEnabled()) || (l2.get(i)== null || !l2.get(i).isEnabled())) && r.nextDouble()< pDisabledP){//in the case one of the parent has this gene disable, you have pDisabledP probability of inherit it.
					genome.get(genome.size()-1).setEnable(false);
				}else {
					genome.get(genome.size()-1).setEnable(true);
				}if(r.nextDouble()<pDisable){//randomly disable a node
					genome.get(genome.size()-1).setEnable(false);
				}
			}
			//case 3: nobody has this gene... Nothing happen
		}
		//case 4: one of the genome is longer, so it has excess genes
		for(int i = Math.min(l1.size(), l2.size());i< Math.max(l1.size(), l2.size());i++){
			//case 4.1 l1 is longer than l2
			if(l1.size()> l2.size() && l1.get(i)!=null){
				if(parent1.getFitness()>=parent2.getFitness())
					genome.add(l1.get(i));
			}
			//case 4.2 l2 is longer than l1
			else if(l2.size()>l1.size() && l2.get(i)!=null){
				if(parent2.getFitness()>parent1.getFitness())
					genome.add(l2.get(i));
			}
		}
		
		
		/*Mutation*/
		//M1: weight mutations
		if(r.nextDouble() < weightMutation){
			for(Gene g:genome){
				//case 1, perturbance
				if(r.nextDouble()< weightPerturbed){
					g.setWeight(g.getWeight()+(((r.nextDouble()-0.5)*2*weightRange)*perturbanceRange));
				}
				//case 2, randomly replace 
				else{
					g.setWeight((r.nextDouble()-0.5)*2);
				}
			}
		}
		//M2: structural mutation, add node
		if(r.nextDouble()<newNeuron){
			//get number of nodes of the fittest parent, equal to the number of nodes of the offspring, before mutation.
			//method 1
			int nodes = 0;
			for(Gene g:genome){
				int max = Math.max(g.getInputNode(), g.getOutputNode());
				if (max > nodes){
					nodes = max;
				}
			}
			nodes++;
			//potentially faster method 2
//			int nodes = 0;
//			if(parent1.getFitness()>=parent2.getFitness()){
//				nodes = parent1.getNet().size();
//			}else if(parent2.getFitness()>parent1.getFitness()){
//				nodes = parent2.getNet().size();
//			}
			
			
			//select a random connection (gene) to disable to sobstitute with a node
			int i = r.nextInt(genome.size());
			Gene toDisable = genome.get(i);
			toDisable.setEnable(false);
			Gene connection1 = new Gene(toDisable.getInputNode(), nodes, toDisable.getWeight(), ++globalInnovationNumber);
			Gene connection2 = new Gene(nodes++,toDisable.getOutputNode(),1,++globalInnovationNumber);
			genome.add(connection1);
			genome.add(connection2);
		}
		//M3: structural mutation, add connection
		if(r.nextDouble()<newConnection){
			int nodes = 0;
			for(Gene g:genome){
				int max = Math.max(g.getInputNode(), g.getOutputNode());
				if (max > nodes){
					nodes = max;
				}
			} 
			int node1 = r.nextInt(nodes+1);
			int node2 = r.nextInt(nodes+1-inputs)+inputs;
			//check wether the connection already exists.
			if(!existConnection(node1,node2,genome) && node1!=node2){
				Gene gene = new Gene(node1,node2,(r.nextDouble()-0.5)*2,++globalInnovationNumber);
				genome.add(gene);
			}
		}
		Network returner = new Network(parent1.getInputs(),parent1.getOutputs(),genome);
		return returner;
	}
	private static boolean existConnection(int node1, int node2, ArrayList<Gene> genome) {
		for(Gene g:genome){
			if(g.getInputNode()==node1 && g.getOutputNode()==node2){
				return true;
			}
		}
		return false;
	}
	public void setFitness(Fitness f) {
		fitnessFuncion = f;
	}
}
