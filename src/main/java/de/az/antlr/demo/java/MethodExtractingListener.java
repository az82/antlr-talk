package de.az.antlr.demo.java;

import de.az.antlr.demo.parser.Java8BaseListener;
import de.az.antlr.demo.parser.Java8Parser;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static de.az.antlr.demo.java.JavaUtils.createParser;

/**
 * Listener extracting the method names from a Java file.
 */
public class MethodExtractingListener extends Java8BaseListener implements MethodExtractor {
    private final Set<String> methods = new HashSet<>();

    @Override
    public Set<String> extractMethods(String fileName) throws IOException {
        methods.clear();

        ParseTreeWalker.DEFAULT.walk(this, createParser(fileName).compilationUnit());

        return methods;
    }

    @Override
    public void enterMethodDeclarator(Java8Parser.MethodDeclaratorContext ctx) {
        methods.add(ctx.getChild(0).getText());
    }

}
