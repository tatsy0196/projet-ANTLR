package Model;

import EvaluationWithVisitor.ExprGrammarVisitor;

public class Affectation<T>  extends Instruction<T> {
    private String ID;
    private T value ;

    public Affectation(String ID , T value) {
        this.ID = ID;
        this.value = value ;
    }
    public T  getValue() {
        return this.value;
    }

    public String getID() {
        return ID;
    }
    @Override
    public String toString() {
        return ID ;
    }
    @Override
    public Object accept(ExprGrammarVisitor<?> visitor) {
        return visitor.visit(this);
    }

}
