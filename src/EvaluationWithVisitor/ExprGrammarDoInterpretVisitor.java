package EvaluationWithVisitor;

import Model.*;

import java.beans.Expression;
import java.util.HashMap;

public class ExprGrammarDoInterpretVisitor implements ExprGrammarVisitor<Object> {

    public HashMap<String, Variable> values; // symbol table for storing values of variables

    public ExprGrammarDoInterpretVisitor() {
        values = new HashMap<>();
    }

    public ExprGrammarDoInterpretVisitor(HashMap<String, Variable> symbolsTable) {
        values = symbolsTable;
    }


    @Override
    public String visit(Program program) {
        System.out.println("Symbol table before interpretation : \n" + values);
        System.out.println("Program interpretation: Begin");
        for (Instruction instructions : program.instructions) {
            // expression.accept(this) will trigger a call to the right visit method
            // output a line with the source code and the produced value (form accept()->visit())
            // for VariableDeclration Void is produced
            System.out.println(instructions + " is " + instructions.accept(this));
        }
        System.out.println("Program interpretation: End");
        System.out.println("Symbol table after interpretation : \n" + values);
        return "";
    }
    @Override
    public Object visit(Max max) {
        return max.getValues();
    }

    @Override
    public Object visit(Min min) {
        return min.getValues();
    }

    @Override
    public Object visit(Prod prod) {
        return prod.getValues();
    }

    @Override
    public Object visit(SimpleOp simpleOp) {
        return simpleOp;
    }
    @Override
    public Object visit(Sort sort) {
        return sort.getValues();
    }

    @Override
    public Object visit(Sum sum) {
        return sum.getValues();
    }

    @Override
    public Object visit(Affectation affectation) {
        if(values.containsKey(affectation.getID())) {
            values.get(affectation.getID()).setValue(affectation.getValue());
        }
        return affectation.getID() + " value : " + affectation.getValue();
    }

    @Override
    public Object visit(VariableDeclaration variableDeclaration) {
        return variableDeclaration.getType() + " value : " + variableDeclaration.getValue();
    }

    @Override
    public Object visit(VarOut varOut) {
        return varOut.getID()     +" value = "+    values.get(varOut.getID());}
}
//
//    @Override
//    public Integer visit(Addition addition) {
//        // Use of wildcard to get the left and right expressions
//        // right and left expressions will deliver an int upon visit
//        // left.accept(this) and right.accept(this) will trigger a call to the right visit method
//        Expression<?> left = addition.getLeft();
//        int valLeft = (int) left.accept(this);
//        Expression<?> right = addition.getRight();
//        int valRight = (int) right.accept(this);
//        return valLeft + valRight;
//    }
//
//    @Override
//    public Integer visit(Multiplication multiplication) {
//        // Use of wildcard to get the left and right expressions
//        // right and left expressions will deliver an int upon visit
//        // left.accept(this) and right.accept(this) will trigger a call to the right visit method
//        Expression<?> left = multiplication.getLeft();
//        int valLeft = (int) left.accept(this);
//        Expression<?> right = multiplication.getRight();
//        int valRight = (int) right.accept(this);
//        return valLeft * valRight;
//    }
//
//    @Override
//    public Integer visit(Variable variable) {
//        // get the value of the variable
//        return values.get(variable.getId());
//    }
//
//    @Override
//    public Void visit(VariableDeclaration variableDeclaration) {
//        // put the variable and its value in the values HashMap
//        values.put(variableDeclaration.getId(), variableDeclaration.getValue());
//        return null;
//    }
//
//    @Override
//    public Integer visit(Model.Number number) {
//        // get the value of the number
//        return number.getNumber();
//    }
//}
