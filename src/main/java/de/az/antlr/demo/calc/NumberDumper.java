package de.az.antlr.demo.calc;

import de.az.antlr.demo.parser.CalcParser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.io.IOException;

import static de.az.antlr.demo.calc.CalcUtils.createParser;
import static de.az.antlr.demo.calc.CalcUtils.repl;
import static java.util.stream.Collectors.toList;

/**
 * Dumps all the numbers in a calculator expression.
 */
public class NumberDumper {

    /**
     * Execute the REPL.
     *
     * @param argv command line arguments, ignored
     * @throws IOException if reading stdin or writing stdout fails
     */
    public static void main(String[] argv) throws IOException {
        repl(line -> {
            CalcParser parser = createParser(line);
            return XPath.findAll(parser.calc(), "//number", parser).stream()
                    .map(ParseTree::getText)
                    .collect(toList());
        });
    }
}
