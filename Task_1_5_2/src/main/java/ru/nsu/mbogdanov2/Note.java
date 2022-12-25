package ru.nsu.mbogdanov2;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.Objects;

/**
 * Note class with every needed for note information.
 */
public class Note {
    @SerializedName("Title")
    private String title;
    @SerializedName("Description")
    private String description;
    @SerializedName("TimeOfOrder")
    private Date timeOfNote;

    /**
     * Note constructor that remembers the time of the note.
     *
     * @param title       string name of the note
     * @param description string description of the note
     */
    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        setTimeOfNote();
    }

    @Override
    public String toString() {
        return "Note {"
                + "title = '" + title
                + "', description = '" + description
                + "', time of this note = '" + timeOfNote
                + "' }";
    }

    public String getTitle() {
        return title;
    }

    public Date getTimeOfNote() {
        return timeOfNote;
    }


    public void setTimeOfNote() {
        timeOfNote = new Date();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Note note = (Note) o;
        return Objects.equals(title, note.title) && Objects.equals(description, note.description) && Objects.equals(timeOfNote, note.timeOfNote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, timeOfNote);
    }
}
