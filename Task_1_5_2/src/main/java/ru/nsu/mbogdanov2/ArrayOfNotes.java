package ru.nsu.mbogdanov2;


import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to store notes in one list.
 * Gson can work with another class with list, but has problems with
 */
public class ArrayOfNotes {
    @SerializedName("Array Of Notes")
    private List<Note> notes;
    int size;

    public ArrayOfNotes() {
        notes = new ArrayList<>();
        size = 0;
    }

    /**
     * Simple method to remote note by name.
     *
     * @param title name of the note
     * @throws IllegalAccessException if there are no such note
     */
    public void removeNoteByTitle(String title) throws IllegalAccessException {
        for (var note : notes) {
            if (note.getTitle().equals(title)) {
                notes.remove(note);
                size--;
                return;
            }
        }
        throw new IllegalAccessException("No note with this title");
    }

    public void addNote(Note note) {
        this.notes.add(note);
        size++;
    }

    public Note getById(int id) {
        return notes.get(id);
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "ArrayOfNotes{"
                + "note=" + notes
                + '}';
    }

    /**
     * Method for more comfortable storing of notes.
     *
     * @return string representation of notes
     */
    public String stringify() {
        String stringNotes = " ";
        for (int i = 0; i < this.size; i++) {
            var currentNote = getById(i);
            stringNotes = stringNotes.concat(currentNote.toString());
            stringNotes = stringNotes.concat("\n");
        }
        return stringNotes;
    }

    /**
     * We are trying to find note by title.
     *
     * @param title string name of the note
     * @return false if there are no such note
     */
    public boolean checkExistence(String title) {
        for (var note : notes) {
            if (note.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
}