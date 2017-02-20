package gui;

import java.awt.Color;
import java.awt.Graphics;

public class DrawDesignPanel {

	final int SIZE = 40;
	final int OFF_SET = 10;
	Color[][] grid;
	
	public void setGrid(Color[][] grid) {
		this.grid = grid;
	}

	public void draw(Graphics g) {
		for (int x = 0; x < 10; x++) {
			for (int y = 0; y < 10; y++) {
				g.setColor(Color.GRAY);
				g.fillRect(OFF_SET + (SIZE*x), OFF_SET + (SIZE*y), SIZE, SIZE);
				g.setColor(Color.BLACK);
				g.drawRect(OFF_SET + (SIZE*x), OFF_SET + (SIZE*y), SIZE, SIZE);
			}
		}
	}
}
