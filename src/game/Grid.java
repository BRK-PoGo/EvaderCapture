package game;


import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;



public class Grid extends JComponent {

    private Node[][] nodeGrid;
    private ArrayList<Node> graph = new ArrayList<Node>();
    public int size;

    public Grid(int h, int w,int size){
    	this.size=size;
        nodeGrid = new Node[h][w];


        for(int i=0; i<h; i++) {
            for (int j=0; j<w; j++) {
            	nodeGrid[i][j] = new Node();
                nodeGrid[i][j].rectangle = new Rectangle(size*i,size*j, size, size);
                graph.add(nodeGrid[i][j]);
                if(i>0)
                	nodeGrid[i][j].addAdjacentNode(nodeGrid[i-1][j]);
                if(i<nodeGrid.length-1)
                	nodeGrid[i][j].addAdjacentNode(nodeGrid[i+1][j]);
                if(j>0)
                    nodeGrid[i][j].addAdjacentNode(nodeGrid[i][j-1]);
                if(j<nodeGrid[i].length-1){
                      	nodeGrid[i][j].addAdjacentNode(nodeGrid[i][j+1]);
                }
            }
        }
    }
   
	public void addWall(int i, int j) {//i and j are coordinates
		nodeGrid[i][j].deleteAllAdjacentNodes();
		nodeGrid[i][j].value = "wall";
		repaint();
		
		
	}
	public void makeEmpty(int i, int j) {
		if(i>0 && !nodeGrid[i-1][j].value.equals("wall"))
        	nodeGrid[i][j].addAdjacentNode(nodeGrid[i-1][j]);
        if(i<nodeGrid.length-1 && !nodeGrid[i+1][j].value.equals("wall"))
        	nodeGrid[i][j].addAdjacentNode(nodeGrid[i+1][j]);
        if(j>0 && !nodeGrid[i][j-1].value.equals("wall"))
            nodeGrid[i][j].addAdjacentNode(nodeGrid[i][j-1]);
        if(j<nodeGrid[i].length-1 && !nodeGrid[i][j+1].value.equals("wall")){
              	nodeGrid[i][j].addAdjacentNode(nodeGrid[i][j+1]);
        }
        nodeGrid[i][j].value = "";
		
	}

	public Node[][] getNodeGrid() {
		
		return nodeGrid;
	}

}