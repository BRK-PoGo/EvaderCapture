package game;

public interface Entity {
	public Node getNode();
	public void moveToNode(Node node, Graph graph);
	public void setSpeed(int parseInt);
	public void setViewAngle(int parseInt);
	public int getSpeed();
	public void move();
	void setAlgorithm(Algorithm alg);
	public void setCapture(boolean isCaptured);
	public boolean getCapture();
	public int [][] getDirtyClean();
	public void setDirtyClean(int [][] dirtyClean);
	public Boolean isPursuer(Entity e);
	public int getRadius();
	public int getAngle();
}
