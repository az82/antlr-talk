package de.az.antlr.demo.calc;

import java.io.IOException;

import static de.az.antlr.demo.calc.CalcUtils.createParser;
import static de.az.antlr.demo.calc.CalcUtils.repl;

/**
 * Calculator that uses a visitor.
 */
public class VisitingCalculator {

    /**
     * Execute the REPL.
     *
     * @param argv command line arguments, ignored
     * @throws IOException if reading stdin or writing stdout fails
     */
    public static void main(String[] argv) throws IOException {
        repl(line -> createParser(line).calc().accept(new CalculatingVisitor()));
    }

}
