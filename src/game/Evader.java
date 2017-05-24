package game;

public class Evader implements Entity {

	private Node node;
	private int speed;////moves in 100 frames
	private int viewAngle;
	private Algorithm alg;
	private boolean isCaught = false;
	
	public Evader(Node node) {
		this.node = node;
		node.setValue("evader");
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
	@Override
	public void setViewAngle(int parseInt) {
		viewAngle = parseInt;	
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
	}
	
	public boolean getCapture() {
		return isCaught;
	}
}
