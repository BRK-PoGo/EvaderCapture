package game;

import AI.SetEvaluator;
import AI.VisibilityChecker;

public class Evader implements Entity {

	private Node node;
	private int speed;////moves in 100 frames
	private Algorithm alg;
	private boolean isCaught = false;
	private int [][] dirtyCleanMatrix;
	private Boolean isPursuer = false;
	private final int SIGHT_RAD = 10;
	private final int SIGHT_ANG = 360;

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
	public void moveToNode(Node node, Graph graph) {
		this.node.setValue("");
		this.node = node;
		this.node.setValue("evader");
		VisibilityChecker toCompare;
		SetEvaluator evaluator;
		toCompare = new VisibilityChecker();
        toCompare.checkEntitiesCurrent(graph,node);
        evaluator=new SetEvaluator(toCompare);
        evaluator.evaluateDirtyClean(this.getDirtyClean());
        this.setDirtyClean(evaluator.getDirtyClean());
		
	}
	@Override
	public void setSpeed(int parseInt) {
			speed= parseInt;
		
	}
	public int getSpeed() {
		return speed;
	}
	@Override
	public void move() {
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
	@Override
	public int[][] getDirtyClean() {
		return dirtyCleanMatrix;
	}
	
	public void setDirtyClean(int[][] dirtyCleanMatrix) {
		this.dirtyCleanMatrix = dirtyCleanMatrix;
		
	}
	@Override
	public int getRadius() {
		return SIGHT_RAD;
	}
	@Override
	public int getAngle() {
		return SIGHT_ANG;
	}
}
