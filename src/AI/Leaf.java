package AI;

import game.Node;

public class Leaf {
	private Leaf parent = null;
 	private Node node;
 	private int[][] dirtyCleanMatrix;
  
 	public Leaf(Node n, int[][] dirtyCleanMatrix){
	  this.dirtyCleanMatrix = dirtyCleanMatrix;
	  node = n;
  }public Leaf(Node n, int[][] dirtyCleanMatrix,Leaf parent){
	  this.dirtyCleanMatrix = dirtyCleanMatrix;
	  node = n;
	  this.parent = parent;
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
	public int[][] getDirtyCleanMatrix() {
		return dirtyCleanMatrix;
	}
	public void setDirtyCleanMatrix(int[][] dirtyCleanMatrix) {
	this.dirtyCleanMatrix = dirtyCleanMatrix;
}
}
