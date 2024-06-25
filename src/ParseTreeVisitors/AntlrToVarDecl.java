package ParseTreeVisitors;

import Model.Max;
import Model.SimpleOp;
import Model.Variable;
import Model.VariableDeclaration;
import antlr.ArrayOperationsBaseVisitor;
import antlr.ArrayOperationsParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class AntlrToVarDecl  extends ArrayOperationsBaseVisitor<VariableDeclaration> {
    // List of semantic errors
    private List<String> semanticErrors;// the symboles table stores all the variables declared in the program so far,
    private HashMap<String, Variable> symbolTable;

    public AntlrToVarDecl(List<String> semanticErrors, HashMap<String, Variable> symbolTable) {
        this.symbolTable = symbolTable;
        this.semanticErrors = semanticErrors;
    }
    @Override
    public VariableDeclaration visitVararray(ArrayOperationsParser.VararrayContext ctx){
        String id = ctx.getChild(1).getText();
        String type = ctx.getChild(0).getText();
        if(symbolTable.containsKey(id)){
            semanticErrors.add("elle existe deja ta variable "+ id);
        }
        else {
            symbolTable.put(id, new Variable<>(id, type));
        }
        return new VariableDeclaration(type, id);
    }

    @Override
    public VariableDeclaration visitInitvararray(ArrayOperationsParser.InitvararrayContext ctx){
        AntlrToArray arrayVisitor = new AntlrToArray();

        String id = ctx.getChild(1).getText();
        String type = ctx.getChild(0).getText();
        Object value = arrayVisitor.visit(ctx.getChild(3));
        if(symbolTable.containsKey(id)){
            semanticErrors.add("elle existe deja ta variable "+ id);
        }
        else {
            symbolTable.put(id, new Variable<>(id, type, value));
        }
        return new VariableDeclaration(type, id, value);
    }

    @Override
    public VariableDeclaration visitVarint(ArrayOperationsParser.VarintContext ctx){
        String id = ctx.getChild(1).getText();
        String type = ctx.getChild(0).getText();

        if(symbolTable.containsKey(id)){
            semanticErrors.add("elle existe deja ta variable "+ id);
        }
        else{
            symbolTable.put(id, new Variable<>(id, type));
        }
        return new VariableDeclaration(type, id);
    }

    @Override
    public VariableDeclaration visitInitvarint(ArrayOperationsParser.InitvarintContext ctx){
        String id = ctx.getChild(1).getText();
        String type = ctx.getChild(0).getText();
        Object value = Integer.parseInt(ctx.getChild(3).getText());
        if(symbolTable.containsKey(id)){
            semanticErrors.add("elle existe deja ta variable "+ id);
        }
        else {
            symbolTable.put(id, new Variable<>(id, type, value));
        }
        return new VariableDeclaration(type, id, value);
    }
}