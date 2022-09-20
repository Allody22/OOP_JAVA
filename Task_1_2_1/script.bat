javadoc -d .\make\ -sourcepath .\src\main\java -subpackages ru.nsu.mbogdanov2

javac -d .\make\bin\ -sourcepath .\src\main\java .\src\main\java\ru\nsu\mbogdanov2\Stack.java

mkdir .\make\jar
jar cf .\make\jar\Stack.jar -C .\make\bin .
