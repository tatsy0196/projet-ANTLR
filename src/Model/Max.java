package Model;

import EvaluationWithVisitor.ExprGrammarVisitor;

import java.util.ArrayList;
import java.util.Collections;

public class Max extends SimpleOp {

    public Max() {
        super();
    }

    @Override
    public String toString() {
        return "max " + this.getData().toString();
    }

    public int getValues() {
        int maxValue = Collections.max(this.getData());
        return maxValue;
    }
    @Override
    public Object accept(ExprGrammarVisitor<?> visitor) {
        return visitor.visit(this);    }
}


