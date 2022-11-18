package ru.nsu.mbogdanov2;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Main class that realize FIT credit book.
 * It has a lot of basic functions: add student, add marks,
 * check different information about student knowledge
 */
public class BookFit {
    private final Map<String, StudentInformation> electronicCreditBook = new HashMap<>();

    public BookFit() {

    }

    /**
     * Constructor for main class with one student name.
     *
     * @param nameOfTheStudent string name of the student
     */
    public BookFit(String nameOfTheStudent) {
        electronicCreditBook.put(nameOfTheStudent, new StudentInformation());
    }

    /**
     * Constructor for electronic book with student and his information.
     *
     * @param nameOfTheStudent string name of the student
     * @param studentData      student credit book and diploma marks
     */
    public BookFit(String nameOfTheStudent, StudentInformation studentData) {
        electronicCreditBook.put(nameOfTheStudent, studentData);
    }

    /**
     * Method to add new diploma and credit book for existing student.
     *
     * @param name               string name of the student
     * @param studentInformation student credit book and his diploma marks
     */
    public void addStudentInformation(String name, StudentInformation studentInformation) {
        if (!electronicCreditBook.containsKey(name)) {
            throw new IllegalArgumentException("No student with this name");
        }
        electronicCreditBook.put(name, studentInformation);
    }

    /**
     * Getter method for diploma and credit book.
     *
     * @param name string name of the student
     * @return diploma and credit book of the student
     */
    public StudentInformation getStudentInformation(String name) {
        return electronicCreditBook.get(name).getStudentInformation();
    }

    /**
     * Adding of new student without credit book and diploma.
     *
     * @param name string name of the student
     */
    public void addStudent(String name) {
        electronicCreditBook.put(name, new StudentInformation());
    }

    /**
     * Adding of new diploma subject with its mark
     *
     * @param name    string name of the student
     * @param subject string name of the subject
     * @param mark    string mark
     */
    public void addDiplomaSubjectAndMark(String name, String subject, String mark) {
        electronicCreditBook.get(name).addDiplomaSubjectAndMark(subject, mark);
    }

    /**
     * Adding of new credit book subject with its mark
     *
     * @param name    string name of the student
     * @param subject string name of the subject
     * @param mark    string mark
     */
    public void addCreditBookSubjectAndMark(String name, String subject, String mark) {
        electronicCreditBook.get(name).addCreditBookMarkAndSubject(subject, mark);
    }

    /**
     * Deleting of credit book subject and mark.
     * I don't if this possible
     *
     * @param name    string name of the student
     * @param subject string name of the subject
     */
    public void deleteCreditBookSubjectAndMark(String name, String subject) {
        electronicCreditBook.get(name).deleteCreditBookMarkForSubject(subject);
    }

    /**
     * Checking of students scholarship.
     * If student doesn't have bad marks in his credit book
     * Then he has scholarship
     *
     * @param name name of the student
     * @return true if student has scholarship or false
     */
    public boolean checkScholarship(String name) {
        if (!electronicCreditBook.containsKey(name)) {
            throw new IllegalArgumentException("No student with this name");
        }
        return !electronicCreditBook.get(name).getCreditBookMarks().containsValue("Удовлетворительно");
    }

    /**
     * Checking of students red diploma.
     * If student has only Хорошо or Отлично marks and Отлично mark for Квалификационную работу
     * Then he has red diploma
     *
     * @param name name of the student
     * @return true if student has red diploma or false
     */
    public boolean checkRedDiploma(String name) {
        if (!electronicCreditBook.containsKey(name)) {
            throw new IllegalArgumentException("No student with this name");
        }
        return !electronicCreditBook.get(name).getCreditBookMarks().containsValue("Удовлетворительно")
                && electronicCreditBook.get(name).getDiplomaMarkForSubject("Квалификационная_работа")
                .equals("Отлично")
                && !(checkPercentage(name, "Приложение к диплому") < 75);
    }

