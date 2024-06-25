package ParseTreeVisitors;

import Model.*;
import antlr.ArrayOperationsBaseVisitor;
import antlr.ArrayOperationsParser;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class AntlrToSimpleOp extends ArrayOperationsBaseVisitor<SimpleOp>{
    // data structures to handle semantic errors

    /*
     * Given that all visit_* methods are called in a top-down fashion,
     * we can be sure that the order in which we add declared variables in 'vars'
     * is identical to how they are declared in the program
     */
    private List<String> vars; // stores all the variables declared in the program so far
    private List<String> semanticErrors; // 1. duplicate declaration ; 2. reference to undeclared variable
    // semantic errors are different from syntax errors

    /*
     * semanticErrors list is passed from the Program visitor
     */
    public AntlrToSimpleOp(List<String> semanticErrors) {
        vars = new ArrayList<>();  // empty list of variables at the begining
        this.semanticErrors = semanticErrors;
    }


    @Override
    public SimpleOp visitMin(ArrayOperationsParser.MinContext ctx) {
        //| MIN array ';' #min
        AntlrToArray arrayVisitor = new AntlrToArray();
        ArrayList<Integer> array = arrayVisitor.visit(ctx.getChild(1));

        Min min = new Min();
        min.setData(array);
        return min;
    }

    @Override
    public SimpleOp visitMax(ArrayOperationsParser.MaxContext ctx) {

        AntlrToArray arrayVisitor = new AntlrToArray();
        ArrayList<Integer> array = arrayVisitor.visit(ctx.getChild(1));
        Max max = new Max();
        max.setData(array);

        return max;
    }

    @Override
    public SimpleOp visitSum(ArrayOperationsParser.SumContext ctx) {
        AntlrToArray arrayVisitor = new AntlrToArray();
        ArrayList<Integer> array = arrayVisitor.visit(ctx.getChild(1));
        Sum sum = new Sum();
        sum.setData(array);

        return sum;
    }

    @Override
    public SimpleOp visitSort(ArrayOperationsParser.SortContext ctx) {
        AntlrToArray arrayVisitor = new AntlrToArray();
        ArrayList<Integer> array = arrayVisitor.visit(ctx.getChild(1));
        Sort sort = new Sort();
        sort.setData(array);

        return sort;
    }

    @Override
    public SimpleOp visitProd(ArrayOperationsParser.ProdContext ctx) {

        AntlrToArray arrayVisitor = new AntlrToArray();
        ArrayList<Integer> array = arrayVisitor.visit(ctx.getChild(1));
        Prod prod = new Prod();
        prod.setData(array);


        return prod;
    }
}
//
//    @Override
//    public Expression visitMultiplication(ArrayOperationsParser.MultiplicationContext ctx) {
//        // ctx has 3 children -> left_expression, *, right_expression
//        // recursive call necessary expr * exp
//        // recursively visit the left subtree of the current Multiplication node
//        Expression left = visit(ctx.getChild(0));
//        // recursively visit the right subtree of the current Multiplication node
//        Expression right = visit(ctx.getChild(2));        return new Multiplication(left, right);
//    }
//
//    @Override
//    public Expression visitAddition(ExprParser.AdditionContext ctx) {
//        // ctx has 3 children -> left_expression, +, right_expression
//        // recursive call necessary expr + expr
//        Expression left = visit(ctx.getChild(0)); // recursively visit the left subtree of the current Addition node
//        Expression right = visit(ctx.getChild(2)); // recursively visit the right subtree of the current Addition node
//        return new Addition(left, right);
//    }
//
//    @Override
//    public Expression visitVariable(ExprParser.VariableContext ctx) {
//        // we can report semantic error if the variable has not been declared, not in vars
//        // get the line and column number
//        // ID() is a method generates to correspond to the token ID in the source grammar
//        // however, the safer method is to use the number of the child (tokens may appear several times in a grammar line)
//        Token idToken = ctx.ID().getSymbol(); // equivalent to: ctx.getChild(0).getSymbol()
//        int line = idToken.getLine();
//        int column = idToken.getCharPositionInLine() + 1;
//
//        // ctx has child: id
//        String id = ctx.getChild(0).getText();
//        // check if variable is already declared
//        if (!vars.contains(id)) {
//            semanticErrors.add("Error: variable " + id + " not declared (" + line + ", " + column + ")");
//        }
//        return new Variable(id);
//    }
//
//    @Override
//    public Expression visitNumber(ExprParser.NumberContext ctx) {
//        // ctx is of type NumberContext with 1 child (index 0) the value as a string
//        // to be converted as an integer
//        String numText = ctx.getChild(0).getText();
//        int num = Integer.parseInt(numText);
//        return new Number(num);
//    }
//}
