package ParseTreeVisitors;

import Model.Affectation;
import Model.SimpleOp;
import Model.Variable;
import Model.VariableDeclaration;
import antlr.ArrayOperationsBaseVisitor;
import antlr.ArrayOperationsParser;

import java.util.HashMap;
import java.util.List;

public class AntlrToAffectation  extends ArrayOperationsBaseVisitor<Affectation> {


    private List<String> semanticErrors;// the symboles table stores all the variables declared in the program so far,
    private HashMap<String, Variable> symbolTable;
    public AntlrToAffectation(List<String> semanticErrors, HashMap<String, Variable> symbolTable) {
        this.symbolTable = symbolTable;
        this.semanticErrors = semanticErrors;
    }

    @Override
    public Affectation visitAffectsimpleop(ArrayOperationsParser.AffectsimpleopContext ctx){
        return null;}

    @Override
    public Affectation visitAffectarray(ArrayOperationsParser.AffectarrayContext ctx){
        AntlrToArray arrayVisitor = new AntlrToArray();
        String id = ctx.getChild(0).getText();
        Object value = arrayVisitor.visit(ctx.getChild(2));
        if(!symbolTable.containsKey(id)){
            semanticErrors.add("elle existe pas ta variable "+ id);
        } else if (!symbolTable.get(id).getType().equals("array")) {
            semanticErrors.add("la variable existe mais le type est pas le bon on veut un array pour "+ id + " et il est declaré comme un entier");
        }
        return new Affectation(id, value);}

    @Override
    public Affectation visitAffectint(ArrayOperationsParser.AffectintContext ctx){
        String id = ctx.getChild(0).getText();
        Object value = Integer.parseInt(ctx.getChild(2).getText());
        if(!symbolTable.containsKey(id)){
            semanticErrors.add("elle existe pas ta variable "+ id);
        } else if (!symbolTable.get(id).getType().equals("int")) {
            semanticErrors.add("la variable existe mais le type est pas le bon on veut un int pour "+ id);


        }
        return new Affectation(id, value);}



}
