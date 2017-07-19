package de.az.antlr.demo.java;

import java.io.IOException;
import java.util.Arrays;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

/**
 * Method extraction example.
 */
public class MethodsExtractionExample {

    /**
     * Extract the method names from a list of Java files.
     *
     * @param argv list of Java files
     * @throws IOException if parsing one of the files fails
     */
    public static void main(String[] argv) throws IOException {

        for (MethodExtractor extractor : Arrays.asList(new MethodExtractingVisitor(), new MethodExtractingListener(), new XPathMethodExtractor())) {
            System.out.println(format("Using %s:", extractor.getClass().getSimpleName()));

            for (String fileName : argv) {
                System.out.println(format(
                        "%s contains the method names %s",
                        fileName,
                        extractor.extractMethods(fileName).stream().sorted().collect(toList())));
            }

            System.out.println();
        }

    }

}
