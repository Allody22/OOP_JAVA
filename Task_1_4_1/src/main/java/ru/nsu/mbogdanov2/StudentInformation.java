package ru.nsu.mbogdanov2;

import java.util.HashMap;
import java.util.Map;

/**
 * Student class for comfortable storing students information.
 */
public class StudentInformation {
    private Map<String, String> diplomaMarks = new HashMap<>(); //last marks in diploma
    private Map<String, String> creditBookMarks = new HashMap<>(); //all marks in credit book

    public StudentInformation() {

    }

    public StudentInformation(Map<String, String> diplomaMarks,
                              Map<String, String> creditBookMarks) {
        this.diplomaMarks = diplomaMarks;
        this.creditBookMarks = creditBookMarks;
    }

    public Map<String, String> getCreditBookMarks() {
        return creditBookMarks;
    }

    public Map<String, String> getDiplomaMarks() {
        return diplomaMarks;
    }

    public void setDiplomaMarks(Map<String, String> diplomaMarks) {
        this.diplomaMarks = diplomaMarks;
    }

    public void setCreditBookMarks(Map<String, String> creditBookMarks) {
        this.creditBookMarks = creditBookMarks;
    }

    public void addDiplomaSubjectAndMark(String subject, String mark) {
        diplomaMarks.put(subject, mark);
    }

    public String getDiplomaMarkForSubject(String subject) {
        return diplomaMarks.get(subject);
    }

    public void addCreditBookMarkAndSubject(String subject, String mark) {
        creditBookMarks.put(subject, mark);
    }

    public String getCreditBookMarkForSubject(String subject) {
        return creditBookMarks.get(subject);
    }
}