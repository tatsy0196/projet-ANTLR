package EvaluationWithVisitor;
import Model.*;

public interface ExprGrammarVisitor<R> {
    R visit(Max max);
    R visit(Min min);
    R visit(Prod prod);
    R visit(Program program);
    R visit(SimpleOp simpleOp);
    R visit(Sort sort);
    R visit(Sum sum);

    R visit(Affectation affectation);

    //R visit(Variable variable);

    R visit(VariableDeclaration variableDeclaration);

    R visit( VarOut varOut);

}
