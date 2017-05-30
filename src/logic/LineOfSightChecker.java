package logic;

import java.util.ArrayList;

import game.Entity;
import game.Graph;
import game.Node;

public class LineOfSightChecker {

	private ArrayList<Entity> toCheck;
	private ArrayList<Entity> checked = new ArrayList<>();
	private ArrayList<Pair> pairs = new ArrayList<>();
	
	public ArrayList<Pair> checkEntities(Graph graph) {
		checked.removeAll(checked);
		pairs.removeAll(pairs);
		toCheck = graph.getEntities();
		RayTracer tracer = new RayTracer();
		Node[][] grid = graph.getNodeGrid();
		for(Entity currentEntity : toCheck) {
			for(Entity ent : toCheck) {
				if (currentEntity != ent && !checked.contains(ent)) {
					int x0 = currentEntity.getNode().getX();
					int y0 = currentEntity.getNode().getY();
					int x1 = ent.getNode().getX();
					int y1 = ent.getNode().getY();
					ArrayList<Node> rayTrace = tracer.getRayTrace(x0, x1, y0, y1, grid);
					boolean isLineOfSight = true;
					for(Node node : rayTrace) {
						System.out.println(node.getValue() + " x: " + node.getX() + " y: " + node.getY());
						if (isLineOfSight && node.getValue().equals("wall")) {
							isLineOfSight = false;
						}
					}
					System.out.println(isLineOfSight);
					pairs.add(new Pair(currentEntity, ent, isLineOfSight));
				}
			}
			checked.add(currentEntity);
		}
		return pairs;
	}
}
