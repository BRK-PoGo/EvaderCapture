package NeuralNetwork;

import java.util.ArrayList;

public class Node {
	private ArrayList<Node> inputs=new ArrayList<Node>();
	private int recivedInputs = 0;
	
	private ArrayList<Node> outputs=new ArrayList<Node>();
	private ArrayList<Gene> genes=new ArrayList<Gene>();//genes with input this node, and output one of its outputs. in same order as outputs array list.
	
	private double inputValue=0;
	
	public ArrayList<Node> fire(){
		double out = sigmoid(inputValue);
		inputValue = 0;
		for(int i = 0;i<outputs.size();i++){
			outputs.get(i).increase(out*(genes.get(i).getWeight()));
		}
		//return all outputs, expect output layer neurons.
		ArrayList<Node> outp = new ArrayList<Node>();
		for(Node n:outputs){
			if(n.outputs.size()>0){
				outp.add(n);
			}
		}
		return outp;
	}
	public ArrayList<Node> fire(double d) {
		increase(d);
		double out = inputValue;
		inputValue = 0;
		for(int i = 0;i<outputs.size();i++){
			outputs.get(i).increase(out*(genes.get(i).getWeight()));
		}
		//return all outputs, expect output layer neurons.
		ArrayList<Node> outp = new ArrayList<Node>();
		for(Node n:outputs){
			if(n.outputs.size()>0){
				outp.add(n);
			}
		}
		return outp;
		
	}
	public void increase(double input){
		inputValue +=input;
		recivedInputs++;
	}
	public void addOutput(Node out, Gene g){
		outputs.add(out);
		genes.add(g);
	}
	public void addInput(Node in){
		inputs.add(in);
	}
	public int getInputMissing() {
	
		return inputs.size()-recivedInputs;
	}
	public double getOutputValue() {
		// TO use only in output neurons
		double value = inputValue;
		inputValue = 0;
		return sigmoid(value);
		
	}
	public double sigmoid(double value) {
		double a = 4.9; //constant of the sigmoid function. For high a, the sigmoid approximates a binary step function.
		return 1/(1+Math.exp(-a*value));
	}
}