package AI;

import game.Node;

public class Leaf {
	private Leaf parent = null;
 	private Node finalNode;
 	private int[][] dirtyCleanMatrix;
  
 	public Leaf(Node n, int[][] dCM){
	  dirtyCleanMatrix = dCM;
	  finalNode = n;
  }public Leaf(Node n, int[][] dCM,Leaf parent){
	  dirtyCleanMatrix = dCM;
	  finalNode = n;
	  this.parent = parent;
  }
	public Leaf getParent() {
		return parent;
	}
	public void setParent(Leaf parent) {
		this.parent = parent;
	}
	public Node getFinalNode() {
		return finalNode;
	}
	public void setFinalNode(Node finalNode) {
		this.finalNode = finalNode;
	}
	public int[][] getDirtyCleanMatrix() {
		return dirtyCleanMatrix;
	}
	public void setDirtyCleanMatrix(int[][] dirtyCleanMatrix) {
	this.dirtyCleanMatrix = dirtyCleanMatrix;
}
}