    /**
     * Checking of high scholarship.
     * If student has only Отлично marks in his credit book
     * Then he has high scholarship
     *
     * @param name string student name
     * @return true if student has high scholarship or false in another way
     */
    public boolean checkHighScholarship(String name) {
        if (!electronicCreditBook.containsKey(name)) {
            throw new IllegalArgumentException("No student with this name");
        }
        return checkPercentage(name, "Зачётная книжка") == 100;
    }

    /**
     * Getter of students average mark.
     * This value has 3 numbers after dot name by decimal format class
     *
     * @param name name of the student
     * @return string average mark value
     */
    public String getAverageMark(String name) {
        var currentCreditBook = electronicCreditBook.get(name).getCreditBookMarks();
        if (currentCreditBook.size() == 0) {
            throw new IllegalArgumentException("Зачетная книжка пустая");
        }
        double averageMark = 0;
        for (var currentMark : currentCreditBook.entrySet()) {
            switch (currentMark.getValue()) {
                case "Удовлетворительно" -> averageMark += 3;
                case "Хорошо" -> averageMark += 4;
                case "Отлично" -> averageMark += 5;
                default -> throw new
                        IllegalArgumentException("В зачётной книжке написана ерунда");
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.###");
        return decimalFormat.format(averageMark / currentCreditBook.size());
    }

    /** Method for printing all information about student.
     * It may be useless and I can easily delete this
     *
     * @param name student name
     */
    public void printFullStudentInformation(String name) {
        if (!electronicCreditBook.containsKey(name)) {
            throw new IllegalArgumentException("No student with this name");
        }
        System.out.println("ФИО СТУДЕНТА:" + name);
        System.out.println("----------------------------------");
        System.out.println("Оценки в зачетной книжке студента:");
        for (var creditBookMarks : electronicCreditBook.get(name).getCreditBookMarks().entrySet()) {
            System.out.println(creditBookMarks.getKey() + " - " + creditBookMarks.getValue());
        }
        System.out.println("----------------------------------");
        System.out.println("Оценки в дипломе студента:");
        for (var creditBookMarks : electronicCreditBook.get(name).getDiplomaMarks().entrySet()) {
            System.out.println(creditBookMarks.getKey() + " - " + creditBookMarks.getValue());
        }
        System.out.println("----------------------------------");
        System.out.println("Средняя оценка студента" + getAverageMark(name));
        System.out.println("----------------------------------");
        System.out.println("Наличие стипендии у студента" + checkScholarship(name));
        System.out.println("----------------------------------");
        System.out.println("Наличие повышенной стипендии у студента"
                + checkHighScholarship(name));
        System.out.println("----------------------------------");
        System.out.println("Наличие красного диплома у студента" + checkRedDiploma(name));
        System.out.println("----------------------------------");
        System.out.println("Это вся информация о студенте");
    }

    /** Function to help us to count percentage of the best mark to all.
     * It is needed for red diploma and high scholarship checks
     *
     * @param name name of the student
     * @param interestedBook credit or diploma book
     * @return percentage ratio of the best mark to all
     */
    private double checkPercentage(String name, String interestedBook) {
        Map<String, String> currentDiplomaMarks = switch (interestedBook) {
            case "Зачётная книжка" -> electronicCreditBook.get(name)
                    .getCreditBookMarks();
            case "Приложение к диплому" -> electronicCreditBook.get(name).getDiplomaMarks();
            default -> throw new IllegalArgumentException("Неверные входные данные");
        };
        if (currentDiplomaMarks == null || currentDiplomaMarks.size() == 0) {
            throw new IllegalArgumentException("Нету оценок в данной книге");
        }
        double bestMarkPercent = 0;
        for (var mark : currentDiplomaMarks.values()) {
            if (mark.equals("Отлично")) {
                bestMarkPercent++;
            }
        }
        return (bestMarkPercent / currentDiplomaMarks.size()) * 100;
    }

    /** Overriding of equals method to compare different electronic books.
     *
     * @param o object
     * @return true if our objects are equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookFit bookFit)) return false;
        return Objects.equals(electronicCreditBook, bookFit.electronicCreditBook);
    }

    /** Overriding of hash code function to help us to compare different books.
     *
     * @return hash code of the objects
     */
    @Override
    public int hashCode() {
        return Objects.hash(electronicCreditBook);
    }
}