package game;

public class Random implements Algorithm{

	@Override
	public void move(Entity entity) {
		int random = (int) Math.floor(Math.random()*entity.getNode().getActiveNeighbors().size());
		Node node = entity.getNode().getActiveNeighbors().get((int)random);
		if(node.getValue()=="")
			entity.moveToNode(node);
		else {
			recursiveMove(entity,0);
		}
		
	}
	private void recursiveMove(Entity entity,int counter) {
		int random = (int) Math.floor(Math.random()*entity.getNode().getActiveNeighbors().size());
		Node node = entity.getNode().getActiveNeighbors().get((int)random);
		if(node.getValue()=="")
			entity.moveToNode(node);
		else if (counter<100){
			counter++;
			recursiveMove(entity,counter);
		}
		
	}

}
