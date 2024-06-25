package ParseTreeVisitors;

import Model.Affectation;
import Model.SimpleOp;
import Model.VarOut;
import Model.Variable;
import antlr.ArrayOperationsBaseVisitor;
import antlr.ArrayOperationsParser;

import java.util.HashMap;
import java.util.List;

public class AntlrToVarOut  extends ArrayOperationsBaseVisitor<VarOut> {


    private List<String> semanticErrors;// the symboles table stores all the variables declared in the program so far,
    private HashMap<String, Variable> symbolTable;
    public AntlrToVarOut(List<String> semanticErrors, HashMap<String, Variable> symbolTable) {
        this.symbolTable = symbolTable;
        this.semanticErrors = semanticErrors;
    }
    @Override
    public VarOut visitVarout(ArrayOperationsParser.VaroutContext ctx){
        String id = ctx.getChild(0).getText();
        if(!symbolTable.containsKey(id)){
            semanticErrors.add("elle existe pas ta variable "+ id);
        }return new VarOut(id);}
}



