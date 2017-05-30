package game;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
 	

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
	Graph graph = null;
	private String edit = "";//variable for adding elements: wall, when adding walls ecc...
	boolean dragAdd = false;//when true, will add elements on drag, according to the "edit" variable
	
	public GamePanel() {
		addMouseMotionListener(new MouseMotionAdapter() {

			@Override
			public void mouseDragged(MouseEvent m) {
				if(dragAdd && (m.getX()/graph.size)<graph.getNodeGrid().length&& (m.getX()/graph.size)>=0 &&  (m.getY()/graph.size)<graph.getNodeGrid()[0].length &&  (m.getY()/graph.size)>=0){
					if(edit.equals("wall")&& graph.getNodeGrid()[(m.getX()/graph.size)] [m.getY()/graph.size].getValue() == ""){										
						graph.addWall(m.getX()/graph.size, m.getY()/graph.size);
					}else if (edit.equals("delete")){
						graph.makeEmpty(m.getX()/graph.size, m.getY()/graph.size);
					}
					repaint();
				}
			}
		});
		addMouseListener(new MouseAdapter() {//mouse listener for the drawer
			@Override
			public void mousePressed(MouseEvent m) {
				if(graph != null && !edit.equals("") && (m.getX()/graph.size)<graph.getNodeGrid().length&& (m.getX()/graph.size)>=0 &&  (m.getY()/graph.size)<graph.getNodeGrid()[0].length &&  (m.getY()/graph.size)>=0){
					if(edit.equals("wall")){	
						dragAdd = true;														//Transform the node on which you clicked 
						if(graph.getNodeGrid()[(m.getX()/graph.size)] [m.getY()/graph.size].getValue() == "")
						graph.addWall(m.getX()/graph.size, m.getY()/graph.size);
					}else if (edit.equals("delete")){
						dragAdd = true;
						graph.makeEmpty(m.getX()/graph.size, m.getY()/graph.size);
					}else if (edit.equals("pursuiter") && graph.getNodeGrid()[(m.getX()/graph.size)] [m.getY()/graph.size].getValue() == ""){
						graph.addPurs(m.getX()/graph.size, m.getY()/graph.size);
					}else if (edit.equals("evader") && graph.getNodeGrid()[(m.getX()/graph.size)] [m.getY()/graph.size].getValue() == ""){
						graph.addEvader(m.getX()/graph.size, m.getY()/graph.size);
					}
						repaint();												
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				dragAdd = false;

			}
		});
	
	}
	public void setGraph(Graph grid){
		this.graph = grid;
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		//paint graph
		if(graph != null ){
			
			g.setColor(Color.gray);
			for(int i=0; i<graph.getNodeGrid().length; i++) {
	            for (int j=0; j<graph.getNodeGrid()[i].length; j++) {
	            	g.drawRect(graph.getNodeGrid()[i][j].rectangle.x, graph.getNodeGrid()[i][j].rectangle.y, graph.getNodeGrid()[i][j].rectangle.width, graph.getNodeGrid()[i][j].rectangle.height);
	            	if(graph.getNodeGrid()[i][j].getValue().equals("wall")){
	            		g.setColor(Color.darkGray);
	            		g.fillRect(graph.getNodeGrid()[i][j].rectangle.x, graph.getNodeGrid()[i][j].rectangle.y, graph.getNodeGrid()[i][j].rectangle.width, graph.getNodeGrid()[i][j].rectangle.height);
	            		g.setColor(Color.gray);
	            	}else if(graph.getNodeGrid()[i][j].getValue().equals("pursuer")){
	            		int delta = graph.getNodeGrid()[i][j].rectangle.height/5;
	            		g.setColor(Color.blue);
	            		g.fillOval(graph.getNodeGrid()[i][j].rectangle.x+delta/2, graph.getNodeGrid()[i][j].rectangle.y+delta/2, graph.getNodeGrid()[i][j].rectangle.width-delta, graph.getNodeGrid()[i][j].rectangle.height-delta);
	            		g.setColor(Color.gray);
	            	}else if(graph.getNodeGrid()[i][j].getValue().equals("evader")){
	            		int delta = graph.getNodeGrid()[i][j].rectangle.height/5;
	            		g.setColor(Color.red);
	            		g.fillOval(graph.getNodeGrid()[i][j].rectangle.x+delta/2, graph.getNodeGrid()[i][j].rectangle.y+delta/2, graph.getNodeGrid()[i][j].rectangle.width-delta, graph.getNodeGrid()[i][j].rectangle.height-delta);
	            		g.setColor(Color.gray);
	            	} else if(graph.getNodeGrid()[i][j].getValue().equals("evaderCaptured")){
	            		int delta = graph.getNodeGrid()[i][j].rectangle.height/5;
	            		g.setColor(Color.green);
	            		g.fillOval(graph.getNodeGrid()[i][j].rectangle.x+delta/2, graph.getNodeGrid()[i][j].rectangle.y+delta/2, graph.getNodeGrid()[i][j].rectangle.width-delta, graph.getNodeGrid()[i][j].rectangle.height-delta);
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
