package de.az.antlr.demo.calc;

import de.az.antlr.demo.parser.CalcBaseListener;
import de.az.antlr.demo.parser.CalcParser;

import java.util.Stack;

/**
 * Listener evaluating the calculation.
 */
public class CalculatingListener extends CalcBaseListener {

    private final Stack<Integer> operands = new Stack<>();

    /**
     * Get the result.
     *
     * @return result
     */
    public Integer getResult() {
        assert operands.size() == 1;
        return operands.peek();
    }

    @Override
    public void exitExpr(CalcParser.ExprContext ctx) {
        if (ctx.PLUS() != null) {
            operands.push(operands.pop() + operands.pop());
        } else if (ctx.MINUS() != null) {
            operands.push(operands.pop() - operands.pop());
        } else if (ctx.TIMES() != null) {
            operands.push(operands.pop() * operands.pop());
        } else if (ctx.DIV() != null) {
            operands.push(operands.pop() / operands.pop());
        }
    }

    @Override
    public void enterNumber(CalcParser.NumberContext ctx) {
        operands.push(Integer.valueOf(ctx.getText()));
    }

}
