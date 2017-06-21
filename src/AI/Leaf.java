package AI;

import java.util.ArrayList;

import game.Graph;
import game.Node;

public class Leaf {
	private Leaf parent = null;
	private ArrayList<Leaf> children = new ArrayList<Leaf>();
 	private Node node;
 	private double[][] dirtyCleanMatrix;
 	private double value = Double.MIN_VALUE;
  
 	public Leaf(Node n, double[][] dirtyCleanMatrix){
	  this.dirtyCleanMatrix = dirtyCleanMatrix;
	  node = n;
  }public Leaf(Node n, double[][] dirtyCleanMatrix,Leaf parent){
	  this.dirtyCleanMatrix = dirtyCleanMatrix;
	  node = n;
	  this.parent = parent;
	  parent.setChildren(this);
  }
	private void setChildren(Leaf leaf) {
	if(children == null){
		children = new ArrayList<Leaf>();
	}
	children.add(leaf);
	
}
	public Leaf getParent() {
		return parent;
	}
	public void setParent(Leaf parent) {
		this.parent = parent;
	}
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	public double[][] getDirtyCleanMatrix() {
		return dirtyCleanMatrix;
	}
	public void setDirtyCleanMatrix(double[][] ds) {
	this.dirtyCleanMatrix = ds;
}
	public void evaluate(Graph graph) {
		VisibilityChecker toCompare;
		SetEvaluator evaluator;
		toCompare = new VisibilityChecker();
        toCompare.checkEntitiesCurrent(graph,node);
        evaluator=new SetEvaluator(toCompare);
        evaluator.evaluateDirtyClean(parent.getDirtyCleanMatrix());
        this.setDirtyCleanMatrix(evaluator.getDirtyClean());
        if(children.isEmpty()){
        	this.setValue(evaluator.getSumOfDirtyClean());
        }
        else{
        	double bestValue = Double.MIN_VALUE;
			for(Leaf kid:children){
				kid.evaluate(graph);
				if(kid.getValue()>=bestValue){
					bestValue = kid.getValue();
					this.setValue(kid.getValue());
				}
			}
        }
		
	}
	public double getValue(){
		return value;
	}
	public void setValue(double value){
		this.value = value;
	}
}
