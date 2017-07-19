package de.az.antlr.demo.java;

import de.az.antlr.demo.parser.Java8BaseListener;
import de.az.antlr.demo.parser.Java8Lexer;
import de.az.antlr.demo.parser.Java8Parser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static java.lang.String.format;

/**
 * ANTLR solution to the LoC Code Kata.
 *
 * @see <a href="http://codekata.com/kata/kata13-counting-code-lines/">http://codekata.com/kata/kata13-counting-code-lines/</a>
 */
public class LoCCounter {

    /**
     * Sums the LoC of a list of files.
     *
     * @param argv java files
     * @throws IOException if parsing one of the files fails
     */
    public static void main(String[] argv) throws IOException {
        int loc = 0;
        for (String fileName : argv) {
            loc += countLoc(fileName);
        }

        System.out.println(format("%d LoC in the files %s.", loc, Arrays.toString(argv)));
    }

    private static int countLoc(String fileName) throws IOException {
        Java8Lexer lexer = new Java8Lexer(CharStreams.fromFileName(fileName));
        Java8Parser parser = new Java8Parser(new CommonTokenStream(lexer));

        Set<Integer> loc = new HashSet<>();

        ParseTreeWalker.DEFAULT.walk(new Java8BaseListener() {
            @Override
            public void visitTerminal(TerminalNode node) {
                // EOF also is a token
                if (node.getSymbol().getType() != Token.EOF) {
                    loc.add(node.getSymbol().getLine());
                }
            }
        }, parser.compilationUnit());

        return loc.size();
    }

}
