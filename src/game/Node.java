package game;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Node {
	private String value = "";//What's in this node? Default, nothing.
	public Rectangle rectangle; //coordinates of the node + dimension
	private int x;
	private int y;
	private ArrayList<Node> activeNeighbors = new ArrayList<Node>();
	private ArrayList<Node> neighbors = new ArrayList<Node>();

	public Node(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void addNeighbor(Node node) {
		if(node !=null){
			neighbors.add(node);
			activeNeighbors.add(node);
		}else
			System.err.println("NULL NODE");
		
	}
	public void removeAllNeighbors() {
		ArrayList<Node> all = new ArrayList<Node>();
		for(Node node:activeNeighbors){
			all.add(node);
		}activeNeighbors.removeAll(all);
		
	}
	public void restoreNeighbors() {
		activeNeighbors = new ArrayList<Node>();
		for(Node node:neighbors){
			activeNeighbors.add(node);
		}
		
	}
	public ArrayList<Node> getNeighbors(){
		return neighbors;
	}
	public ArrayList<Node> getActiveNeighbors() {
		return activeNeighbors;
	}
	public void setValue(String string) {
		value = string;
	}
	public String getValue(){
		return value;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}
