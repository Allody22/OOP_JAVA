package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**Class for testing common edge methods.
 */
public class EdgeTest {
    @Test
    public void edgeEqualsTest() {
        Graph<Integer> expectedGraph = new Graph<>();
        var startVertex = new Vertex<>(1);
        var secondVertex = new Vertex<>(2);

        expectedGraph.addVertex(1);
        expectedGraph.addVertex(2);
        expectedGraph.addEdge(1, 2, 33);
        Edge<Integer> expectedEdge = new Edge<>(startVertex, secondVertex, 33);

        Assertions.assertEquals(expectedGraph.getEdge(1, 2, 33), expectedEdge);
    }

    @Test
    public void deleteEdgeTest() {
        Graph<String> expectedGraph = new Graph<>();
        var startVertex = new Vertex<>("one");
        var secondVertex = new Vertex<>("two");

        expectedGraph.addVertex("one");
        expectedGraph.addVertex("two");
        expectedGraph.addEdge("one", "two", 33);
        expectedGraph.addEdge("one", "two", 66);
        var actualDeletedEdge = expectedGraph.deleteEdge("one", "two", 33);

        var expectedEdge = new Edge<>(startVertex, secondVertex, 33);
        Assertions.assertEquals(actualDeletedEdge, expectedEdge);
    }

    @Test
    public void addEdgeTest() {
        Graph<Integer> expectedGraph = new Graph<>();
        var startVertex = new Vertex<>(1);
        var secondVertex = new Vertex<>(2);

        expectedGraph.addVertex(2);
        expectedGraph.addVertex(1);

        Edge<Integer> expectedEdge = new Edge<>(startVertex, secondVertex, 33);
        Assertions.assertDoesNotThrow(() -> expectedGraph.addEdge(1, 2, 33));
        Assertions.assertDoesNotThrow(() -> expectedGraph.addEdge(2, 1, 43));
        Assertions.assertEquals(expectedGraph.getEdge(1, 2, 33), expectedEdge);
    }

    @Test
    public void getMapOfEdgeTest() {
        Graph<Integer> expectedGraph = new Graph<>();

        expectedGraph.addVertex(1);
        expectedGraph.addVertex(2);
        expectedGraph.addVertex(3);
        expectedGraph.addEdge(1, 2, 33);
        expectedGraph.addEdge(2, 3, 44);
        expectedGraph.addEdge(2, 2, 88);

        var edges = expectedGraph.getEdges();
        Assertions.assertDoesNotThrow(() -> edges.containsKey(1));
        Assertions.assertDoesNotThrow(() -> edges.containsKey(2));

        var startVertex = new Vertex<>(1);
        var secondVertex = new Vertex<>(2);
        Edge<Integer> actualEdge = new Edge<>(startVertex ,secondVertex, 33);
        Assertions.assertEquals(edges.get(1).get(0), actualEdge);
    }

    @Test
    public void weightsOfEdgesTest() {
        Graph<String> expectedGraph = new Graph<>();

        expectedGraph.addVertex("one");
        expectedGraph.addVertex("two");
        expectedGraph.addEdge("one", "two", 33);
        expectedGraph.addEdge("one", "two", 66);

        Assertions.assertNotEquals(expectedGraph.getEdge("one","two",33).getWeight(), 32);
        Assertions.assertEquals(expectedGraph.getEdge("one","two",33).getWeight(), 33);
    }
}
