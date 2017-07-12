package de.az.antlr.demo.calc;

import de.az.antlr.demo.parser.CalcBaseVisitor;
import de.az.antlr.demo.parser.CalcParser;
import org.antlr.v4.runtime.tree.ParseTree;

/**
 * Visitor evaluating the calculation.
 *
 */
public class CalculatingVisitor extends CalcBaseVisitor<Integer> {

    @Override
    public Integer visitExpr(CalcParser.ExprContext ctx) {
        if (ctx.PLUS() != null) {
            return lhs(ctx) + rhs(ctx);
        } else if (ctx.MINUS() != null) {
            return lhs(ctx) - rhs(ctx);
        } else if (ctx.TIMES() != null) {
            return lhs(ctx) * rhs(ctx);
        } else if (ctx.DIV() != null) {
            return lhs(ctx) / rhs(ctx);
        } else {
            return super.visitExpr(ctx);
        }
    }

    @Override
    public Integer visitNumber(CalcParser.NumberContext ctx) {
        return Integer.parseInt(ctx.getText());
    }

    @Override
    protected Integer aggregateResult(Integer aggregate, Integer nextResult) {
        return aggregate != null ? aggregate : nextResult;
    }

    private Integer lhs(ParseTree ctx) {
        return visit(ctx.getChild(0));
    }

    private Integer rhs(ParseTree ctx) {
        return visit(ctx.getChild(2));
    }

}
