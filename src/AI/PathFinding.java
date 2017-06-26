package AI;

import java.util.ArrayList;

import game.Entity;
import game.Graph;
import game.Node;
import game.Pursuer;

public class PathFinding {
	ArrayList<Node> path = new ArrayList<Node>();
	PathNode[][] grid;
	ArrayList<PathNode> openSet= new ArrayList<PathNode>();
	ArrayList<PathNode> closedSet= new ArrayList<PathNode>();
	public Node eNode;
	private Graph graph;

	public PathFinding(Entity entity, Graph graph, int[] endNode) {
		this.graph = graph;
		PathNode.setGraph(graph);
		eNode = graph.getNodeGrid()[endNode[0]][endNode[1]];
		grid = new PathNode[graph.getNodeGrid().length][graph.getNodeGrid()[0].length];
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[i].length;j++){
				grid[i][j]=new PathNode(graph.getNodeGrid()[i][j],eNode);
			}
		}
		
		grid[entity.getNode().getY()][entity.getNode().getX()].setPathDistance(0);
		openSet.add( grid[entity.getNode().getY()][entity.getNode().getX()]);
		while(!openSet.isEmpty()){
			if(openSet.get(0).getNode()==eNode){
				break;
			}
			if(entity instanceof Pursuer)
				expandPursuer(openSet.get(0));
			else
				expandEvader(openSet.get(0));
		}
		if(openSet.isEmpty()){
			System.out.println("No Path found");
		}else{
			PathNode node = grid[endNode[0]][endNode[1]];
			while(node.getNode()!=entity.getNode()){
				path.add(node.getNode());
				node = node.getParent();
			}
		}
		
	}

	private void addOpen(PathNode expand) {
		
			if(openSet.isEmpty()){
				openSet.add(expand);
			}else{
				for(int i=0;i<openSet.size();i++){
					if(expand.getTotalPathValue()<openSet.get(i).getTotalPathValue()){
						openSet.add(i,expand);
						return;
					}
				}
				openSet.add(expand);
			}
		
	}


	private ArrayList<PathNode> expandEvader(PathNode node) {
		ArrayList<PathNode> pn = new ArrayList<PathNode>();
		openSet.remove(node);
		closedSet.add(node);
		for(Node n:node.getNode().getActiveNeighbors()){
			PathNode nextNode = grid[n.getY()][n.getX()];
			pn.add(nextNode);
			if(openSet.contains(nextNode)){
				if(nextNode.getPathDistance()<=node.getPathDistance()+ graph.getVisibilityMap().getVisibilityMatrix()[nextNode.getNode().getY()][nextNode.getNode().getX()]){
					continue;
				}
				openSet.remove(nextNode);
				nextNode.setPathDistance(node.getPathDistance()+ graph.getVisibilityMap().getVisibilityMatrix()[nextNode.getNode().getY()][nextNode.getNode().getX()]);
				addOpen(nextNode);
			}else if(closedSet.contains(grid[n.getY()][n.getX()])){
				if(nextNode.getPathDistance()<=node.getPathDistance()+ graph.getVisibilityMap().getVisibilityMatrix()[nextNode.getNode().getY()][nextNode.getNode().getX()]){
					continue;
				}
				closedSet.remove(nextNode);
				nextNode.setPathDistance(node.getPathDistance()+ graph.getVisibilityMap().getVisibilityMatrix()[nextNode.getNode().getY()][nextNode.getNode().getX()]);
				addOpen(nextNode);
			}else{
				nextNode.setPathDistance(node.getPathDistance()+ graph.getVisibilityMap().getVisibilityMatrix()[nextNode.getNode().getY()][nextNode.getNode().getX()]);
				addOpen(nextNode);
			}
			nextNode.setParent(node);
		}
		return pn;
	}	private ArrayList<PathNode> expandPursuer(PathNode node) {
		ArrayList<PathNode> pn = new ArrayList<PathNode>();
		openSet.remove(node);
		closedSet.add(node);
		for(Node n:node.getNode().getActiveNeighbors()){
			PathNode nextNode = grid[n.getY()][n.getX()];
			pn.add(nextNode);
			if(openSet.contains(nextNode)){
				if(nextNode.getPathDistance()<=node.getPathDistance()+1){
					continue;
				}
				openSet.remove(nextNode);
				nextNode.setPathDistance(node.getPathDistance()+1);
				addOpen(nextNode);
			}else if(closedSet.contains(grid[n.getY()][n.getX()])){
				if(nextNode.getPathDistance()<=node.getPathDistance()+1){
					continue;
				}
				closedSet.remove(nextNode);
				nextNode.setPathDistance(node.getPathDistance()+1);
				addOpen(nextNode);
			}else{
				nextNode.setPathDistance(node.getPathDistance()+1);
				addOpen(nextNode);
			}
			nextNode.setParent(node);
		}
		return pn;
	}

	public Node removeNextNode() {
		Node ret = path.get(path.size()-1);
		path.remove(ret);
		return ret;
	}

	public boolean hasNextNod() {
		return !path.isEmpty();
	}

}
