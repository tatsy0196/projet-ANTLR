package ParseTreeVisitors;

import Model.SimpleOp;
import antlr.ArrayOperationsBaseVisitor;
import antlr.ArrayOperationsParser;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.List;

public class AntlrToArray extends ArrayOperationsBaseVisitor<ArrayList<Integer>>{

    public List<String> semanticErrors;


    @Override
    public ArrayList<Integer> visitArray(ArrayOperationsParser.ArrayContext ctx) {
        // '[' INT (',' INT)* ']';

        // declarer et init array
        ArrayList<Integer> array = new ArrayList<>();

        // remplir
        // boucle
        for (int i = 1 ; i < ctx.getChildCount()-1 ; i = i+2){
          array.add(Integer.valueOf(ctx.getChild(i).getText()));

        }

        return array;
    }
}