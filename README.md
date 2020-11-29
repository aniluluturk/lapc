# APC assignment

### Q1: FactoryAssigner.java

file contains a main method that can be executed, 
which will consume input from stdin and print out 
the assignment of workers, if there exists a successful
assignment for the requested production (sum) value.

expected input format:
```
number of workers
production amount per unit time for each worker, separated by whitespace
intended production sum
```

### Q2: BinaryTreeChecker.java

file contains a main method that expects a list of comma
separated strings that will be interpreted as
directed edges of a tree. if it's possible to construct
a binary tree with given edges, prints "YES"
otherwise prints "NO"

expected input format
```
edge1, edge2, edge3 ...
```

## To run main methods 

first build the project with `mvn clean install`

### Q1: FactoryAssigner

1. run from target class FactoryAssigner

    ```
    java -classpath target\classes FactoryAssigner
    ```
1. provide input to stdin

### Q2: BinaryTreeChecker

1. run from target class BinaryTreeChecker
    
    ```
    java -classpath target\classes BinaryTreeChecker
    ```
1. provide input to stdin

## Tests

project contains test for both questions, to execute
run `mvn clean test` or `mvn verify`