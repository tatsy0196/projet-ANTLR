package ParseTreeVisitors;

import Model.Program;
import Model.Variable;
import antlr.ArrayOperationsBaseVisitor;
import antlr.ArrayOperationsParser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AntlrToProgram extends ArrayOperationsBaseVisitor<Program> {

    // List of semantic errors
    // – variable re-declaration
    // – variable not declared
    // public to be accessed by the main application program
    public List<String> semanticErrors;
    public HashMap<String, Variable> symbolTable;
    @Override
    public Program visitProgram(ArrayOperationsParser.ProgramContext ctx) {
        // visit the root of the parse tree
        // prog: (decl | expr)+ EOF  # Program
        // decl and expr are Expression
        // EOF is the last child
        Program prog = new Program();

        semanticErrors = new ArrayList<>();
        symbolTable = new HashMap<>();

        // helper visitor for transforming each subtree into an Expression object
        AntlrToInstruction instructionVisitor = new AntlrToInstruction(semanticErrors, symbolTable);
        // do not visit the last child of start symbol PROG which is EOF
        for (int i = 0; i < ctx.getChildCount()-1; i++) {
          prog.addInstruction(instructionVisitor.visit(ctx.getChild(i)));
        }

        return prog;

    }
}
