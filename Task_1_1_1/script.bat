javadoc -d .\make\ -sourcepath .\src\main\java -subpackages ru.nsu.mbogdanov2

javac -d .\make\bin\ -sourcepath .\src\main\java .\src\main\java\ru\nsu\mbogdanov2\HeapSort.java

mkdir .\make\jar
jar cf .\make\jar\HeapSort.jar -C .\make\bin .
java -classpath .\make\jar\HeapSort.jar ru.nsu.mbogdanov2.HeapSort