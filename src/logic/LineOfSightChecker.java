package logic;

import java.util.ArrayList;

import game.Entity;
import game.Graph;
import game.Node;

public class LineOfSightChecker {

	private ArrayList<Entity> toCheck;
	private ArrayList<Entity> checked = new ArrayList<>();
	private ArrayList<Pair> pairs = new ArrayList<>();
	
	public void checkEntities(Graph graph) {
		toCheck = graph.getEntities();
		RayTracer tracer = new RayTracer();
		Node[][] grid = graph.getNodeGrid();
		while (toCheck.size() != 0) {
			Entity currentEntity = toCheck.remove(0);
			for(Entity ent : toCheck) {
				int x0 = currentEntity.getNode().rectangle.x;
				int y0 = currentEntity.getNode().rectangle.y;
				int x1 = ent.getNode().rectangle.x;
				int y1 = ent.getNode().rectangle.y;
				ArrayList<Node> rayTrace = tracer.getRayTrace(x0, x1, y0, y1, grid);
				boolean isLineOfSight = true;
				for(Node node : rayTrace) {
					if (isLineOfSight && node.getValue().equals("")) { //need the wall value
						isLineOfSight = false;
					}
				}
				pairs.add(new Pair(currentEntity, ent, isLineOfSight));
			}
			checked.add(currentEntity);
		}
	}
}
