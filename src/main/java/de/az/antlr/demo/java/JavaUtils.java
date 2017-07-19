package de.az.antlr.demo.java;

import de.az.antlr.demo.parser.Java8Lexer;
import de.az.antlr.demo.parser.Java8Parser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;

/**
 * Utilities for the Java showcases
 */
class JavaUtils {

    /**
     * Create a Java parser.
     *
     * @param fileName Java file
     * @return Java parser
     * @throws IOException if creating the Java parser fails
     */
    static Java8Parser createParser(String fileName) throws IOException {
        Java8Lexer lexer = new Java8Lexer(CharStreams.fromFileName(fileName));
        return new Java8Parser(new CommonTokenStream(lexer));
    }

    /**
     * No instantiation.
     */
    private JavaUtils() {
    }

}
