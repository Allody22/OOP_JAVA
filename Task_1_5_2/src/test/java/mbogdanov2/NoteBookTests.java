package mbogdanov2;

import org.junit.jupiter.api.Test;

import java.text.ParseException;

public class NoteBookTests {
    @Test
    void addNoteTest() throws ParseException {
        NoteBook.main(new String[]{"-add", "Моя заметка", "Очень важная заметка"});
    }
    @Test
    void ShowNotesTest() throws ParseException {
        NoteBook.main(new String[]{"-show", "Dec 1, 2022, 1:50:04 PM", "Dec 29, 2022, 1:50:04 PM"});

    }
}
