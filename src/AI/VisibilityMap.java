package AI;

import java.util.ArrayList;

import game.Graph;
import game.Node;
import logic.RayTracer;

public class VisibilityMap {
	private int[][] visibilityMatrix;
	

	public VisibilityMap(Graph graph) {
		visibilityMatrix = new int[graph.getNodeGrid().length][graph.getNodeGrid()[0].length];
		RayTracer tracer = new RayTracer();
		Node[][] grid = graph.getNodeGrid();
		for(int i=1;i<grid.length-1;i++){
			for(int j=1;j<grid[i].length-1;j++){
				for(int k=i;k<grid.length-1;k++){
					for(int l=j;l<grid[k].length-1;l++){
						if((i!=k || j != l) && (!grid[i][j].getValue().equals("wall")&!grid[k][l].getValue().equals("wall"))){
							ArrayList<Node> rayTrace = tracer.getRayTrace(i, k, j, l, grid);
							boolean isLineOfSight = true;
							for(Node node : rayTrace) {
								if (grid[node.getX()][node.getY()].getValue().equals("wall")) {
									isLineOfSight = false;
									break;
								}
							}
							if(isLineOfSight){
								visibilityMatrix[i][j]++;
								visibilityMatrix[k][l]++;
							}
						}
					}
				}
			}
		}
		System.out.println("done");

	}


	public int[][] getVisibilityMatrix() {
		return visibilityMatrix;
	}

}
