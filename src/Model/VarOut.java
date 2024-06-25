package Model;

import EvaluationWithVisitor.ExprGrammarVisitor;


public class VarOut  extends Instruction<Object> {
    private String ID;

    public VarOut(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }
    public String toString() {
        return ID ;
    }


    @Override
    public Object accept(ExprGrammarVisitor<?> visitor) {
        return visitor.visit(this);
    }
}
