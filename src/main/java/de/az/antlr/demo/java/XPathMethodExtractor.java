package de.az.antlr.demo.java;

import de.az.antlr.demo.parser.Java8Parser;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.xpath.XPath;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static de.az.antlr.demo.java.JavaUtils.createParser;

/**
 * Method extractor using XPath on the AST.
 */
public class XPathMethodExtractor implements MethodExtractor {

    @Override
    public Set<String> extractMethods(String fileName) throws IOException {
        Java8Parser parser = createParser(fileName);
        Set<String> methods = new HashSet<>();

        for (ParseTree ctx : XPath.findAll(parser.compilationUnit(), "//methodDeclarator", parser)) {
            methods.add(ctx.getChild(0).getText());
        }

        return methods;
    }

}
