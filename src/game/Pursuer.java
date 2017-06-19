package game;

import java.util.ArrayList;

import logic.RadiusChecker;
import logic.RayTracer;

public class Pursuer implements Entity{

	private Node node;
	private int speed;//moves in 100 frames
	private int viewAngle;
	private Algorithm alg;
	private boolean isCaught = false;
	private int [][] dirtyClearMatrix;
	private boolean isPursuer = true;
	private final int SIGHT_RAD = 5;
	private final int SIGHT_ANG = 360;
	private RadiusChecker radChecker = new RadiusChecker();
	private RayTracer lineChecker = new RayTracer();

	public Pursuer(Node node) {
		this.node = node;
		node.setValue("pursuer");
	}
	public Pursuer(Node node, int sp, Algorithm alg) {
		this.alg = alg;
		this.node = node;
		node.setValue("pursuer");
		speed = sp;
	}
	@Override
	public Node getNode() {
		return node;
	}
	public void moveToNode(Node node) {
		this.node.setValue("");
		this.node = node;
		this.node.setValue("pursuer");
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
		setVision(graph);
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

	@Override
	public int[][] getDirtyClean() {
		return dirtyClearMatrix;
	}

	@Override
	public void setDirtyClean(int[][] dirtyClearMatrix) {
		this.dirtyClearMatrix=dirtyClearMatrix;
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
		return SIGHT_ANG;
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
		
		Node[][] grid = graph.getNodeGrid();
		for (int x = node.getX() - SIGHT_RAD; x <= node.getX() + SIGHT_RAD; x++) {
			for (int y = node.getY() - SIGHT_RAD; y <= node.getY() + SIGHT_RAD; y++) {
				if (x >= 0 && x < graph.getNodeGrid()[0].length && y >= 0 && y < graph.getNodeGrid().length) {
					grid[y][x].setVision(radChecker.RadiusCheck(node.getX(), x, node.getY(), y, SIGHT_RAD));
				}
			}
		}
		
	}
}
