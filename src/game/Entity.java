package game;

public interface Entity {
	public Node getNode();
	public void moveToNode(Node node);
	public void setSpeed(int parseInt);
	public void setViewAngle(int parseInt);
	public int getSpeed();
	public void move();
	void setAlgorithm(Algorithm alg);

}
