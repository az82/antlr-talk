package de.az.antlr.demo.java;

import java.io.IOException;
import java.util.Set;

/**
 * A method extractor.
 */
public interface MethodExtractor {

    /**
     * Extract methods from a Java file.
     *
     * @param fileName Java file
     * @return set of method methods
     * @throws IOException if parsind the file fails
     */
    Set<String> extractMethods(String fileName) throws IOException;

}
