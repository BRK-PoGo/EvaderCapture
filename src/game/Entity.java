package game;



public interface Entity {
	public Node getNode();
	public void moveToNode(Node node, Graph graph,String dir);
	public void setSpeed(int parseInt);
	public void setViewAngle(int parseInt);
	public int getSpeed();
	public void move(Graph graph);
	void setAlgorithm(Algorithm alg);
	public void setCapture(boolean isCaptured);
	public boolean getCapture();
	public Boolean isPursuer(Entity e);
	public int getRadius();
	public int getAngle();
	public void setDir (String dir);
	public String getDir();
	public void setViewSight(int parseInt);

}
