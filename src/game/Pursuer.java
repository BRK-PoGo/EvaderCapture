package game;

import AI.SetEvaluator;
import AI.VisibilityChecker;

public class Pursuer implements Entity{

	private Node node;
	private int speed;//moves in 100 frames
	private int viewAngle;
	private Algorithm alg;
	private boolean isCaught = false;
	private double [][] dirtyClearMatrix;
	private boolean isPursuer = true;
	private final int SIGHT_RAD = 10;
	private final int SIGHT_ANG = 360;

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
	public void moveToNode(Node node, Graph graph) {
		this.node.setValue("");
		this.node = node;
		this.node.setValue("pursuer");
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
		SetEvaluator evaluator;
		toCompare = new VisibilityChecker();
        toCompare.checkEntitiesCurrent(graph,node);
        evaluator=new SetEvaluator(toCompare);
        evaluator.evaluateDirtyClean(this.getDirtyClean());
        this.setDirtyClean(evaluator.getDirtyClean());
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
	public void move() {
		alg.move(this);
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
		return SIGHT_ANG;
	}
}
