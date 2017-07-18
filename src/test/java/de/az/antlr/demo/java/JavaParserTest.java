package de.az.antlr.demo.java;

import de.az.antlr.demo.parser.Java8BaseListener;
import de.az.antlr.demo.parser.Java8BaseVisitor;
import de.az.antlr.demo.parser.Java8Lexer;
import de.az.antlr.demo.parser.Java8Parser;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.runtime.tree.xpath.XPath;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Test case showing off the Java 8 parser.
 * <p>
 * The test case uses itself as reference.
 */
public class JavaParserTest {

    private static final String SOURCE_FILE = "src/test/java/" +
            JavaParserTest.class.getName().replace('.', '/') +
            ".java";

    private static final List<String> DECLARED_METHODS = asList(
            "setUp",
            "testVisitor",
            "visitMethodDeclarator",
            "aggregateResult",
            "testListener",
            "enterMethodDeclarator",
            "testXpath");

    private Java8Parser parser;


    @Before
    public void setUp() throws Exception {
        Java8Lexer lexer = new Java8Lexer(CharStreams.fromFileName(SOURCE_FILE));
        parser = new Java8Parser(new CommonTokenStream(lexer));
    }

    /**
     * ANTLR solution to the LoC Code Kata.
     *
     * @see <a href="http://codekata.com/kata/kata13-counting-code-lines/">http://codekata.com/kata/kata13-counting-code-lines/</a>
     */
    @Test
    public void calcLoc() {
        Set<Integer> lines = new HashSet<>();

        ParseTreeWalker.DEFAULT.walk(new Java8BaseListener() {

            @Override
            public void visitTerminal(TerminalNode node) {
                lines.add(node.getSymbol().getLine());
            }
        }, parser.compilationUnit());

        assertThat(lines.size(), is(equalTo(95)));
    }

    @Test
    public void testVisitor() throws Exception {
        Java8Parser.CompilationUnitContext ctx = parser.compilationUnit();
        List<String> methods = ctx.accept(new Java8BaseVisitor<List<String>>() {

            @Override
            public List<String> visitMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
                String name = ctx.getChild(0).getText();
                // Descend to gather subsequent declarations within the method
                return aggregateResult(singletonList(name), super.visitMethodDeclarator(ctx));
            }

            @Override
            protected List<String> aggregateResult(List<String> aggregate, List<String> nextResult) {
                List<String> result = new ArrayList<>();
                if (aggregate != null) {
                    result.addAll(aggregate);
                }
                if (nextResult != null) {
                    result.addAll(nextResult);
                }
                return result;
            }
        });

        assertThat(methods, is(equalTo(DECLARED_METHODS)));
    }

    @Test
    public void testListener() throws Exception {
        final List<String> methods = new ArrayList<>();
        ParseTreeWalker.DEFAULT.walk(new Java8BaseListener() {

            @Override
            public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
                methods.add(ctx.getChild(0).getText());
            }

        }, parser.compilationUnit());

        assertThat(methods, is(equalTo(DECLARED_METHODS)));
    }


    @Test
    public void testXpath() throws Exception {
        List<String> methods = new ArrayList<>();

        for (ParseTree ctx : XPath.findAll(parser.compilationUnit(), "//methodDeclarator", parser)) {
            methods.add(ctx.getChild(0).getText());
        }

        assertThat(methods, is(equalTo(DECLARED_METHODS)));
    }
}
