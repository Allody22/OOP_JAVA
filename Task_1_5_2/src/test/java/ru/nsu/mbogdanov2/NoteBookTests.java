package ru.nsu.mbogdanov2;

import java.io.File;
import java.text.ParseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


/**
 * Test class with command options arguments.
 */
public class NoteBookTests {
    @Test
    void addNoteTest() throws ParseException, NoSuchFieldException, IllegalAccessException {
        File notes = new File("notes.json");
        if (notes.exists()) {
            notes.delete();
        }
        NoteBook.main(new String[]{"-add", "Моя заметка 111", "Очень важная заметка 1"});
        NoteBook.main(new String[]{"-add", "Моя заметка 222", "Очень важная заметка 2"});
        var notesField = NoteBook.class.getDeclaredField("notesArray");
        notesField.setAccessible(true);
        var arrayOfNotes = (ArrayOfNotes) notesField.get(notesField);
        Assertions.assertTrue(arrayOfNotes.checkExistence("Моя заметка 111"));
        Assertions.assertFalse(arrayOfNotes.checkExistence("Моя заметка 33"));
        notes.delete();
    }

    @Test
    void remoteNoteTest() throws ParseException, NoSuchFieldException, IllegalAccessException {
        File notes = new File("notes.json");
        if (notes.exists()) {
            notes.delete();
        }
        NoteBook.main(new String[]{"-add", "Моя заметка 1", "Очень важная заметка 1"});
        NoteBook.main(new String[]{"-add", "Моя заметка 2", "Очень важная заметка 2"});
        var notesField = NoteBook.class.getDeclaredField("notesArray");
        notesField.setAccessible(true);
        var arrayOfNotes = (ArrayOfNotes) notesField.get(notesField);
        Assertions.assertTrue(arrayOfNotes.checkExistence("Моя заметка 1"));
        NoteBook.main(new String[]{"-rm", "Моя заметка 1"});
        Assertions.assertFalse(arrayOfNotes.checkExistence("Моя заметка 1"));
        notes.delete();
    }

    @Test
    void remoteNoteExceptionTest() throws ParseException, NoSuchFieldException,
            IllegalAccessException {
        File notes = new File("notes.json");
        if (notes.exists()) {
            notes.delete();
        }
        NoteBook.main(new String[]{"-add", "Моя заметка 11", "Очень важная заметка 1"});
        NoteBook.main(new String[]{"-add", "Моя заметка 22", "Очень важная заметка 2"});
        var notesField = NoteBook.class.getDeclaredField("notesArray");
        notesField.setAccessible(true);
        NoteBook.main(new String[]{"-rm", "Нет такой заметки"});
        var arrayOfNotes = (ArrayOfNotes) notesField.get(notesField);
        Assertions.assertFalse(arrayOfNotes.checkExistence("Нет такой заметки"));
        notes.delete();
    }
}
