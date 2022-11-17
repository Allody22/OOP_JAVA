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

    public BookFIT(ArrayList<String> nameOfTheStudents,
                   ArrayList<StudentInformation> studentsData) {
        if (nameOfTheStudents.size() != studentsData.size()) {
            throw new IllegalArgumentException("Количество имён студентов не совпадает " +
                    "с количеством информации о студентах");
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
        return !creditBook.get(name).getDiplomaMarks().containsValue("Три")
                && !creditBook.get(name).getDiplomaMarks().containsValue("Пересдача");
    }

    public boolean checkRedDiploma(String name) {
        if (!creditBook.containsKey(name)) {
            throw new IllegalArgumentException("No student with this name");
        }
        return !creditBook.get(name).getCreditBookMarks().containsValue("Удовлетворительно")
                && !creditBook.get(name).getCreditBookMarks().containsValue("Пересдача")
                && creditBook.get(name).getDiplomaMarkForSubject("Квалификационная работа")
                .equals("Отлично")
                && !(checkDiploma(name, "Приложение к диплому") < 75);
    }

    public boolean checkHighScholarship(String name) {
        if (!creditBook.containsKey(name)) {
            throw new IllegalArgumentException("No student with this name");
        }
        return checkDiploma(name, "Зачётная книжка") == 100;
    }

    public int getAverageMark(String name) {
        var currentCreditBook = creditBook.get(name).getCreditBookMarks();
        if (currentCreditBook.size() == 0) {
            throw new IllegalArgumentException("Зачетная книжка пустая");
        }
        int averageMark = 0;
        for (var currentMark : currentCreditBook.entrySet()) {
            switch (currentMark.getValue()) {
                case "Удовлетворительно" -> averageMark += 3;
                case "Хорошо" -> averageMark += 4;
                case "Отлично" -> averageMark += 5;
            }
        }
        return averageMark / currentCreditBook.size();
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

    private double checkDiploma(String name, String interestedBook) {
        Map<String, String> currentDiplomaMarks = null;
        switch (interestedBook) {
            case "Зачётная книжка" -> currentDiplomaMarks = creditBook.get(name)
                    .getDiplomaMarks();
            case "Приложение к диплому" ->
                    currentDiplomaMarks = creditBook.get(name).getCreditBookMarks();
        }
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

}