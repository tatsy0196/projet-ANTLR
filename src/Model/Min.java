package Model;

import EvaluationWithVisitor.ExprGrammarVisitor;

import java.util.Collections;

public class Min extends SimpleOp {

    public Min() {
        super();
    }

    @Override
    public String toString() {
        return "min " + this.getData().toString();
    }

    public int getValues() {
        int minValue = Collections.min(this.getData());
        return minValue;
    }
    @Override
    public Object accept(ExprGrammarVisitor<?> visitor) {
        return visitor.visit(this);    }
}
