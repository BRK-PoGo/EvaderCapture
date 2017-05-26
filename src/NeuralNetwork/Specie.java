package NeuralNetwork;

import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Random;

import org.eclipse.wb.swing.Tools;

public class Specie {
	private static int globalCounter =0;
	private int specieID;//SPECIE #
	private ArrayList<Network> specie = new ArrayList<Network>();
	private Network champion;
	private int offspringSize;
	private double sharedFitness;
	private double summedFitness;
	private static GA ga;
	
	public Specie(){
		super();
		globalCounter++;
		specieID = globalCounter;
	}
	public void reproduce(){
		ArrayList<Network> nextGen = new ArrayList<Network>();
		int i=0;
		if(specie.size()>=5){//if the specie is big enough,
			nextGen.add(champion);//add champion, unmuted
			i++;
		}
		Network parent1;
		Network parent2;
		/*natural selection*/
		//this method dont allowed for multiple riproduction and "same parent" reproduction
//		for(;i<offspringSize;i++){
//			parent1=null;
//			parent2=null;
//			
//			if(specie.size()>= 2+parents.size()){//if there are still at least 2 parents avaiable for reproduction
//					parent1 = getParent(parents);
//					parent2 = getParent(parents);
//				if(parent1 == parent2)
//					{i--; continue;}
//				
//				parents.add(parent2);
//				parents.add(parent1);
//			}else if(specie.size()== 1+parents.size()){//if there is only 1 parent avaiable for reproduction
//					parent1 = getParent(parents);
//					parents.clear();
//					while(parent2==null||parent2!=parent1)
//						parent2 = getParent(parents);
//					parents.add(parent2);
//			}else{
//				parents.clear();
//				i--; continue;
//			}
//			Network newNet = GA.crossOver(parent1, parent2);
//			xor xor = new xor();
//			xor.getFitness(newNet);
//			nextGen.add(newNet);	
//		}
		for(;i<offspringSize;i++){
			parent1=null;
			parent2=null;
			
				parent1 = getParent();
				parent2 = getParent();
	
	
			Network newNet = ga.crossOver(parent1.copy(), parent2.copy());
			newNet.setParents(parent1, parent2);
			xor xor = new xor();
			xor.getFitness(newNet);
			nextGen.add(newNet);	
		}
		specie = nextGen;
	}
	private Network getParent() {
		
		return getParent(new ArrayList<Network>());
	}
	public int size() {
		return specie.size();
	}
	public Network get(int temp) {
		return specie.get(temp);
	}
	public void add(Network n) {
		specie.add(n);
	}
	public ArrayList<Network> getPop() {
		return specie;	
	}
	public void calculateSpecieFitness(){
		//calculate shared fitness  and the summed fitness of the specie
			sharedFitness =0;
			summedFitness =0;
		for(Network n:specie){
			n.adjustFitness();
			sharedFitness += n.getAdjFitness();
			summedFitness += n.getFitness();
		}
	}
	public Network getChampion() {
		return champion;
	}
	public void setChampion(Network champion) {
		this.champion = champion;
	}
	public int getOffspringSize() {
		return offspringSize;
	}
	public void setOffspringSize(int d) {
		this.offspringSize = d;
	}
	public double getSharedFitness() {
		return sharedFitness;
	}
	private Network getParent(ArrayList<Network> parents){
		double parentSum =0;
		for(Network n:parents){
			parentSum +=n.getFitness();
		}
		double cumulativeProbability = 0;
		Random r = new Random();
		  double random = r.nextDouble();
//		  double summedD  =0;
//		  double summedTot =0;
//		  for (Network n : specie) {
//			    summedTot += n.getFitness();
//			}
		  	ArrayList<Network> t = Tools.subtract(specie, parents);
		  	if(t.size()!=specie.size()-parents.size()){
		  		System.err.println("Error, double found");
		  	}
//			for (Network n : Tools.subtract(specie, parents)) {
//			    summedD += n.getFitness();
//			}
//			if(summedD > (summedFitness-parentSum)+0.0001 || summedD < (summedFitness-parentSum)-0.0001){
//				System.out.println(summedD+" "+(summedFitness-parentSum));
//			}
		for (Network n : Tools.subtract(specie, parents)) {
		    cumulativeProbability += n.getFitness()/(summedFitness-parentSum);
		    if ( random < cumulativeProbability) {
		        return n;
		    }
	
		}
    	System.out.println("CUM: "+cumulativeProbability);

		System.err.println("GetParent couldnt find a parent ");
		return null;
	}
	public int getSpecieID(){
		return specieID;
	}
	public void write(int generation, String name){				
		for(Network net:specie){
			new File("./src/NeuralNetwork/NNFiles/"+name+"/Generation"+generation+"/Specie"+specieID).mkdirs();
			net.write("/"+name+"/Generation"+generation+"/Specie"+specieID+"/Net"+(net.getID()));
		}
	}
	public void clear() {
		champion = null;
		specie = new ArrayList<Network>();
		
	}
	public double getSummedFitness() {
		return summedFitness;
	}

	public void survive(double percentage) {
		ArrayList<Network> toRemove = new ArrayList<Network>();
		for(int i=0;i<(int)(specie.size()*percentage);i++){
			Network net = null;
			for(Network n:Tools.subtract(specie, toRemove)){
				if(net ==null || net.getFitness()>n.getFitness()){
					net = n;
				}
			}
			toRemove.add(net);
		}
		specie = Tools.subtract(specie, toRemove);
		summedFitness=0;
		for(Network n:specie){
			summedFitness+=n.getFitness();
		}
		
	}
	public static void setGA(GA ga2) {
		ga=ga2;
		
	}
}
