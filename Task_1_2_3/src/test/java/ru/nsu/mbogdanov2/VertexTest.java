package ru.nsu.mbogdanov2;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**Testing of base vertex methods in graph.
 */
public class VertexTest {
    @Test
    public void vertexEqualsTest() {
        Graph<Integer> expectedGraph = new Graph<>();
        var secondVertex = new Vertex<>(2);
        expectedGraph.addVertex(1);
        expectedGraph.addVertex(2);
        Assertions.assertNotEquals(secondVertex, expectedGraph.getVertex(1));
        Assertions.assertEquals(secondVertex, expectedGraph.getVertex(2));
    }

    @Test
    public void deleteVertexTest() {
        Graph<String> expectedGraph = new Graph<>();
        expectedGraph.addVertex("one");
        expectedGraph.addVertex("two");
        expectedGraph.addEdge("one", "two", 33);
        expectedGraph.addEdge("one", "two", 66);
        var actualDeletedVertex = expectedGraph.deleteVertex("one");
        NullPointerException exceptionDeleteEdge =
                assertThrows(NullPointerException.class,
                        () -> expectedGraph.deleteEdge("one", "two", 33));

        Assertions.assertEquals("No edges from this vertex", exceptionDeleteEdge.getMessage());
        var startVertex = new Vertex<>("one");
        Assertions.assertEquals(actualDeletedVertex, startVertex);
    }

    @Test
    public void addVertexTest() {
        Graph<Integer> expectedGraph = new Graph<>();
        var expectedVertex = new Vertex<>(1);
        Assertions.assertDoesNotThrow(() -> expectedGraph.addVertex(1));
        Assertions.assertDoesNotThrow(() -> expectedGraph.addVertex(2));
        Assertions.assertEquals(expectedGraph.getVertex(1), expectedVertex);
    }

    @Test
    public void getMapOfVertexesTest() {
        Graph<Integer> expectedGraph = new Graph<>();

        expectedGraph.addVertex(1);
        expectedGraph.addVertex(2);
        expectedGraph.addVertex(3);
        expectedGraph.addEdge(1, 2, 33);
        expectedGraph.addEdge(2, 3, 44);
        expectedGraph.addEdge(2, 2, 88);

        var vertexes = expectedGraph.getVertexes();
        Assertions.assertDoesNotThrow(() -> vertexes.containsKey(1));
        Assertions.assertDoesNotThrow(() -> vertexes.containsKey(2));

        var startVertex = new Vertex<>(1);
        Assertions.assertNotEquals(vertexes.get(2), startVertex);
        Assertions.assertEquals(vertexes.get(1), startVertex);
    }

    @Test
    public void namesOfVertexesTest() {
        Graph<String> expectedGraph = new Graph<>();

        expectedGraph.addVertex("one");
        expectedGraph.addVertex("two");
        expectedGraph.addEdge("one", "two", 33);
        expectedGraph.addEdge("one", "two", 66);

        Assertions.assertNotEquals(expectedGraph.getEdge("one","two",33).getV1().getName(), "two");
        Assertions.assertEquals(expectedGraph.getEdge("one","two",33).getV1().getName(), "one");
    }
}
