package NeuralNetwork;

import java.util.ArrayList;
import java.util.Random;

public class GA {
	private ArrayList<Network> population;
	private ArrayList<ArrayList<Network>> species = new ArrayList<ArrayList<Network>>();
	private int globalInnovationNumber = 0;//latest innovation.
	
	//constants
	double c1 = 1;//value of excess for distance
	double c2 = 1;//value of disjoint for distance
	double c3 = 0.4;//value of average weight difference
	double Dt = 3;// max distance from the representator of a specie, to enter in that specie.
	
	
	public GA(int popSize, int inputLayers, int outputLayers){
		population = new ArrayList<Network>();
		for(int i = 0;i<popSize;i++){
			population.add(new Network(inputLayers, outputLayers));
		}
		if(popSize>0)
			globalInnovationNumber = population.get(0).getGenome().size();
	}
	public ArrayList<Network> getPopulation() {
		return population;
	}
	public double distance(Network n1, Network n2){
		
		ArrayList<Gene> l1 = new ArrayList<Gene>();
		ArrayList<Gene> l2 = new ArrayList<Gene>();
		for(Gene g:n1.getGenome()){
			if(l1.size()<=g.getInnovation()){
				for(int i=0;i<=g.getInnovation()-l1.size();i++){
					l1.add(null);
				}
			}
			l1.set(g.getInnovation()-1, g);
		}for(Gene g:n2.getGenome()){
			if(l2.size()<=g.getInnovation()){
				for(int i=0;i<=g.getInnovation()-l2.size();i++){
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
		int N = Math.max(n1.getGenome().size(),n2.getGenome().size());
		return (c1*excess/N)+(c2*disjoint/N)+(c3*w);
	}
	public void sortSpeciees(){
		ArrayList<Network> rep = new ArrayList<Network>();
		for (ArrayList<Network> s:species){//go trough pre-existing species
			Random r = new Random();
			int temp = r.nextInt(s.size());
			rep.add(s.get(temp));
		}
		
		//sort population
		for(Network n:population){
			for(int i=0;i<rep.size();i++){
				if(distance(n,rep.get(i))<=Dt){
					species.get(i).add(n);
					break;
				}else if(i>=rep.size()-1){
					rep.add(n);
					species.add(new ArrayList<Network>());
					species.get(species.size()-1).add(n);
				}
			}
		}
	}
}
