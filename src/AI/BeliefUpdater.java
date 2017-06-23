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

    VisibilityChecker toCompare;
    SetEvaluator evaluator;
    int depth;

    public BeliefUpdater(Graph g, int depth){
    	this.depth = depth;
        currentState = g;
    }
    public void moveOLD(Entity entity){
    	//move depth 1
        //Current now contains an Arraylist of all visibilty matrices of all current entities
        if (entity instanceof Pursuer && ((Pursuer) entity).getDirtyClean()==null){
            ((Pursuer) entity).setDirtyClean(new double[currentState.getNodeGrid().length][currentState.getNodeGrid()[0].length]);
            for(int i=0;i<currentState.getNodeGrid().length;i++)
                for(int j=0;j<currentState.getNodeGrid()[0].length;j++){
                    if(currentState.getNodeGrid()[i][j].getValue().equals("wall")){
                        ((Pursuer) entity).getDirtyClean()[i][j]=-5;
                    }

                }
        }
        double BestSum= Double.MIN_VALUE;
        ArrayList<Node> possMoves = entity.getNode().getActiveNeighbors();
        for (int i = 0; i < possMoves.size(); i++) {
            Node moveToCheck = possMoves.get(i);

            toCompare = new VisibilityChecker(currentState,moveToCheck,entity);
            evaluator=new SetEvaluator(toCompare);
            //xevaluator.evaluateDirtyClean(entity.getDirtyClean());
            if (evaluator.getSumOfDirtyClean() >= BestSum) {
                BestSum=evaluator.getSumOfDirtyClean();
                bestMove=possMoves.get(i);
            }
        }
        if(previousMove != null)
        	System.out.println("x: "+previousMove.getY()+" y: "+previousMove.getX());
        
        if(previousMove==null || bestMove != previousMove){
        	previousMove = entity.getNode();
        	entity.moveToNode(bestMove,currentState);
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
        if (entity instanceof Pursuer && ((Pursuer) entity).getDirtyClean()==null){
            ((Pursuer) entity).setDirtyClean(new double[currentState.getNodeGrid().length][currentState.getNodeGrid()[0].length]);
            for(int i=0;i<currentState.getNodeGrid().length;i++)
                for(int j=0;j<currentState.getNodeGrid()[0].length;j++){
                    if(currentState.getNodeGrid()[i][j].getValue().equals("wall")){
                        ((Pursuer) entity).getDirtyClean()[i][j]=-5;
                    }

                }
        }
        double BestSum= Double.MIN_VALUE;
        Tree tree = new Tree((Pursuer)entity,currentState,depth);
        ArrayList<Leaf> possMoves = tree.getLevel(1);
        for (int i = 0; i < possMoves.size(); i++) {
            Leaf leaf = possMoves.get(i);
            leaf.evaluate(currentState, entity);
            if (leaf.getValue() >= BestSum) {
                BestSum=leaf.getValue();
                bestMove=leaf.getNode();
            }
        }
        if(previousMove != null)
        	System.out.println("x: "+previousMove.getY()+" y: "+previousMove.getX());
        
        if(previousMove==null || bestMove != previousMove){
        	previousMove = entity.getNode();
        	entity.moveToNode(bestMove,currentState);
        }
        else{
        	  	
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

}
