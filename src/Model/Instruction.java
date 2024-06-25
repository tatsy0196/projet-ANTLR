package Model;
import EvaluationWithVisitor.ExprGrammarVisitor;

/**
 * In case several types of instruction are developed
 */
public abstract class Instruction<R> {
    public abstract Object accept(ExprGrammarVisitor<?> visitor);

}
