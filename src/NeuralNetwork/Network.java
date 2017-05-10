package NeuralNetwork;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.eclipse.wb.swing.Tools;

public class Network {
	ArrayList<Gene> genome = new ArrayList<Gene>();
	ArrayList<Node> net;//net[i] is Neuron i.
	ArrayList<Node> inputNodes;
	ArrayList<Node> outputNodes;
	private int inputs;//input layer size, inputs nodes are from net.get(0) to net.get(inputs).
	private int outputs;//output layer size, output nodes are from net.get(inputs) to net.get(inputs + outputs) 
	public Network(ArrayList<Gene> genome){
		this.genome = genome;
		genomeDecoding();
		
	}
	public Network(String file){
		//make a network from a file in ./NNFiles

	    FileReader in = null;
		try {
			in = new FileReader("./src/NeuralNetwork/NNFiles/"+file+".txt");
		} catch (FileNotFoundException e) {
			System.err.println("FILE NOT FOUND");
			e.printStackTrace();
		}
	    BufferedReader br = new BufferedReader(in);
	    String line = null;
	    try {
			line = br.readLine();
			String[] splited = line.split("\\s+");
		    int s = Integer.parseInt(splited[0]);//genome length.
		    inputs = Integer.parseInt(splited[1]);//input layer size
		    outputs = Integer.parseInt(splited[2]);//output layer size
			for(int i=0;i<s;i++){
				line =  br.readLine();
				splited = line.split("\\s+");
				Gene g = new Gene(Integer.parseInt(splited[0]),Integer.parseInt(splited[1]),Integer.parseInt(splited[2]),Double.parseDouble(splited[3]));
				if(splited[4].equals("0")){
					g.enableFlip();
				}
				genome.add(g);
			}
		} catch (IOException e) {
			System.err.println("FILE INVALID");
			e.printStackTrace();
		}
	    try {
			in.close();
		} catch (IOException e) {
			System.err.println("ERROR CLOSING");
			e.printStackTrace();
		}
	    genomeDecoding();
	}
	public void genomeDecoding() {
		//this method update the changes made to the genome in the actual network, to be done also after initialization.
			net = new ArrayList<Node>();
	
		for(Gene g:genome){
			
			int in = g.getInputNode();
			int out = g.getOutputNode();
			if(net.size()<=in || net.size()<= out){//if array list is not long enough, expand it.
				for(int i=net.size();i<=Math.max(in, out);i++){
					net.add(i, null);
				}
			}
			if(net.get(in) == null){//if in does not exists yet, create it
				net.set(in, new Node());
				}			
			if(net.get(out) == null){//if in does not exists yet, create it
				net.set(out, new Node());
			}
			if(g.isEnabled()){
				net.get(in).addOutput(net.get(out),g);
				net.get(out).addInput(net.get(in));
			}
		}
		//organize input and output neurons
		inputNodes = new ArrayList<Node>();
		outputNodes = new ArrayList<Node>();
		for(int i=0; i<inputs*outputs;i++){//go trough the original genome (Before evolving), composed only of the connection of in-& out-put layers
			if(!inputNodes.contains(net.get(genome.get(i).getInputNode())))
				inputNodes.add(net.get(genome.get(i).getInputNode()));
			if(!outputNodes.contains(net.get(genome.get(i).getOutputNode())))
				outputNodes.add(net.get(genome.get(i).getOutputNode()));
		}

	}
	public double[] execute(double[] input){
		/**
		 * output of this method is the output layer, in double
		**/
		ArrayList<Node> toFire = new ArrayList<Node>();
		if(input.length == inputNodes.size()){
			for(int i=0;i<inputNodes.size();i++){//fire the input nodes.
				toFire = Tools.union(toFire, inputNodes.get(i).fire(input[i]));
				//toFire.addAll(inputNodes.get(i).fire(input[i]));
			}
		}else{
			System.err.println("ERROR, Input dimentions missmatch");
			System.exit(1);
		}
		while(!toFire.isEmpty()){
			Node next = getNext(toFire);
			toFire.remove(next);
			toFire = Tools.union(toFire, next.fire());
		}
		double output[] = new double[outputs];
		for(int i=0;i<outputs;i++){
			double temp = outputNodes.get(i).getOutputValue();
			output[i] = temp;
		}
		return output;
	}
	private Node getNext(ArrayList<Node> toFire) {
		//the best next neuron to fire is the one with lower number of missing inputs.
		Node OBJ_best = null;
		int INT_best =Integer.MAX_VALUE;
		for(Node n:toFire){
			if(n.getInputMissing() <= 0){
				return n;
			}
			if(n.getInputMissing()<INT_best){
				OBJ_best = n;
				INT_best = n.getInputMissing();
			}
				
		}
		return OBJ_best;
		
	}
	
}
