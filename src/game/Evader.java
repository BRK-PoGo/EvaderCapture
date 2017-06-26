package game;

import AI.SetEvaluator;
import AI.VisibilityChecker;

public class Evader implements Entity {

	private Node node;
	private int speed;////moves in 100 frames
	private Algorithm alg;
	private boolean isCaught = false;
	private Boolean isPursuer = false;
	private final int SIGHT_RAD = 10;
	private final int SIGHT_ANG = 360;
	private String dir = "UP";

	public Evader(Node node) {
		this.node = node;
		node.setValue("evader");
	}
	public Evader(Node node, int sp, Algorithm alg) {
		this.alg = alg;
		this.node = node;
		node.setValue("evader");
		speed = sp;
		}
	@Override
	public Node getNode() {
		return node;
	}
	public void moveToNode(Node node, Graph graph, String dir) {
		this.node.setValue("");
		this.node = node;
		this.node.setValue("evader");
		
	}
	@Override
	public void setSpeed(int parseInt) {
			speed= parseInt;
		
	}
	public int getSpeed() {
		return speed;
	}
	@Override
	public void move(Graph graph) {
		if (!isCaught) alg.move(this);
		
	}

	public void setAlgorithm(Algorithm alg) {
		this.alg = alg;
	}
	
	public void setCapture(boolean isCaptured) {
		this.isCaught = isCaptured;
		node.setValue("evaderCaptured");
	}
	
	public boolean getCapture() {
		return isCaught;
	}
	public void setViewAngle(int parseInt) {
		//Irrelevant
		
	}
	public Boolean isPursuer(Entity e){
		return isPursuer;
	}
	
	public int getRadius() {
		return SIGHT_RAD;
	}
	@Override
	public int getAngle() {
		return SIGHT_ANG;
	}
	
	public void setDir (String dir) {
		this.dir = dir;
	}
	
	public String getDir() {
		return dir;
	}
	@Override
	public void setViewSight(int parseInt) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public Algorithm getAlgorithm() {
		return alg;
	}
}
