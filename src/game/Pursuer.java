package game;

public class Pursuer implements Entity{

	private Node node;
	private int speed;//moves in 100 frames
	private int viewAngle;
	private Algorithm alg;
	private boolean isCaught = false;
	private int [][] dirtyClearMatrix;
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
}
