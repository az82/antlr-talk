package de.az.antlr.demo.java;

import de.az.antlr.demo.parser.Java8BaseVisitor;
import de.az.antlr.demo.parser.Java8Parser;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static de.az.antlr.demo.java.JavaUtils.createParser;
import static java.util.Collections.singleton;

/**
 * Visitor extracting the method names from a Java file.
 */
public class MethodExtractingVisitor extends Java8BaseVisitor<Set<String>> implements MethodExtractor {

    @Override
    public Set<String> extractMethods(String fileName) throws IOException {
        return createParser(fileName).compilationUnit().accept(this);
    }

    @Override
    public Set<String> visitMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        String name = ctx.getChild(0).getText();
        // Descend to gather subsequent declarations within the method
        return aggregateResult(singleton(name), super.visitMethodDeclarator(ctx));
    }

    @Override
    protected Set<String> aggregateResult(Set<String> aggregate, Set<String> nextResult) {
        Set<String> result = new HashSet<>();
        if (aggregate != null) {
            result.addAll(aggregate);
        }
        if (nextResult != null) {
            result.addAll(nextResult);
        }
        return result;
    }

}
