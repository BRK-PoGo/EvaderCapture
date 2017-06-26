package AI;
import game.Algorithm;
import game.Entity;
import game.Graph;
import game.Node;
import game.Pursuer;

import java.util.ArrayList;
/* I am a bit confused about the structure of the code. Shouldn't the algorithm perform on the game or the entities
* rather than entities having algorithms.
 */

/**
 * Created by Alexander on 30/05/2017.
 */
public class BeliefUpdater implements Algorithm {

    //THis should only check on one entity
	private Node previousMove = null;
    private Graph currentState;
    private Node bestMove;
    private String bestDir;
    private String pastDir;
    public boolean pathFind=false;
    private PathFinding pf;

    VisibilityChecker toCompare;
    SetEvaluator evaluator;
    int depth;

    public BeliefUpdater(Graph g, int depth){
    	this.depth = depth;
        currentState = g;
    }
    public void moveOLD(Entity entity){
    	boolean turn = false;
    	String bestDir = "";
    	//move depth 1
        //Current now contains an Arraylist of all visibilty matrices of all current entities
        if (entity instanceof Pursuer && Pursuer.getDirtyClean()==null){
            Pursuer.setDirtyClean(new double[currentState.getNodeGrid().length][currentState.getNodeGrid()[0].length]);
            for(int i=0;i<currentState.getNodeGrid().length;i++)
                for(int j=0;j<currentState.getNodeGrid()[0].length;j++){
                    if(currentState.getNodeGrid()[i][j].getValue().equals("wall")){
                        Pursuer.getDirtyClean()[i][j]=-5;
                    }

                }
        }
        double BestSum= Double.MIN_VALUE;
        ArrayList<Node> possMoves = entity.getNode().getActiveNeighbors();
        for (int i = 0; i < possMoves.size(); i++) {
            Node moveToCheck = possMoves.get(i);

            toCompare = new VisibilityChecker(currentState,moveToCheck,entity,entity.getDir());
            //evaluator=new SetEvaluator(toCompare);
            //xevaluator.evaluateDirtyClean(entity.getDirtyClean());
            if (evaluator.getSumOfDirtyClean() >= BestSum) {
                BestSum=evaluator.getSumOfDirtyClean();
                bestMove=possMoves.get(i);
            }
        }
        /*
        for (int i = 0; i < 4; i++) {
        	if (i == 0) entity.setDir("UP");
			else if (i == 1) entity.setDir("DOWN");
			else if (i == 2) entity.setDir("LEFT");
			else if (i == 3) entity.setDir("RIGHT");
            toCompare = new VisibilityChecker(currentState,entity.getNode(),entity);
            evaluator=new SetEvaluator(toCompare);
            evaluator.evaluateDirtyClean(entity.getDirtyClean());
            if (evaluator.getSumOfDirtyClean() >= BestSum) {
                BestSum=evaluator.getSumOfDirtyClean();
                //bestMove=possMoves.get(i);
                turn = true;
                bestDir = entity.getDir();
            }
        }
        */
//        if(previousMove != null)
//        	System.out.println("x: "+previousMove.getY()+" y: "+previousMove.getX());
        
        if((previousMove==null || bestMove != previousMove) && !turn){
        	previousMove = entity.getNode();
        	//entity.moveToNode(bestMove,currentState);
        } else if (previousMove==null || turn) {
        	entity.setDir(bestDir);
        }
        else{
        	
        	
            Node temp = entity.getNode();
            Random r = new Random(currentState);
            r.move(entity,previousMove);
            previousMove = temp;
        }
        if(previousMove == null){
        	previousMove= entity.getNode(); //update previous
        	//firtsIteration=false;
        } 
        
   
        

    }
    public void move(Entity entity) {
    	
    	//move depth "depth"
        //Current now contains an Arraylist of all visibilty matrices of all current entities
        if (entity instanceof Pursuer && Pursuer.getDirtyClean()==null){
            Pursuer.setDirtyClean(new double[currentState.getNodeGrid().length][currentState.getNodeGrid()[0].length]);
            for(int i=0;i<currentState.getNodeGrid().length;i++)
                for(int j=0;j<currentState.getNodeGrid()[0].length;j++){
                    if(currentState.getNodeGrid()[i][j].getValue().equals("wall")){
                        Pursuer.getDirtyClean()[i][j]=-5;
                    }

                }
        }
        if(!pathFind){
	        double BestSum= Double.MIN_VALUE;
	        Tree tree = new Tree((Pursuer)entity,currentState,depth);
	        ArrayList<Leaf> possMoves = tree.getLevel(1);
	        for (int i = 0; i < possMoves.size(); i++) {
	            Leaf leaf = possMoves.get(i);
	            leaf.evaluate(currentState,entity);
	            if (leaf.getValue() >= BestSum) {
	                BestSum=leaf.getValue();
	                bestMove=leaf.getNode();
	                bestDir = leaf.getDir();
	            }
	        }
        }
        
        
        if(!pathFind && (previousMove==null || bestMove != previousMove || !bestDir.equals(pastDir))){
        	previousMove = entity.getNode();
        	pastDir = entity.getDir();
        	entity.moveToNode(bestMove,currentState, bestDir);
        }
        else{
//        	if(!pathFind){
//        		//find node to visit
//        		VisibilityMap map = currentState.getVisibilityMap();
//        		int[] worst = map.getWorst(entity);
//        		pathFind=true;
//        		pf = new PathFinding(entity, currentState,worst);
//        	}else{
//        		if(pf.hasNextNod()){// && Pursuer.getDirtyClean()[pf.eNode.getY()][pf.eNode.getX()]<0.1){
//        			entity.moveToNode(pf.removeNextNode(), currentState, entity.getDir());
//        		}else{
//        			pathFind = false;
//        		}
//        	}
////        	  	
            Node temp = entity.getNode();
            Random r = new Random(currentState);
            r.move(entity,previousMove);
            previousMove = temp;
        }
        if(previousMove == null){
        	previousMove= entity.getNode(); //update previous of 1st iteration
        } 
   

    }
    public void setCurrentState(Graph g){currentState=g;}

    public int sumOfElements (int [][] matrix){
        int sum = 0;
        for (int i = 0; i<matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(matrix[i][j]>0)
                    sum+=matrix[i][j];
            }
        }
        return sum;
    }
	@Override
	public boolean getPathFind() {
		return pathFind;
		
	}

}
