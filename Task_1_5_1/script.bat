
javac -d .\make\bin\ -sourcepath .\src\main\java .\src\main\java\ru\nsu\mbogdanov2\PrefixCalc.java
javac -d .\make\bin\ -sourcepath .\src\main\java .\src\main\java\ru\nsu\mbogdanov2\Calculator.java
javac -d .\make\bin\ -sourcepath .\src\main\java .\src\main\java\ru\nsu\mbogdanov2\ExpressionTree.java


mkdir .\make\jar
jar cf .\make\jar\PrefixCalc.jar -C .\make\bin .
gjava -classpath .\make\jar\PrefixCalc.jar ru.nsu.mbogdanov2.PrefixCalc