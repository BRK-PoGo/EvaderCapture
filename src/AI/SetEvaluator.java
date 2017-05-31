package AI;

/**
 * Created by Alexander on 30/05/2017.
 */
public class SetEvaluator {

    private VisibilityChecker currenctVisibility;
    private VisibilityChecker futureVisiblity;
    private int [][] dirtyClean;

    public SetEvaluator (VisibilityChecker current, VisibilityChecker future){
        currenctVisibility=current;
        futureVisiblity = future;
    }

    public int [][] evaluateVisibility(){

        return dirtyClean;
    }
}
