package game;
import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

import AI.VisibilityMap;



public class Graph {

    private Node[][] nodeGrid;
    public int size;//size of node
    private ArrayList<Node> nodes = new ArrayList<Node>();
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Evader> evaders = new ArrayList<Evader>();
	private VisibilityMap visibilityMap;

    public Graph(int h, int w,int size){
    	this.size=size;
        nodeGrid = new Node[h][w];
     
// 			     Create Grid.
         
        for(int i=0; i<h; i++) {
            for (int j=0; j<w; j++) {
            	nodeGrid[i][j] = new Node(j, i);
                nodeGrid[i][j].rectangle = new Rectangle(size*i,size*j, size, size);
                addNode(nodeGrid[i][j]);// addd all nodes
            }
        } for(int i=0; i<h; i++) {//add neighbors
            for (int j=0; j<w; j++) {
		        if(i>0)
		        	nodeGrid[i][j].addNeighbor(nodeGrid[i-1][j]);
		        if(i<nodeGrid.length-1)
		        	nodeGrid[i][j].addNeighbor(nodeGrid[i+1][j]);
		        if(j>0)
		            nodeGrid[i][j].addNeighbor(nodeGrid[i][j-1]);
		        if(j<nodeGrid[i].length-1){
		              	nodeGrid[i][j].addNeighbor(nodeGrid[i][j+1]);
		        }
    		}
		}
    }
	public void addWall(int i, int j) {//i and j are coordinates
		nodeGrid[i][j].removeAllNeighbors();
		nodeGrid[i][j].setValue("wall");
	}
	public void makeEmpty(int i, int j) {
		//make node i j empty (i.e. no walls, no people.)
		if( nodeGrid[i][j].getValue().equals("evader")|| nodeGrid[i][j].getValue().equals("pursuer")){
			Entity remove = null;
			for(Entity ent:entities){
				if(ent.getNode()== nodeGrid[i][j]){
					remove=ent;
				}
			}
			if(remove !=null)
				entities.remove(remove);
		}
        nodeGrid[i][j].setValue("");
        nodeGrid[i][j].restoreNeighbors();
        
		
	}
	public Node[][] getNodeGrid() {
		
		return nodeGrid;
	}
	public void addPurs(int i, int j) {
		Node node = nodeGrid[i][j];
		if(node.getValue().equals("")){
			addEntity(new Pursuer(node));
		}
	
		
	}
	public void addEvader(int i, int j) {
		if(nodeGrid[i][j].getValue().equals("")){
			Evader temp = new Evader(nodeGrid[i][j]);
			addEntity(temp);
			evaders.add(temp);
		}
	}
	public void addNode(Node node) {
		nodes.add(node);
		
	}
	public void addEntity(Entity entity) {
		entities.add(entity);
	}
	public ArrayList<Entity> getEntities() {
		return entities;
	}
	public ArrayList<Evader> getEvaders() {
		return evaders;
	}
	public void setDirtyClean(double[][] dirtyClean) {
		for(int i=0;i<nodeGrid.length;i++)
		{
			for(int j=0;j<nodeGrid[0].length;j++){
				nodeGrid[i][j].setDirtyCleanValue(dirtyClean[i][j]);
			}
			
		}
		
	}
	public VisibilityMap getVisibilityMap() {
		return visibilityMap;
	}
	public void setVisibilityMap(VisibilityMap visibilityMap2) {
		visibilityMap = visibilityMap2;
	}
}