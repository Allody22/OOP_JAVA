package ru.nsu.mbogdanov2;

import java.util.HashMap;
import java.util.Map;

/**
 * Student class for comfortable storing students information.
 */
public class StudentInformation {
    private Map<String, String> diplomaMarks = new HashMap<>(); //last marks in diploma
    private Map<String, String> creditBookMarks = new HashMap<>(); //all marks in credit book
    private double averageMark;
    private boolean redDiploma;
    private boolean scholarship;
    private boolean highScholarship;

    public StudentInformation() {

    }

    public StudentInformation(Map<String, String> diplomaMarks, Map<String, String> creditBookMarks) {
        this.diplomaMarks = diplomaMarks;
        this.creditBookMarks = creditBookMarks;
    }

    public StudentInformation(Map<String, String> diplomaMarks, boolean redDiploma, boolean scholarship, boolean highScholarship, boolean qualificationWork) {
        this.diplomaMarks = diplomaMarks;
        this.redDiploma = redDiploma;
        this.scholarship = scholarship;
        this.highScholarship = highScholarship;
    }

    public Map<String, String> getCreditBookMarks() {
        return creditBookMarks;
    }

    public void setAverageMark() {
        if (creditBookMarks.size() == 0) {
            throw new IllegalArgumentException("Зачетная книжка пустая");
        }
        this.averageMark = 0;
        for (var creditBookMark : creditBookMarks.values()) {
            switch (creditBookMark) {
                case "Удовлетворительно" -> averageMark += 3;
                case "Хорошо" -> averageMark += 4;
                case "Отлично" -> averageMark += 5;
            }
        }
        this.averageMark /= creditBookMarks.size();
    }

    public double getAverageMark() {
        return averageMark;
    }

    public boolean isScholarship() {
        return scholarship;
    }

    public boolean isRedDiploma() {
        return redDiploma;
    }

    public boolean isHighScholarship() {
        return highScholarship;
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

    public void setHighScholarship(boolean highScholarship) {
        this.highScholarship = highScholarship;
    }

    public void setRedDiploma(boolean redDiploma) {
        this.redDiploma = redDiploma;
    }

    public void setScholarship(boolean scholarship) {
        this.scholarship = scholarship;
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