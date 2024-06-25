package Model;

import EvaluationWithVisitor.ExprGrammarVisitor;

import java.util.Collections;

public class Prod extends SimpleOp {

    public Prod() {
        super();
    }

    @Override
    public String toString() {
        return "prod " + this.getData().toString();
    }
    public int getValues() {
        // Initialiser le résultat avec le premier élément
        int resultat = this.getData().get(0);

        // Parcourir le reste des éléments et les multiplier
        for (int i = 1; i < this.getData().size(); i++) {
            resultat *= this.getData().get(i);
        }
        return resultat;
    }

    @Override
    public Object accept(ExprGrammarVisitor<?> visitor) {
        return visitor.visit(this);    }
}
