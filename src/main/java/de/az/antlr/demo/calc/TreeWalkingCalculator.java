package de.az.antlr.demo.calc;

import de.az.antlr.demo.parser.CalcParser;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.IOException;

import static de.az.antlr.demo.calc.CalcUtils.createParser;
import static de.az.antlr.demo.calc.CalcUtils.repl;
import static java.lang.String.format;

/**
 * Calculator that walks the parse tree.
 */
public class TreeWalkingCalculator {

    /**
     * Execute the REPL.
     *
     * @param argv command line arguments, ignored
     * @throws IOException if reading stdin or writing stdout fails
     */
    public static void main(String[] argv) throws IOException {
        repl(line -> eval(createParser(line).calc().expr()));
    }

    private static Integer eval(CalcParser.ExprContext ctx) {
        if (ctx.PLUS() != null) {
            return evalLeft(ctx) + evalRight(ctx);
        } else if (ctx.MINUS() != null) {
            return evalLeft(ctx) - evalRight(ctx);
        } else if (ctx.TIMES() != null) {
            return evalLeft(ctx) * evalRight(ctx);
        } else if (ctx.DIV() != null) {
            return evalLeft(ctx) / evalRight(ctx);
        } else if (ctx.number() != null) {
            return Integer.valueOf(ctx.getChild(0).getText());
        } else if (ctx.BR_OPEN() != null) {
            return eval((CalcParser.ExprContext) ctx.getChild(1));
        } else {
            throw new IllegalStateException(format("Unexpected expression variant in \"%s\"", ctx.getText()));
        }
    }

    private static Integer evalLeft(CalcParser.ExprContext ctx){
        return eval((CalcParser.ExprContext) ctx.getChild(0));
    }

    private static Integer evalRight(CalcParser.ExprContext ctx){
        return eval((CalcParser.ExprContext) ctx.getChild(2));
    }

}
