package ru.nsu.mbogdanov2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Main class that realize FIT electronic credit book.
 * It has a lot of basic functions: add student, add marks,
 * check different information about student knowledge
 */
public class ElectronicStudentBook {
    public Map<String, List<StudentDataForOneSubject>> electronicBook = new HashMap<>();

    /**
     * Constructor to create this electronic book.
     */
    public ElectronicStudentBook() {

    }

    /**
     * Method to add new student without any information.
     *
     * @param name name of the student
     */
    public void addStudent(String name) {
        this.electronicBook.put(name, new ArrayList<>());
    }

    /**
     * Adding of information about one subject.
     * It has mark spelling filter and checking of student existence
     *
     * @param name           name of the student
     * @param oneSubjectInfo information about one passed subject exam
     */
    public void addStudentInfoAboutOneSubject(String name, StudentDataForOneSubject oneSubjectInfo) {
        checkCorrectnessOfMark(oneSubjectInfo.mark());

        if (!electronicBook.containsKey(name)) {
            throw new IllegalArgumentException("Такого студента нет");
        }
        electronicBook.get(name).add(oneSubjectInfo);
    }

    /**
     * Adding a lot of new information about student passed exams.
     * It has mark spelling filter and checking of student existence
     *
     * @param name        name of the student
     * @param information list with student passed exams
     */
    public void addInformationAboutStudent(String name, ArrayList<StudentDataForOneSubject> information) {
        if (!electronicBook.containsKey(name)) {
            throw new IllegalArgumentException("Такого студента нет");
        }
        for (var studentData : information) {
            checkCorrectnessOfMark(studentData.mark());
            electronicBook.get(name).add(studentData);
        }
    }

    /**
     * Getter for map value.
     *
     * @param name name of the student
     * @return list of student passed exam
     */
    public List<StudentDataForOneSubject> getStudentInfo(String name) {
        if (!electronicBook.containsKey(name)) {
            throw new IllegalArgumentException("Такого студента нет");
        }
        return electronicBook.get(name);
    }

    /**
     * Function to check whether student has scholarship or no.
     *
     * @param name name of the student
     * @return true if student has scholarship
     */
    public boolean checkScholarShip(String name) {
        if (!electronicBook.containsKey(name)) {
            throw new IllegalArgumentException("Такого студента нет");
        }
        int maxSemester = 0;
        for (var studentData : getStudentInfo(name)) {
            if (studentData.semester() > maxSemester) {
                maxSemester = studentData.semester();
            }
        }
        for (var studentData : getStudentInfo(name)) {
            if (studentData.semester() == maxSemester
                    && !(studentData.mark().equals("Отлично") || studentData.mark().equals("Хорошо"))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Function to check whether student has high scholarship or no.
     * If the student got only "Отлично" marks on the last session
     * Then he has high scholarship
     *
     * @param name name of the student
     * @return true if student has scholarship
     */
    public boolean checkHighScholarShip(String name) {
        if (!electronicBook.containsKey(name)) {
            throw new IllegalArgumentException("Такого студента нет");
        }
        int maxSemester = 0;
        for (var studentData : getStudentInfo(name)) {
            if (studentData.semester() > maxSemester) {
                maxSemester = studentData.semester();
            }
        }
        for (var studentData : getStudentInfo(name)) {
            if (studentData.semester() == maxSemester && !studentData.mark().equals("Отлично")) {
                return false;
            }
        }
        return true;
    }

    /**
     * Getter method for student average mark of the whole time.
     *
     * @param name name of the student
     * @return average mark itself
     */
    public double getAverageMark(String name) {
        if (!electronicBook.containsKey(name)) {
            throw new IllegalArgumentException("Такого студента нет");
        }
        if (electronicBook.get(name).size() == 0) {
            throw new IllegalArgumentException("У этого студента пока нет оценок");
        }
        double averageMark = 0;
        int creditsWithoutMarks = 0;
        for (var currentMarks : getStudentInfo(name)) {
            switch (currentMarks.mark()) {
                case "Удовлетворительно" -> averageMark += 3;
                case "Хорошо" -> averageMark += 4;
                case "Отлично" -> averageMark += 5;
                case "Зачёт" -> creditsWithoutMarks++;
                default -> throw new
                        IllegalArgumentException("В зачётной книжке написана ерунда");
            }
        }
        if (creditsWithoutMarks == getStudentInfo(name).size()) {
            throw new IllegalArgumentException("У этого студента нету оценок, только зачёты");
        }
        return (Math.floor((averageMark / (getStudentInfo(name).size() - creditsWithoutMarks)) * 1000)) / 1000;
    }

    /**
     * Function to check whether student has high scholarship or no.
     *
     * @param name name of the student
     * @return true if student has scholarship
     */
    public boolean checkRedDiploma(String name) {
        if (!electronicBook.containsKey(name)) {
            throw new IllegalArgumentException("Такого студента нет");
        }
        if (electronicBook.get(name).size() == 0) {
            throw new IllegalArgumentException("У этого студента пока нет оценок");
        }
        if (!checkConditions(name)) {
            return false;
        }
        int workFlag = 0;
        for (var studentData : getStudentInfo(name)) {
            if (studentData.subject().equals("Квалификационная_работа")) {
                workFlag++;
                if (!studentData.mark().equals("Отлично")) {
                    return false;
                }
            }
        }
        return workFlag == 1;
    }

    /**
     * This function help us to get information about student red diploma.
     *
     * @param name name of the student
     * @return true if student can apply for an increased scholarship
     */
    private boolean checkConditions(String name) {
        double bestMarkPercent = 0;
        for (var mark : electronicBook.get(name)) {
            if (checkIfMarkLast(mark, name)) {
                if (mark.mark().equals("Удовлетворительно")) {
                    return false;
                }
                if (mark.mark().equals("Отлично") && checkIfMarkLast(mark, name)) {
                    bestMarkPercent++;
                }
            }
        }
        return (bestMarkPercent / electronicBook.get(name).size()) * 100 >= 75;
    }

    /**
     * Function to check whether it is the last semester of this subject exam.
     *
     * @param studentData student exam information
     * @param name        name of the student
     * @return true if this exam was last
     */
    private boolean checkIfMarkLast(StudentDataForOneSubject studentData, String name) {
        int currentSemester = studentData.semester();
        var currentSubject = studentData.subject();
        for (var subjectInfo : getStudentInfo(name)) {
            if (subjectInfo.subject().equals(currentSubject)
                    && subjectInfo.semester() > currentSemester) {
                return false;
            }
        }
        return true;
    }

    /**Checking correctness of the subject mark.
     *
     * @param subjectName name of the subject
     */
    private static void checkCorrectnessOfMark(String subjectName) {
        if (!(subjectName.equals("Отлично") || subjectName.equals("Удовлетворительно")
                || subjectName.equals("Хорошо") || subjectName.equals("Зачёт"))) {
            throw new IllegalArgumentException("Такой оценки не бывает");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElectronicStudentBook that)) {
            return false;
        }
        return Objects.equals(electronicBook, that.electronicBook);
    }

    @Override
    public int hashCode() {
        return Objects.hash(electronicBook);
    }
}

