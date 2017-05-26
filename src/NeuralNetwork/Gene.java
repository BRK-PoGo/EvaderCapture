package NeuralNetwork;

public class Gene {
	private double weight;
	private int inputNode;
	private int outputNode;
	private boolean enabled = true;
	private int innovation;
	public Gene(int inputNode, int outputNode, double weight, int innovation) {
		this.inputNode = inputNode;
		this.outputNode = outputNode;
		this.innovation = innovation;
		this.weight = weight;
	}
	public void setEnable(boolean b){
		enabled = b;	
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
		String s = inputNode+" " +outputNode+"  "+weight+"   "+ innovation+"  "+enabled;
		
		return s;
	}
	public Gene copy() {
		Gene g= new Gene(inputNode, outputNode, weight, innovation);
		g.setEnable(enabled);
		return g;
	}
}
