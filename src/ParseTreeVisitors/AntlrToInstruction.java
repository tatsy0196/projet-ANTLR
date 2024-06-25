package ParseTreeVisitors;

import Model.*;
import antlr.ArrayOperationsBaseVisitor;
import antlr.ArrayOperationsParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//Le visiteur d’instructions la passera en paramètre des
//constructeurs des visiteurs non terminaux qui en ont besoin
//(utilisent des variables)
//tabledesymbole doit etres passer en parameter
public class AntlrToInstruction  extends ArrayOperationsBaseVisitor<Instruction> {
    public List<String> semanticErrors;
    private HashMap<String, Variable> symbolTable;

    public AntlrToInstruction (List<String> semanticErrors, HashMap<String, Variable> symbolTable) {
        this.symbolTable = symbolTable;
        this.semanticErrors = semanticErrors;

    }
    @Override
    public Instruction visitSimpleopinstruction(ArrayOperationsParser.SimpleopinstructionContext ctx) {

        AntlrToSimpleOp simpleOpVisitor = new AntlrToSimpleOp(this.semanticErrors);
        SimpleOp simpleOp = simpleOpVisitor.visit(ctx.getChild(0));

        return simpleOp;
    }
    @Override
    public Instruction visitVardeclinstruction(ArrayOperationsParser.VardeclinstructionContext ctx) {

        AntlrToVarDecl varDeclVisitor = new AntlrToVarDecl(this.semanticErrors, this.symbolTable);
        VariableDeclaration vardcl = varDeclVisitor.visit(ctx.getChild(0));

        return vardcl;
    }
    @Override
    public Instruction visitAffectationinstruction(ArrayOperationsParser.AffectationinstructionContext ctx) {

        AntlrToAffectation affectVisitor = new AntlrToAffectation(this.semanticErrors, this.symbolTable);
        Affectation affect = affectVisitor.visit(ctx.getChild(0));

        return affect;
    }
    public Instruction visitVaroutinstruction(ArrayOperationsParser.VaroutinstructionContext ctx ){
        AntlrToVarOut varoutVisitor = new AntlrToVarOut(this.semanticErrors, this.symbolTable);
        VarOut varout = varoutVisitor.visit(ctx.getChild(0));

    return varout;
}
}
