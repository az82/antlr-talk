package de.az.antlr.demo.calc;

import de.az.antlr.demo.parser.CalcLexer;
import de.az.antlr.demo.parser.CalcParser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

/**
 * Utilites for the Calculator demo.
 */
class CalcUtils {

    /**
     * Create a parser.
     *
     * @param input input string
     * @return parser
     */
    static CalcParser createParser(String input) {
        CalcLexer lexer = new CalcLexer(CharStreams.fromString(input));
        return new CalcParser(new CommonTokenStream(lexer));

    }

    /**
     * Execute a REPL.
     *
     * @param action function accepting a line and returning a value that can be represented as a String
     * @param <R>    return type
     * @throws IOException if reading stdin or writing stdout fails
     */
    static <R> void repl(Function<String, R> action) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            for (String line = prompt(reader); !isQuitCommand(line); line = prompt(reader)) {
                System.out.println(action.apply(line));
            }

        }
    }

    private static boolean isQuitCommand(String line) {
        return line.matches("q(uit)?|e(xit)?\\s*");
    }

    private static String prompt(BufferedReader reader) throws IOException {
        System.out.print("$ ");
        return reader.readLine();
    }

    /**
     * No instantiation.
     */
    private CalcUtils() {
    }

}
