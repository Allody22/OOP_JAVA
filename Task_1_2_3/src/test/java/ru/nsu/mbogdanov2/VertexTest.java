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
        Vertex<String> firstVertex = new Vertex<>("one");
        Assertions.assertEquals(firstVertex.getName(), "one");

        Vertex<String> secondVertex = new Vertex<>("two");
        Assertions.assertEquals(secondVertex.getName(), "two");

        Vertex<String> thirdVertex = new Vertex<>("three");
        Assertions.assertNotEquals(thirdVertex.getName(), "two");
        Assertions.assertEquals(thirdVertex.getName(), "three");

        Graph<String> expectedGraph = new Graph<>();
        expectedGraph.addVertex("one");
        expectedGraph.addVertex("two");
        expectedGraph.addEdge("one", "two", 33);
        expectedGraph.addEdge("one", "two", 66);

        Assertions.assertNotEquals(expectedGraph.getEdge("one", "two", 33)
                .getV1().getName(), "two");
        Assertions.assertEquals(expectedGraph.getEdge("one", "two", 33).getV1().getName(), "one");
    }

    @Test
    public void distanceOfVertexesTest() {
        Vertex<Integer> firstVertex = new Vertex<>(1);
        firstVertex.setDistance(3);
        Assertions.assertEquals(firstVertex.getDistance(), 3);

        Vertex<Integer> secondVertex = new Vertex<>(2);
        secondVertex.setDistance(4);
        Assertions.assertEquals(secondVertex.getDistance(), 4);

        Vertex<Integer> thirdVertex = new Vertex<>(3);
        thirdVertex.setDistance(5);
        Assertions.assertNotEquals(thirdVertex.getDistance(), 3);
        Assertions.assertEquals(thirdVertex.getDistance(), 5);

        Graph<String> expectedGraph = new Graph<>();
        expectedGraph.addVertex("one");
        expectedGraph.addVertex("two");
        expectedGraph.addVertex("three");
        expectedGraph.addEdge("one", "two", 10);
        expectedGraph.addEdge("two", "one", 11);

        Assertions.assertNull(expectedGraph.getVertex("one").getDistance());
        Assertions.assertNull(expectedGraph.getVertex("one").getDistance());

        expectedGraph.getVertex("one").setDistance(0);
        expectedGraph.getVertex("three").setDistance(Integer.MAX_VALUE);
        expectedGraph.getVertex("two").setDistance(Integer.MAX_VALUE);
        Assertions.assertEquals(expectedGraph.getVertex("one").getDistance(), 0);
        Assertions.assertEquals(expectedGraph.getVertex("three").getDistance(), Integer.MAX_VALUE);
        Assertions.assertEquals(expectedGraph.getVertex("two").getDistance(), Integer.MAX_VALUE);
    }

    @Test
    public void markOfVertexesTest() {
        Graph<Integer> expectedGraph = new Graph<>();

        expectedGraph.addVertex(0);
        expectedGraph.addVertex(1);
        expectedGraph.addVertex(2);
        expectedGraph.addEdge(0, 1, 10);
        expectedGraph.addEdge(2, 0, 11);
        expectedGraph.addEdge(2, 1, 11);

        var vertexes = expectedGraph.getVertexes();
        int expectedMark = 1;
        for (int i = 0; i < vertexes.size(); i++) {
            Assertions.assertEquals(vertexes.get(i).getMark(), expectedMark);
            vertexes.get(i).setMark(3);
        }

        var newVertexes = expectedGraph.getVertexes();
        int expectedNewMark = 3;
        for (int i = 0; i < newVertexes.size(); i++) {
            Assertions.assertEquals(expectedNewMark, newVertexes.get(i).getMark());
            vertexes.get(i).setMark(2);
        }

        var newVertexesLast = expectedGraph.getVertexes();
        int expectedLastMark = 2;
        for (int i = 0; i < newVertexes.size(); i++) {
            Assertions.assertEquals(expectedLastMark, newVertexesLast.get(i).getMark());
        }
    }
}
