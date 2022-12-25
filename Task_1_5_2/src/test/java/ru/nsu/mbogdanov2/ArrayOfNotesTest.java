package ru.nsu.mbogdanov2;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Class for testing array of notes.
 * I test it by comparing with java list util
 */
class ArrayOfNotesTest {

    @Test
    void removeNoteByTitle() throws IllegalAccessException {
        var notesArray = new ArrayOfNotes();
        notesArray.addNote(new Note("Title 1", "Description 1"));
        notesArray.addNote(new Note("Title 2", "Description 2"));
        notesArray.addNote(new Note("Title 4", "Description 4"));
        List<Note> notesArrayByJava = new ArrayList<>();
        notesArrayByJava.add(new Note("Title 1", "Description 1"));
        notesArrayByJava.add(new Note("Title 2", "Description 2"));
        notesArrayByJava.add(new Note("Title 3", "Description 3"));
        notesArray.removeNoteByTitle("Title 1");
        int expectedCoincidences = 1;
        int actualCoincidences = 0;
        for (var note : notesArrayByJava) {
            for (int i = 0; i < notesArray.getSize(); i++) {
                if (note.equals(notesArray.getById(i))) {
                    actualCoincidences++;
                }
            }
        }
        Assertions.assertEquals(expectedCoincidences, actualCoincidences);
    }

    @Test
    void addNote() {
        var notesArray = new ArrayOfNotes();
        notesArray.addNote(new Note("Title 1", "Description 1"));
        notesArray.addNote(new Note("Title 2", "Description 2"));
        notesArray.addNote(new Note("Title 4", "Description 4"));
        List<Note> notesArrayByJava = new ArrayList<>();
        notesArrayByJava.add(new Note("Title 1", "Description 1"));
        notesArrayByJava.add(new Note("Title 2", "Description 2"));
        notesArrayByJava.add(new Note("Title 3", "Description 3"));
        int expectedCoincidences = 2;
        int actualCoincidences = 0;
        for (var note : notesArrayByJava) {
            for (int i = 0; i < 3; i++) {
                if (note.equals(notesArray.getById(i))) {
                    actualCoincidences++;
                }
            }
        }
        Assertions.assertEquals(expectedCoincidences, actualCoincidences);
    }

    @Test
    void checkExistence() {
        var notesArray = new ArrayOfNotes();
        notesArray.addNote(new Note("Title 1", "Description 1"));
        notesArray.addNote(new Note("Title 2", "Description 2"));
        notesArray.addNote(new Note("Title 4", "Description 4"));
        Assertions.assertFalse(notesArray.checkExistence("Title 3"));
        Assertions.assertTrue(notesArray.checkExistence("Title 2"));
    }
}