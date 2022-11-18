package ru.nsu.mbogdanov2;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Student class for comfortable storing students information.
 */
public class StudentInformation {
    private Map<String, String> diplomaMarks = new HashMap<>(); //last marks in diploma
    private final Map<String, String> creditBookMarks = new HashMap<>(); //all marks in credit book

    /** Empty constructor for this class.
     *
     */
    public StudentInformation() {

    }

    /** Getter for diploma and credit book.
     *
     * @return credit book and diploma
     */
    public StudentInformation getStudentInformation() {
        return this;
    }

    /** Getter for credit book.
     *
     * @return credit book with marks and subjects
     */
    public Map<String, String> getCreditBookMarks() {
        return creditBookMarks;
    }

    /** Getter for diploma.
     *
     * @return diploma with marks and subjects
     */
    public Map<String, String> getDiplomaMarks() {
        return diplomaMarks;
    }

    /** Setter of diploma.
     *
     * @param diplomaMarks diploma with its subjects and marks
     */
    public void setDiplomaMarks(Map<String, String> diplomaMarks) {
        this.diplomaMarks = diplomaMarks;
    }

    /** Setter for one diploma subject and mark.
     *
     * @param subject string name of the subject
     * @param mark string mark
     */
    public void addDiplomaSubjectAndMark(String subject, String mark) {
        if (!checkDiplomaCorrectnessOfInformation(mark)) {
            throw new IllegalArgumentException("Такую оценку в "
                    + "диплом ставить нельзя");
        }
        diplomaMarks.put(subject, mark);
    }

    public String getDiplomaMarkForSubject(String subject) {
        if (!diplomaMarks.containsKey(subject)) {
            throw new IllegalArgumentException("Такого предмета в дипломе нет");
        }
        return diplomaMarks.get(subject);
    }

    public void addCreditBookMarkAndSubject(String subject, String mark) {
        if (!checkCreditBookCorrectnessOfInformation(mark)) {
            throw new IllegalArgumentException("Такую оценку в зачётную "
                    + "книгу нельзя писать");
        }
        creditBookMarks.put(subject, mark);
    }

    public String getCreditBookMarkForSubject(String subject) {
        if (!creditBookMarks.containsKey(subject)) {
            throw new IllegalArgumentException("Такого предмета в зачётной книги нет");
        }
        return creditBookMarks.get(subject);
    }

    public void deleteCreditBookMarkForSubject(String subject) {
        if (!creditBookMarks.containsKey(subject)) {
            throw new IllegalArgumentException("Неправильно название предмета"
                    + " зачетной книги для удаления");
        }
        creditBookMarks.remove(subject);
    }

    public void deleteDiplomaMarkForSubject(String subject) {
        if (!diplomaMarks.containsKey(subject)) {
            throw new IllegalArgumentException("Неправильно название "
                    + "дипломного предмета для удаления");
        }
        diplomaMarks.remove(subject);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof StudentInformation that)) {
            return false;
        }
        return Objects.equals(diplomaMarks, that.diplomaMarks)
                && Objects.equals(creditBookMarks, that.creditBookMarks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(diplomaMarks, creditBookMarks);
    }

    private static boolean checkCreditBookCorrectnessOfInformation(String name) {
        return name.equals("Отлично") || name.equals("Удовлетворительно") || name.equals("Хорошо") || name.equals("Зачёт");
    }

    private static boolean checkDiplomaCorrectnessOfInformation(String name) {
        return name.equals("Отлично") || name.equals("Удовлетворительно") || name.equals("Хорошо");
    }
}