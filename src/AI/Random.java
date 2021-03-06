package AI;

import game.Algorithm;
import game.Entity;
import game.Graph;
import game.Node;

public class Random implements Algorithm{
	private Graph graph;
	
	public Random(Graph graph){
		this.graph = graph;
	}

	@Override
	public void move(Entity entity) {
		java.util.Random r = new java.util.Random();
		
		int random = (int) Math.floor(r.nextInt(entity.getNode().getActiveNeighbors().size()));
		Node node = entity.getNode().getActiveNeighbors().get((int)random);
		if(node.getValue()=="")
			entity.moveToNode(node, graph, "UP");
		else {
			recursiveMove(entity,0);
		}
		
	}
	public void move(Entity entity,Node nodeNoGo) {
		java.util.Random r = new java.util.Random();
		Node node;
		do{
		int random = (int) Math.floor(r.nextInt(entity.getNode().getActiveNeighbors().size()));
		node = entity.getNode().getActiveNeighbors().get((int)random);
		}while(node == nodeNoGo );
		if(node.getValue()=="")
			entity.moveToNode(node,graph,entity.getDir());
		else {
			recursiveMove(entity,0);
		}
		
	}
	private void recursiveMove(Entity entity,int counter) {
		int random = (int) Math.floor(Math.random()*entity.getNode().getActiveNeighbors().size());
		Node node = entity.getNode().getActiveNeighbors().get((int)random);
		if(node.getValue()=="")
			entity.moveToNode(node,graph,"UP");
		else if (counter<6){
			System.err.println(counter);
			counter++;
			recursiveMove(entity,counter);
		}
		
	}

	@Override
	public boolean getPathFind() {
		return false;
		
	}

}
