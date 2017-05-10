package NeuralNetwork;

public class Gene {
	private double weight;
	private int inputNode;
	private int outputNode;
	private boolean enabled = true;
	private int innovation;
	public Gene(int inputNode, int outputNode, int innovation, double weight) {
		this.inputNode = inputNode;
		this.outputNode = outputNode;
		this.innovation = innovation;
		this.weight = weight;
	}
	public void enableFlip(){
		enabled = !enabled;	
	}
	public int getInputNode() {
		return inputNode;
	}
	public void setInputNode(int inputNode) {
		this.inputNode = inputNode;
	}
	public int getOutputNode() {
		return outputNode;
	}
	public void setOutputNode(int outputNode) {
		this.outputNode = outputNode;
	}
	public boolean isEnabled() {
		return enabled;
	}
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	public int getInnovation() {
		return innovation;
	}
	public void setInnovation(int innovation) {
		this.innovation = innovation;
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public String toString(){
		String s = "in: "+inputNode+" out:" +outputNode+" W: "+weight+" innov: "+ innovation+" enabled: "+enabled;
		
		return s;
	}
}
