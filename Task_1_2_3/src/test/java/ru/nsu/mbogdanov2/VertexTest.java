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
}
