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


	private int manathanDistance(Node node2, Node eNode) {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getTotalPathValue() {
		return pathDistance+remainDistance;
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
