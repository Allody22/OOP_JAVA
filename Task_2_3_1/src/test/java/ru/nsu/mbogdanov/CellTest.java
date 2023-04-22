package ru.nsu.mbogdanov;

import org.junit.jupiter.api.Test;
import ru.nsu.mbogdanov.model.environment.Cell;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CellTest {

    @Test
    void testIntersects() {
        Cell cell1 = new Cell(10, 10);
        cell1.setPosition(0, 0);
        Cell cell2 = new Cell(10, 10);
        cell2.setPosition(5, 5);
        assertTrue(cell1.intersects(cell2));
    }

    @Test
    void testNotIntersects() {
        Cell cell1 = new Cell(10, 10);
        cell1.setPosition(0, 0);
        Cell cell2 = new Cell(10, 10);
        cell2.setPosition(11, 11);
        assertFalse(cell1.intersects(cell2));
    }

    @Test
    void testEqualsWithDifferentWidth() {
        Cell cell1 = new Cell(10, 10);
        cell1.setPosition(0, 0);
        Cell cell2 = new Cell(15, 10);
        cell2.setPosition(0, 0);
        assertNotEquals(cell1, cell2);
    }

    @Test
    void testEqualsWithDifferentHeight() {
        Cell cell1 = new Cell(10, 10);
        cell1.setPosition(0, 0);
        Cell cell2 = new Cell(10, 15);
        cell2.setPosition(0, 0);
        assertNotEquals(cell1, cell2);
    }

    @Test
    void testEqualsWithDifferentCoordinates() {
        Cell cell1 = new Cell(10, 10);
        cell1.setPosition(0, 0);
        Cell cell2 = new Cell(10, 10);
        cell2.setPosition(5, 5);
        assertNotEquals(cell1, cell2);
    }

    @Test
    void testEqualsWithSameObject() {
        Cell cell1 = new Cell(10, 10);
        cell1.setPosition(0, 0);
        assertEquals(cell1, cell1);
    }

    @Test
    void testEquals() {
        Cell cell1 = new Cell(10, 10);
        cell1.setPosition(0, 0);
        Cell cell2 = new Cell(10, 10);
        cell2.setPosition(0, 0);
        assertEquals(cell1, cell2);
    }

    @Test
    void testNotEquals() {
        Cell cell1 = new Cell(10, 10);
        cell1.setPosition(0, 0);
        Cell cell2 = new Cell(10, 10);
        cell2.setPosition(0, 5);
        assertNotEquals(cell1, cell2);
    }
}
