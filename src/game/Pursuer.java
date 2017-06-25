package game;


import java.util.ArrayList;

import logic.AngleChecker;
import logic.RadiusChecker;
import logic.RayTracer;
import AI.ProbSetEvaluator;
import AI.SetEvaluator;
import AI.VisibilityChecker;


public class Pursuer implements Entity {

	private Node node;
	private int speed;//moves in 100 frames
	private int viewAngle;
	private Algorithm alg;
	private boolean isCaught = false;
	private double [][] dirtyClearMatrix;
	private boolean isPursuer = true;
	private  int SIGHT_RAD;
	private RadiusChecker radChecker = new RadiusChecker();
	private AngleChecker angChecker = new AngleChecker();
	private RayTracer lineChecker = new RayTracer();
	private String dir = "UP";

	public Pursuer(Node node) {
		this.node = node;
		
		node.setValue("pursuer");
		
	}
	public Pursuer(Node node, int sp,int rad, int ang, Algorithm alg) {
		this.alg = alg;
		this.node = node;
		node.setValue("pursuer");
		
		speed = sp;
	}
	@Override
	public Node getNode() {
		return node;
	}
	public void moveToNode(Node node, Graph graph, String dir) {
		this.node.setValue("");
		this.node = node;
		this.node.setValue("pursuer");
		this.dir = dir;
		if (this.getDirtyClean()==null){
            this.setDirtyClean(new double[graph.getNodeGrid().length][graph.getNodeGrid()[0].length]);
            for(int i=0;i<graph.getNodeGrid().length;i++)
                for(int j=0;j<graph.getNodeGrid()[0].length;j++){
                    if(graph.getNodeGrid()[i][j].getValue().equals("wall")){
                        this.getDirtyClean()[i][j]=-5;
                    }

                }
        }

		VisibilityChecker toCompare;
		ProbSetEvaluator evaluator;
		toCompare = new VisibilityChecker(graph,node, this,this.dir);
        evaluator=new ProbSetEvaluator(toCompare);
        evaluator.evaluateDirtyClean(this.getDirtyClean(),this.getNode().getY(),this.getNode().getX());
        this.setDirtyClean(evaluator.getDirtyClean());
        graph.setDirtyClean(this.getDirtyClean());
	}
	@Override
	public void setSpeed(int parseInt) {
		speed = parseInt;
		
	}
	@Override
	public void setViewAngle(int parseInt) {

		viewAngle = parseInt;
		
	}
	public int getSpeed() {
		return speed;
	}
	@Override
	public void move(Graph graph) {
		resetVision(graph);
		alg.move(this);
		if (dir.equals("UP")) graph.getNodeGrid()[node.getY()][node.getX() - 1].setVision(true);
		else if (dir.equals("DOWN")) graph.getNodeGrid()[node.getY()][node.getX() + 1].setVision(true);
		else if (dir.equals("LEFT")) graph.getNodeGrid()[node.getY() - 1][node.getX()].setVision(true);
		else if (dir.equals("RIGHT")) graph.getNodeGrid()[node.getY() + 1][node.getX()].setVision(true);
		//setVision(graph);
	}
	@Override
	public void setAlgorithm(Algorithm alg) {
		this.alg = alg;
	}
	@Override
	public void setCapture(boolean isCaptured) {
		this.isCaught = isCaptured;
	}
	
	public boolean getCapture() {
		return isCaught;
	}

	
	public double[][] getDirtyClean() {
		return dirtyClearMatrix;
	}

	
	public void setDirtyClean(double[][] ds) {
		this.dirtyClearMatrix=ds;
	}
	public Boolean isPursuer(Entity e){
		return isPursuer;
	}
	@Override
	public int getRadius() {
		return SIGHT_RAD;
	}
	@Override
	public int getAngle() {
		return viewAngle;
	}
	
	private void resetVision(Graph graph) {
		Node[][] grid = graph.getNodeGrid();
		for (int x = node.getX() - SIGHT_RAD; x <= node.getX() + SIGHT_RAD; x++) {
			for (int y = node.getY() - SIGHT_RAD; y <= node.getY() + SIGHT_RAD; y++) {
				if (x >= 0 && x < graph.getNodeGrid()[0].length && y >= 0 && y < graph.getNodeGrid().length) {
					grid[y][x].setVision(false);
				}
			}
		}
	}
	
	private void setVision(Graph graph) {
		//TODO Fix, its a mess
		Node[][] grid = graph.getNodeGrid();
		for (int x = node.getX() - SIGHT_RAD; x <= node.getX() + SIGHT_RAD; x++) {
			for (int y = node.getY() - SIGHT_RAD; y <= node.getY() + SIGHT_RAD; y++) {
				if (x >= 0 && x < graph.getNodeGrid()[0].length && y >= 0 && y < graph.getNodeGrid().length) {
					int x0 = node.getX();
					int y0 = node.getY();
					int x1 = x;
					int y1 = y;
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
					
					ArrayList<Node> rayTrace = lineChecker.getRayTrace(x0, x1, y0, y1, grid);
					boolean line = true;
					for(Node node : rayTrace) {
						if (line && node.getValue().equals("wall")) {
							line = false;
						}
					}
					boolean rad = radChecker.RadiusCheck(x0, x1, y0, y1, SIGHT_RAD);
					boolean ang = angChecker.checkAngle(vI, vJ, uI, uJ, viewAngle/2);
					if (line && rad && ang) grid[y][x].setVision(true);
				}
			}
		}
	}
	
	public void setDir (String dir) {
		this.dir = dir;
	}
	
	public String getDir() {
		return dir;
	}
	@Override
	public void setViewSight(int parseInt) {
		SIGHT_RAD=parseInt;
		
	}
	public double[][] getDirtyClearMatrix() {
		return dirtyClearMatrix;
	}
	
	

}
