# ANTLR talk

This project contains a small ANTLR showcase and and a talk introducing ANTLR.


## Content
### Java showcase

The Java 8 grammar from https://github.com/antlr/grammars-v4/blob/master/java8/Java8.g4 is used as an example.
- `MethodsExtrationExample` extract method names from Java sources using the listener and visitor patterns and XPath
- `LoCCounter` ANTLR solution to the LoC Code Kata (see http://codekata.com/kata/kata13-counting-code-lines/)


### Calc showcase

- `ListeningCalculator` showcases the listener pattern
- `VisitingCalculator` showcases the visitor pattern
- `TreeWalkingCalculator` showcases tree walking
- `NumberDumper` showcases XPath on the AST

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