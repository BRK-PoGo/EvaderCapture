package AI;

import java.util.ArrayList;

import game.Entity;
import game.Graph;
import game.Node;

public class PathFinding {
	ArrayList<Node> path = new ArrayList<Node>();
	PathNode[][] grid;
	ArrayList<PathNode> openSet= new ArrayList<PathNode>();
	ArrayList<PathNode> closedSet= new ArrayList<PathNode>();
	private Node eNode;

	public PathFinding(Entity entity, Graph graph, int[] endNode) {
		PathNode.setGraph(graph);
		eNode = graph.getNodeGrid()[endNode[0]][endNode[1]];
		grid = new PathNode[graph.getNodeGrid().length][graph.getNodeGrid()[0].length];
		for(int i=0;i<grid.length;i++){
			for(int j=0;j<grid[i].length;j++){
				grid[i][j]=new PathNode(graph.getNodeGrid()[i][j],eNode);
			}
		}
		
		grid[entity.getNode().getX()][entity.getNode().getY()].setPathDistance(0);
		openSet.add( grid[entity.getNode().getX()][entity.getNode().getY()]);
		while(!openSet.isEmpty()){
			if(openSet.get(0).getNode()==eNode){
				break;
			}
			expand(openSet.get(0));
		}
		if(openSet.isEmpty()){
			System.out.println("No Path found");
		}else{
			PathNode node = grid[endNode[0]][endNode[1]];
			path.add(node.getNode());
			while(node.getParent()!=null){
				node = node.getParent();
				path.add(node.getNode());
			}
		}
		
	}

	private void addOpen(PathNode expand) {
		
			if(openSet.isEmpty()){
				openSet.add(expand);
			}else{
				for(int i=0;i<openSet.size();i++){
					if(expand.getTotalPathValue()<openSet.get(i).getTotalPathValue()){
						for(int j=openSet.size()-1;j>=i;j--){
							openSet.add(j+1, openSet.get(j));
						}
						openSet.add(i,expand);
						break;
					}
				}
			}
		
	}


	private ArrayList<PathNode> expand(PathNode node) {
		ArrayList<PathNode> pn = new ArrayList<PathNode>();
		openSet.remove(node);
		closedSet.add(node);
		for(Node n:node.getNode().getActiveNeighbors()){
			PathNode nextNode = grid[n.getX()][n.getY()];
			pn.add(nextNode);
			if(openSet.contains(nextNode)){
				if(nextNode.getPathDistance()>=node.getPathDistance()+1)continue;
			}else if(closedSet.contains(grid[n.getX()][n.getY()])){
				if(nextNode.getPathDistance()>=node.getPathDistance()+1)continue;
				closedSet.remove(nextNode);
				addOpen(nextNode);
			}else{
				addOpen(nextNode);
			}
			nextNode.setPathDistance(node.getPathDistance()+1);
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
