package AI;

import java.util.ArrayList;

import game.Entity;
import game.Graph;
import game.Node;

public class Leaf {
	private Leaf parent = null;
	private ArrayList<Leaf> children = new ArrayList<Leaf>();
 	private Node node;
 	private double[][] dirtyCleanMatrix;
 	private double value = Double.MIN_VALUE;
 	private String dir;
  
 	public Leaf(Node n, double[][] dirtyCleanMatrix,String dir){
	  this.dirtyCleanMatrix = dirtyCleanMatrix;
	  node = n;
	  this.dir = dir;
  }public Leaf(Node n, double[][] dirtyCleanMatrix,Leaf parent){
	  this.dirtyCleanMatrix = dirtyCleanMatrix;
	  node = n;
	  this.parent = parent;
	  parent.setChildren(this);
  }public Leaf(Node n, double[][] dirtyCleanMatrix,Leaf parent, String dir){
	  this.dirtyCleanMatrix = dirtyCleanMatrix;
	  node = n;
	  this.parent = parent;
	  parent.setChildren(this);
	  this.dir = dir;
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
	public void evaluate(Graph graph, Entity ent) {
		VisibilityChecker toCompare;
		ProbSetEvaluator evaluator;
		toCompare = new VisibilityChecker(graph,node,ent,dir);
        evaluator=new ProbSetEvaluator(toCompare);
        evaluator.evaluateDirtyClean(parent.getDirtyCleanMatrix(),ent.getNode().getY(),ent.getNode().getX());
        this.setDirtyCleanMatrix(evaluator.getDirtyClean());
        if(children.isEmpty()){
        	this.setValue(evaluator.getSumOfDirtyClean());
        }
        else{
        	double bestValue = Double.MIN_VALUE;
			for(Leaf kid:children){
				kid.evaluate(graph, ent);
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
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getDir() {
		return dir;
	}
}
