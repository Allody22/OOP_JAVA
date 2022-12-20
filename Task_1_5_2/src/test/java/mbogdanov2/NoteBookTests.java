package mbogdanov2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.text.ParseException;
import java.util.Objects;

public class NoteBookTests {
    @Test
    void addNoteTest() throws ParseException, NoSuchFieldException, IllegalAccessException {
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
        Assertions.assertFalse(arrayOfNotes.checkExistence("Моя заметка 33"));
        notes.delete();
    }

    @Test
    void RemoteNoteTest() throws ParseException, NoSuchFieldException, IllegalAccessException {
        File notes = new File("notes.json");
        if (notes.exists()) {
            Assertions.assertTrue(notes.delete());
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
}
