package AI;

import java.util.ArrayList;

import game.Graph;
import game.Node;
import logic.RayTracer;

public class VisibilityMap {
	private int[][] visibilityMatrix;
	private Graph graph;
	

	public VisibilityMap(Graph graph) {
		this.graph = graph;
		visibilityMatrix = new int[graph.getNodeGrid().length][graph.getNodeGrid()[0].length];
		visibilityMatrix[0][0] = Integer.MAX_VALUE;
		RayTracer tracer = new RayTracer();
		Node[][] grid = graph.getNodeGrid();
		for(int i=1;i<grid.length-1;i++){
			for(int j=1;j<grid[i].length-1;j++){
				for(int k=1;k<grid.length-1;k++){
					for(int l=1;l<grid[k].length-1;l++){
						if((i<k || (i == k && j<l)) && (!grid[i][j].getValue().equals("wall")&!grid[k][l].getValue().equals("wall"))){
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


	public int[][] getBest(int n) {
		//return the "n" best position on the map (lower visibility)
		int[][]result= new int[n][2];
		for(int k=0;k<n;k++){
		
			for(int i=0;i<visibilityMatrix.length;i++){
				for(int j=0;j<visibilityMatrix[i].length;j++){
					if((!graph.getNodeGrid()[i][j].getValue().equals("wall"))&& visibilityMatrix[i][j]<visibilityMatrix[result[k][0]][result[k][1]]){
						boolean alreadyChosen = false;
						for(int k2=0;k2<k;k2++){
							if(result[k2][0]== i && result[k2][1]== j){
								alreadyChosen=true;
								break;
							}
						}
						if(!alreadyChosen){
							result[k][0]=i;
							result[k][1]=j;
						}
					}
				}
			}
		}
		return result;
	}

}
