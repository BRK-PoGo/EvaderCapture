package AI;

import game.Entity;

import java.util.ArrayList;

/**
 * Created by Alexander on 30/05/2017.
 */
public class SetEvaluator {

    /* Modifiy this whole thing to work pass these into the entities. Entities would have belief system*/

    private VisibilityChecker currentVisibility;
    private VisibilityChecker futureVisibility;
    private int [][] dirtyClean;
    private int sumOfDirtyClean;

    public SetEvaluator (VisibilityChecker future){
        futureVisibility = future;
    }

    public void evaluateDirtyClean(int [][] dirty){
        dirtyClean=dirty;
        int[][] tmp = sumOf2D(futureVisibility.getVisibilityMatrix(), futureVisibility.getVisibilityMatrix());
        int[][] toEvaluate = sumOf2D(tmp,dirtyClean);
        firstEvaluator(toEvaluate,0,0);
        cleanUp(toEvaluate);
        sumOfDirtyClean = sumOfElements(toEvaluate);
        dirtyClean=toEvaluate;
    }
    public int getSumOfDirtyClean(){return sumOfDirtyClean;}
    public int [][] getDirtyClean(){return dirtyClean;}


    /*This method is the first evaluator. It looks for 1 values i.e. fields that could be seen before and cannot be seen
    anymore and checks whether they neighbour dirty nodes and makes them dirty. It calls the second evaluator to perform
    this check on its neighbours
     */

    public void firstEvaluator (int [][] toEvaluate,int i, int j){
        for (int k=i; k<toEvaluate.length; k++){
            for (int l=j; l <toEvaluate[0].length;l++){
                if (toEvaluate[k][l] == 1){
                    //check if they  exist first
                    if (k+1<toEvaluate.length && toEvaluate [k+1][l]==0){
                        toEvaluate[j][k]=0;
                        secondEvaluator(toEvaluate, (k+1),l);
                    }
                    if (k-1>0 && toEvaluate [k-1][l]==0){
                        toEvaluate[j][k]=0;
                        secondEvaluator(toEvaluate, (k-1),l);
                    }
                    if (l+1<toEvaluate[0].length && toEvaluate [k][l+1]==0){
                        toEvaluate[j][k]=0;
                        secondEvaluator(toEvaluate, k,(l+1));
                    }
                    if (l-1>0 && toEvaluate [k][l-1]==0){
                        toEvaluate[j][k]=0;
                        secondEvaluator(toEvaluate, k,(l-1));
                    }
                }
            }
        }
    }


    /*This method checks the neighbours of the firstEvaluator and checks the neighbours of what it sets to 0 too
     */
    public void secondEvaluator (int [][] toEvaluate,int i, int j) {
        for (int k = i; k < toEvaluate.length; k++) {
            for (int l = j; l < toEvaluate[0].length; l++) {
                if (k+1<toEvaluate.length && toEvaluate[k + 1][l] == 1 ) {
                    toEvaluate[k + 1][l] = 0;
                    secondEvaluator(toEvaluate, k, l);
                }
                if (l+1<toEvaluate[0].length && toEvaluate[k][l + 1] == 1) {
                    toEvaluate[k][l + 1] = 0;
                    secondEvaluator(toEvaluate, k, l);
                }
                if (l-1>0 && toEvaluate[k][l - 1] == 1) {
                    toEvaluate[k][l - 1] = 0;
                    secondEvaluator(toEvaluate, k, l);
                }
                if (l-1>0 && toEvaluate[k - 1][l] == 1) {
                    toEvaluate[k - 1][l] = 0;
                    secondEvaluator(toEvaluate, k, l);
                }
                else {
                    firstEvaluator(toEvaluate,k,l);
                }
            }
        }
     }

     /*This method cleans up the final Matrix of dirty and clean nodes
     Dirty nodes have value 0, clean have value 1, walls have value -5
      */

     public void cleanUp (int [][] toClean){
        for (int i = 0; i<toClean.length; i++){
            for (int j = 0; j<toClean[0].length; j++){
                if(toClean[i][j]>0){
                    toClean[i][j]=1;
                }
                if (toClean[i][j]<0){
                    toClean[i][j]=-5;
                }
            }
        }
     }

    /*This method sums two matrices*/

    public int [][] sumOf2D(int [][] sum1, int [][] sum2){
        int [][] sum = new int [sum1.length][sum2[0].length];
        if (sum1.length == sum2.length && sum1[1].length == sum2[0].length) {
            for (int i=0; i<sum1.length;i++){
                for (int j=0; j<sum1[0].length;j++){
                    sum[i][j]=sum1[i][j] + sum2[i][j];
                }
        }

        }
        return sum;
    }
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
