package game;

public class Evader implements Entity {

	private Node node;
	private int speed;////moves in 100 frames
	private Algorithm alg;
	private boolean isCaught = false;
	private int [][] visibilityMatrix;
	private Boolean isPursuer = false;

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
	public void moveToNode(Node node) {
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
	
	public int[][] getVisibility() {
		return visibilityMatrix;
	}

	public void setVisibility(int[][] visibility) {
		this.visibilityMatrix=visibility;
	}
	public Boolean isPursuer(Entity e){
		return isPursuer;
	}
	@Override
	public int[][] getDirtyClean() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDirtyClean(int[][] dirtyClean) {
		// TODO Auto-generated method stub
		
	}
}
