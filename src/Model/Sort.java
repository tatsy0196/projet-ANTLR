package Model;
import java.util.Collections;
import EvaluationWithVisitor.ExprGrammarVisitor;

import java.util.ArrayList;

public class Sort extends SimpleOp {

    public Sort() {
        super();
    }

    @Override
    public String toString() {
        return "sort " + this.getData().toString();
    }

    public ArrayList<Integer> getValues() {
        ArrayList<Integer> sortArray = this.getData();
        Collections.sort(sortArray);
        return sortArray;
    }
    @Override
    public Object accept(ExprGrammarVisitor<?> visitor) {
        return visitor.visit(this);    }
}
