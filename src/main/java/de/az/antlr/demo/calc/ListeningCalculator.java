package de.az.antlr.demo.calc;

import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;

import static de.az.antlr.demo.calc.CalcUtils.createParser;
import static de.az.antlr.demo.calc.CalcUtils.repl;

/**
 * Calculator that uses a listener.
 */
public class ListeningCalculator {

    /**
     * Execute the REPL.
     *
     * @param argv command line arguments, ignored
     * @throws IOException if reading stdin or writing stdout fails
     */
    public static void main(String[] argv) throws IOException {
        repl(line -> {
            CalculatingListener listener = new CalculatingListener();
            ParseTreeWalker.DEFAULT.walk(listener, createParser(line).calc());
            return listener.getResult();
        });
    }

}
