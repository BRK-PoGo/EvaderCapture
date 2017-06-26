package AI;

import game.Entity;
import game.Graph;
import game.Node;
import game.Pursuer;
import logic.AngleChecker;
import logic.RadiusChecker;
import logic.RayTracer;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class VisibilityChecker {

	private double [][] visibilityMatrix;
	
	public  VisibilityChecker(Graph graph, Node nod, Entity ent, String dir) {
		//This method checks the visibility of one entities and stores them visibilityMatrix

		//ArrayList<Entity> toCheck = graph.getEntities();
		RayTracer tracer = new RayTracer();
		RadiusChecker radChecker = new RadiusChecker();
		AngleChecker angChecker = new AngleChecker();
		Node[][] grid = graph.getNodeGrid();
		visibilityMatrix = new double[grid.length][grid[0].length];
		for(int i=0;i<visibilityMatrix.length;i++)
			for(int j=0;j<visibilityMatrix[0].length;j++){
				if(grid[i][j].getValue().equals("wall")){
					visibilityMatrix[i][j]=-5;
				}
			}
		ArrayList<Pursuer> pursuers = new ArrayList<Pursuer>();
		for (int i = 0; i < graph.getEntities().size(); i++) {
			if(graph.getEntities().get(i) instanceof Pursuer){
				pursuers.add((Pursuer) graph.getEntities().get(i));
			}
		}
		for (int j = 1; j < grid.length-1; j++) {
			for (int k = 1; k < grid[0].length-1; k++) {
				if (j == nod.getY() && k == nod.getX()) visibilityMatrix[j][k] = 1;
				int x0 = nod.getX();
				int y0 = nod.getY();
				int x1 = k;
				int y1 = j;
				int vI = x0 - x1;
				int vJ = y0 - y1;
				int uI = 0;
				int uJ = 0;
				
				if (dir.equals("UP")) {
					uI = 1;
					uJ = 0;
				} else if (dir.equals("DOWN")) {
					uI = -1;
					uJ = 0;
				} else if (dir.equals("LEFT")) {
					uI = 0;
					uJ = 1;
				} else if (dir.equals("LEFT")) {
					uI = 0;
					uJ = -1;
				}

				ArrayList<Node> rayTrace = tracer.getRayTrace(x0, x1, y0, y1, grid);
				boolean rad = radChecker.RadiusCheck(x0, x1, y0, y1, ent.getRadius());
				boolean ang = angChecker.checkAngle(vI, vJ, uI, uJ, ent.getAngle()/2);
				boolean isLineOfSight = true;

				if (rad && ang) {
					for (Node node : rayTrace) {
						//System.out.println(node.getValue() + " x: " + node.getX() + " y: " + node.getY());
						if (node.getValue().equals("wall")) {
							isLineOfSight = false;
							//These are the locations of the non-visible nodes
							break;
						}
						//These are the locations of the visible
					}
					if(isLineOfSight) {
						visibilityMatrix[j][k] = 1;

					}
				}
			}
		}
		for(Pursuer p:pursuers){
		if(p!= ent){
			for (int j = 1; j < grid.length-1; j++) {
				for (int k = 1; k < grid[0].length-1; k++) {
					if (j == p.getNode().getY() && k == p.getNode().getX()) visibilityMatrix[j][k] = 1;
					int x0 = p.getNode().getX();
					int y0 = p.getNode().getY();
					int x1 = k;
					int y1 = j;
					int vI = x0 - x1;
					int vJ = y0 - y1;
					int uI = 0;
					int uJ = 0;
					
					if (p.getDir().equals("UP")) {
						uI = 1;
						uJ = 0;
					} else if (p.getDir().equals("DOWN")) {
						uI = -1;
						uJ = 0;
					} else if (p.getDir().equals("LEFT")) {
						uI = 0;
						uJ = 1;
					} else if (p.getDir().equals("LEFT")) {
						uI = 0;
						uJ = -1;
					}
	
					ArrayList<Node> rayTrace = tracer.getRayTrace(x0, x1, y0, y1, grid);
					boolean rad = radChecker.RadiusCheck(x0, x1, y0, y1, p.getRadius());
					boolean ang = angChecker.checkAngle(vI, vJ, uI, uJ, p.getAngle()/2);
					boolean isLineOfSight = true;
	
					if (rad && ang) {
						for (Node node : rayTrace) {
							//System.out.println(node.getValue() + " x: " + node.getX() + " y: " + node.getY());
							if (node.getValue().equals("wall")) {
								isLineOfSight = false;
								//These are the locations of the non-visible nodes
								break;
							}
							//These are the locations of the visible
						}
						if(isLineOfSight) {
							visibilityMatrix[j][k] = 1;
	
						}
					}
				}
			}
		}
		}
	}

	public double [][] getVisibilityMatrix(){return visibilityMatrix;};
}
