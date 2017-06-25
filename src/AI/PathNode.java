package AI;

import game.Graph;
import game.Node;

public class PathNode{

	Node node;
	int pathDistance=Integer.MAX_VALUE;
	int remainDistance;
	PathNode parent;
	static private Graph graph;
	public PathNode(Node n, Node eNode){
		this.node = n;
		remainDistance = manathanDistance(node,eNode);
		
		
	}


	public static int manathanDistance(Node node, Node eNode) {
		
		return Math.abs(node.getX()-eNode.getX())+Math.abs(node.getY()-eNode.getY());
	}
	public int getTotalPathValueOld() {
		if(pathDistance == Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		return remainDistance+pathDistance;
	}
	public int getTotalPathValue() {
		if(pathDistance == Integer.MAX_VALUE)
			return Integer.MAX_VALUE;
		return remainDistance+pathDistance+graph.getVisibilityMap().getVisibilityMatrix()[node.getY()][node.getX()];
	}

	public Node getNode() {
		
		return node;
	}
	
	public static void setGraph(Graph g){
		graph = g;
	}
	public int getPathDistance(){
		return pathDistance;
	}


	public void setPathDistance(int i) {
		pathDistance = i;
	}


	public void setParent(PathNode node2) {
		parent = node2;
		
	}


	public PathNode getParent() {
		return parent;
	}
}
