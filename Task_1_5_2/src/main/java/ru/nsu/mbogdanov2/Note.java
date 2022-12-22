package ru.nsu.mbogdanov2;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

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
}
