package gamePlay;

import java.awt.Color;

public class Grid {

	Color[][] grid = new Color[10][10];
	
	public Grid() {
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid[x].length; y++ ) {
				grid[x][y] = Color.GRAY;
			}
		}
	}
	
	public void updateGrid(int x, int y) {
		grid[x][y] = Color.RED;
	}
	
	public Color[][] getGrid() {
		return grid;
	}
}
