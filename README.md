# ANTLR talk

This project contains a small ANTLR showcase and and a talk introducing ANTLR.


## Content
### Java showcase
The Java 8 grammar from https://github.com/antlr/grammars-v4/blob/master/java8/Java8.g4 is used as an example.

The class `JavaParserTest` showcases the basic usage of lexer, parser, the visitor and listener patterns and parse tree matching with XPath.

### Calc showcase

### Talk

You can find the accompanying talk (German) in the [talk](talk) folder.


## Build
### Build with Gradle
```sh
./gradlew clean install
```

### Build with Maven
This project uses the Maven wrapper from https://github.com/takari/maven-wrapper.
Alternatively you can use your own Maven installation.

```sh
./mvnw io.takari:maven:wrapper -Dmaven=3.5.0 
./mvnw clean install
```

## Acknowledgements
Parts of this work are based on  https://github.com/nielsutrecht/antlr-pres by Niels Dommerholt and are used under the MIT license [3rdparty-licenses/MIT-LICENSE](3rdparty-licenses/MIT-LICENSE)