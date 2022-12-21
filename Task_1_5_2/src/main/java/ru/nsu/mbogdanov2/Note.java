package ru.nsu.mbogdanov2;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Note {
    @SerializedName("Title")
    private String title;
    @SerializedName("Description")
    private String description;
    @SerializedName("TimeOfOrder")
    private Date timeOfNote;

    public Note(String title, String description) {
        this.title = title;
        this.description = description;
        setTimeOfNote();
    }

    @Override
    public String toString() {
        return "Note {" +
                "title = '" + title +
                "', description = '" + description +
                "', time of this note = '" + timeOfNote +
                "' }";
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
