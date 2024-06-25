package Model;

import EvaluationWithVisitor.ExprGrammarVisitor;

public class VariableDeclaration<T>  extends Instruction<T> {
    private String ID;
    private T value ;
    private String type;

    public VariableDeclaration( String type,String ID, T value) {
        this.ID = ID;
        this.value = value ;
        this.type = type;
    }
    public VariableDeclaration(String type ,String ID ) {
        this.ID = ID;
        this.type = type;
    }

    public T  getValue() {
        return this.value;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return ID + "(" + type + ") " ;
    }

    @Override
    public Object accept(ExprGrammarVisitor<?> visitor) {
        return visitor.visit(this);
    }
}
