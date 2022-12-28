package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Class for testing notes.
 * Nothing interesting.
 */
class NoteTest {

    @Test
    void getTitle() {
        var firstNote = new Note("Title 1", "Description 1");
        var secondNote = new Note("Title 2", "Description 2");
        Assertions.assertEquals("Title 1", firstNote.getTitle());
        Assertions.assertNotEquals("Title 1", secondNote.getTitle());
    }

    @Test
    void testEquals() {
        var firstNote = new Note("Title 1", "Description 1");
        var secondNote = new Note("Title 1", "Description 1");
        var thirdNote = new Note("Title 11", "Description 1");
        Assertions.assertEquals(secondNote, firstNote);
        Assertions.assertNotEquals(secondNote, thirdNote);
        Assertions.assertNotEquals(firstNote, thirdNote);
    }

    @Test
    void toStringTest() {
        var firstNote = new Note("Title 1", "Description 1");
        var secondNote = new Note("Title 1", "Description 1");
        var thirdNote = new Note("Title 11", "Description 1");
        Assertions.assertEquals(secondNote.toString(), firstNote.toString());
        Assertions.assertNotEquals(secondNote.toString(), thirdNote.toString());
        Assertions.assertNotEquals(firstNote.toString(), thirdNote.toString());
    }
}