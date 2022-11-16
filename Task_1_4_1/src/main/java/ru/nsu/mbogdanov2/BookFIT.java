package ru.nsu.mbogdanov2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BookFIT {
    private Map<String, StudentInformation> creditBook = new HashMap<>();

    public BookFIT() {

    }

    public BookFIT(String nameOfTheStudent) {
        creditBook.put(nameOfTheStudent, new StudentInformation());
    }

    public BookFIT(ArrayList<String> namesOfTheStudents) {
        for (String namesOfTheStudent : namesOfTheStudents) {
            creditBook.put(namesOfTheStudent, new StudentInformation());
        }
    }

    public BookFIT(String nameOfTheStudent, StudentInformation studentData) {
        creditBook.put(nameOfTheStudent, studentData);
    }

    public BookFIT(ArrayList<String> nameOfTheStudents, ArrayList<StudentInformation> studentsData) {
        if (nameOfTheStudents.size() != studentsData.size()) {
            throw new IllegalArgumentException("Please, give correct information");
        }
        for (int i = 0; i < nameOfTheStudents.size(); i++) {
            creditBook.put(nameOfTheStudents.get(i), studentsData.get(i));
        }
    }

    public Map<String, StudentInformation> getCreditBook() {
        return creditBook;
    }

    public void setCreditBook(Map<String, StudentInformation> creditBook) {
        this.creditBook = creditBook;
    }

    public void addStudent(String name) {
        creditBook.put(name, new StudentInformation());
    }

    public void addDiplomaSubjectAndMark(String name, String subject, String mark) {
        creditBook.get(name).addDiplomaSubjectAndMark(subject, mark);
    }

    public void addCreditBookSubjectAndMark(String name, String subject, String mark) {
        creditBook.get(name).addCreditBookMarkAndSubject(subject, mark);
    }

    public boolean containsStudent(String name) {
        return creditBook.containsKey(name);
    }

    public boolean checkScholarship(String name) {
        if (!creditBook.containsKey(name)) {
            throw new IllegalArgumentException("No student with this name");
        }
        if (creditBook.get(name).getDiplomaMarks().containsValue("Три")
                || creditBook.get(name).getDiplomaMarks().containsValue("Пересдача")) {
            return false;
        }
        creditBook.get(name).setScholarship(true);
        return true;
    }

    public boolean checkRedDiploma(String name) {
        if (!creditBook.containsKey(name)) {
            throw new IllegalArgumentException("No student with this name");
        }
        if (creditBook.get(name).getCreditBookMarks().containsValue("Удовлетворительно")
                || creditBook.get(name).getCreditBookMarks().containsValue("Пересдача")
                || !(creditBook.get(name).getDiplomaMarkForSubject("Квалификационная работа")
                .equals("Отлично"))
                || checkDiploma(name) < 75) {
            creditBook.get(name).setRedDiploma(false);
            return false;
        }
        creditBook.get(name).setRedDiploma(true);
        return true;
    }

    public boolean checkHighScholarship(String name) {
        if (!creditBook.containsKey(name)) {
            throw new IllegalArgumentException("No student with this name");
        }
        if (checkDiploma(name) == 100) {
            creditBook.get(name).setHighScholarship(true);
            return false;
        }
        creditBook.get(name).setHighScholarship(true);
        return true;
    }

    public void printFullStudentInformation(String name) {
        if (!creditBook.containsKey(name)) {
            throw new IllegalArgumentException("No student with this name");
        }
        System.out.println("ФИО СТУДЕНТА:" + name);
        System.out.println("----------------------------------");
        System.out.println("Оценки в зачетной книжке студента:");
        for (var creditBookMarks : creditBook.get(name).getCreditBookMarks().entrySet()) {
            System.out.println(creditBookMarks.getKey() + " - " + creditBookMarks.getValue());
        }
        System.out.println("----------------------------------");
        System.out.println("Оценки в дипломе студента:");
        for (var creditBookMarks : creditBook.get(name).getDiplomaMarks().entrySet()) {
            System.out.println(creditBookMarks.getKey() + " - " + creditBookMarks.getValue());
        }
        System.out.println("Средняя оценка студента" + creditBook.get(name).getAverageMark());
        System.out.println("Наличие стипендии у студента" + creditBook.get(name).isScholarship());
        System.out.println("Наличие повышенной стипендии у студента"
                + creditBook.get(name).isHighScholarship());
        System.out.println("Наличие красного диплома у студента" + creditBook.get(name).isRedDiploma());
    }

    private double checkDiploma(String name) {
        var currentDiplomaMarks = creditBook.get(name).getDiplomaMarks();
        double bestMarkPercent = 0;
        for (var mark : currentDiplomaMarks.values()) {
            if (mark.equals("Отлично")) {
                bestMarkPercent++;
            }
        }
        return (bestMarkPercent / currentDiplomaMarks.size()) * 100;
    }

}