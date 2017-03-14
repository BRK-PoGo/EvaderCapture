package game;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
 	

public class GamePanel extends JPanel {
	Grid grid = null;
	String edit = "";
//	ArrayList<Entity> entities;
	public GamePanel() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent m) {
				if(grid != null){
					if(edit.equals("wall")){			//mouse listener for the drawer
						grid.addWall(m.getX()/grid.size, m.getY()/grid.size);
					}else if (edit.equals("delete")){
						grid.makeEmpty(m.getX()/grid.size, m.getY()/grid.size);
					}else if (edit.equals("pursuiter")){
						//grid.addPurs(m.getX()/grid.size, m.getY()/grid.size);
					}else if (edit.equals("elacing Evader")){
						//grid.addEvader(m.getX()/grid.size, m.getY()/grid.size);
						}
				}
			}
		});
	
	}
	public void setGrid(Grid grid){
		this.grid = grid;
	}
	@Override
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//paint grid
		if(grid != null && grid.isVisible()){
			g.setColor(Color.gray);
			for(int i=0; i<grid.getNodeGrid().length; i++) {
	            for (int j=0; j<grid.getNodeGrid()[i].length; j++) {
	            	g.drawRect(grid.getNodeGrid()[i][j].rectangle.x, grid.getNodeGrid()[i][j].rectangle.y, grid.getNodeGrid()[i][j].rectangle.width, grid.getNodeGrid()[i][j].rectangle.height);
	            	if(grid.getNodeGrid()[i][j].value.equals("wall")){
	            		g.setColor(Color.darkGray);
	            		g.fillRect(grid.getNodeGrid()[i][j].rectangle.x, grid.getNodeGrid()[i][j].rectangle.y, grid.getNodeGrid()[i][j].rectangle.width, grid.getNodeGrid()[i][j].rectangle.height);
	            		g.setColor(Color.gray);
	            	}
	            }
			}
		}
	}
	public void setEdit(String edit) {
		this.edit = edit;
	}
	

}
