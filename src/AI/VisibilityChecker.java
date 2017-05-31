package AI;

import game.Entity;
import game.Graph;
import game.Node;
import logic.RayTracer;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class VisibilityChecker {

	private int [][] visibilityMatrix;
	
	public void checkEntitiesCurrent(Graph graph, Node nod) {
		//This method checks the visibility of one entities and stores them visibilityMatrix

		//ArrayList<Entity> toCheck = graph.getEntities();
		RayTracer tracer = new RayTracer();
		Node[][] grid = graph.getNodeGrid();
		int[][] visibilityMatrix = new int[grid.length][grid[0].length];

		//for (int i = 0; i < toCheck.size(); i++) {
		for (int j = 0; j < grid.length; j++) {
			for (int k = 0; k < grid[0].length; k++) {
				int x0 = nod.getX();
				int y0 = nod.getY();
				int x1 = grid[j][k].getX();
				int y1 = grid[j][k].getY();
				ArrayList<Node> rayTrace = tracer.getRayTrace(x0, x1, y0, y1, grid);
				boolean isLineOfSight = true;

				for (Node node : rayTrace) {
					System.out.println(node.getValue() + " x: " + node.getX() + " y: " + node.getY());
					if (isLineOfSight && node.getValue().equals("wall")) {
						isLineOfSight = false;
						//These are the locations of the non-visible nodes
						visibilityMatrix[j][k] = 0;
					}
					//These are the locations of the visible
					else {
						visibilityMatrix[j][k] = 1;

					}
					visibilityMatrix[node.getX()][node.getY()] = -5;
				}
			}
		}
		this.visibilityMatrix=visibilityMatrix;
	}

	public int [][] getVisibilityMatrix(){return visibilityMatrix;};
}
