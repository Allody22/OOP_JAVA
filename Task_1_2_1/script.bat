javadoc -d .\make\ -sourcepath .\src\main\java -subpackages ru.nsu.mbogdanov2

<<<<<<< HEAD:Task_1_2_1/script.bat
javac -d .\make\bin\ -sourcepath .\src\main\java .\src\main\java\ru\nsu\mbogdanov2\Stack.java

mkdir .\make\jar
jar cf .\make\jar\Stack.jar -C .\make\bin .
java -classpath .\make\jar\Stack.jar ru.nsu.mbogdanov2.Stack
=======
javac -d .\make\bin\ -sourcepath .\src\main\java .\src\main\java\ru\nsu\mbogdanov2\HeapSort.java

mkdir .\make\jar
jar cf .\make\jar\HeapSort.jar -C .\make\bin .
java -classpath .\make\jar\HeapSort.jar ru.nsu.mbogdanov2.HeapSort
>>>>>>> master:Task_1_1_1/script.bat
