package game;

import java.io.Serializable;

public class SaverGraph implements Serializable{
	
	private String[][] copyGrid;
	
	public SaverGraph(Graph graph) {
		Node[][] nodeGrid = graph.getNodeGrid();
		copyGrid = new String[nodeGrid.length][nodeGrid[0].length];
		for (int x = 0; x < copyGrid.length; x++) {
			for (int y = 0; y < copyGrid[0].length; y++) {
				copyGrid[x][y] = nodeGrid[x][y].getValue();
			}
		}
	}

	public String[][] getGrid(){
		return copyGrid;
	}
}
