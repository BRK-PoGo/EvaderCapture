package logic;

import game.Entity;

public class Pair { // Written by Tom

	private Entity entity1;
	private Entity entity2;
	private boolean lineOfSight;
	
	public Pair(Entity entity1, Entity entity2, boolean lineOfSight) {
		this.entity1 = entity1;
		this.entity2 = entity2;
		this.lineOfSight = lineOfSight;
	}
	
	public Entity getEntity1() {
		return entity1;
	}
	
	public Entity getEntity2() {
		return entity2;
	}
	
	public boolean getLineOfSight() {
		return lineOfSight;
	}
}
