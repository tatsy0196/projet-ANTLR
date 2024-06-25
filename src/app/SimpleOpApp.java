package app;
import EvaluationWithVisitor.ExprGrammarDoInterpretVisitor;
import antlr.ArrayOperationsLexer;
import antlr.ArrayOperationsParser;
import ParseTreeVisitors.AntlrToProgram;
import Utils.MySyntaxErrorListener;
import Model.Program;
//import evaluation.SimpleOpProcessor;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import java.io.IOException;

public class SimpleOpApp {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Usage: file name\n");
        } else {
            String fileName = args[0];
            ArrayOperationsParser parser = getParser(fileName);
            // tell ANTLR to build a parse tree
            // parse from the start synbol 'prog'
            ParseTree antlrAST = parser.program();

            // parsing may have found syntactic error, which is bad!

            // Option Final -> BEGIN
            // a solution to know if this was the case, we need a listener associated with the parser

            //only go ahead if there is no syntax error detected by the parser
            if(MySyntaxErrorListener.hasError) {
                /* let the syntax error be reported */
                System.out.println("error here all was stoped");

            } else {
                AntlrToProgram progVisitor = new AntlrToProgram();
                Program prog = progVisitor.visit(antlrAST);

               if (progVisitor.semanticErrors.isEmpty()) {
                   ExprGrammarDoInterpretVisitor interpretVisitor = new ExprGrammarDoInterpretVisitor(progVisitor.symbolTable);
                   interpretVisitor.visit(prog);
               } else {
                   for (String err : progVisitor.semanticErrors) {
                       System.out.println(err);
                   }
               }
           }
           // Option Final -> END

            // Option Initial -> BEGIN (mix of syntax and semantics)
            // create a visitor for converting the part tree into a Program/ArrayOperationsession object
            /*AntltToProgram progVisitor = new AntltToProgram();
            Program prog = progVisitor.visit(antlrAST);

            if (progVisitor.semanticErrors.isEmpty()) {
                ArrayOperationsessionProcessor ep = new ArrayOperationsessionProcessor(prog.expressions);
                for (String evaluation : ep.getEvaluationResults()) {
                    System.out.println(evaluation);
                }
            } else {
                for (String err : progVisitor.semanticErrors) {
                    System.out.println(err);
                }
            }*/
            // Option Initial -> END

        }
    }

    // helper method, the same for every grammar
    /*
     * Here the type of parser and lexer are specific to the
     * grammar name ArrayOperations.g4
     */
    private static ArrayOperationsParser getParser(String fileName) {
        ArrayOperationsParser parser = null;

        try {
            CharStream input = CharStreams.fromFileName(fileName);
            ArrayOperationsLexer lexer = new ArrayOperationsLexer(input);
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            // parser initialisation
            parser = new ArrayOperationsParser(tokens);

            // Syntax error error handling
            parser.removeErrorListeners();  // default listener
            parser.addErrorListener(new MySyntaxErrorListener());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return parser;
    }
}