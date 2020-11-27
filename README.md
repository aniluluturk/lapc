# APC assignment

### Q1: FactoryAssigner.java

file contains a main method that can be executed, 
which will consume input from stdin and print out 
the assignment of workers, if there exist a successful
assignment for the requested production (sum) value.

expected input format:
```
number of workers
production amount per unit time for each worker, separated by whitespace
intended production sum
```

### Q2: BSTChecker.java

file contains a main method that expects a list of comma
separated strings that will be interpreted as a possible
preorder traversal for a BST. if it's possible to construct
a BST with given preorder traversal provided, prints "YES"
otherwise prints "NO"

expected input format
```
node_string_value1, node_string_value2, node_string_value3 ...
```

## Tests

project contains test for both questions, to execute
run `mvn clean test` or `mvn verify`