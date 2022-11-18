package ru.nsu.mbogdanov2;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Different checks for main class with main book.
 */
public class BookTests {
    /**
     * Assertions of all exceptions.
     * A lot of checks for "No student with this name" exception
     */
    @Test
    public void exceptionTest() {
        BookFit creditBook = new BookFit();
        creditBook.addStudent("Богданов Михаил Сергеевич");
        IllegalArgumentException exceptionRedDiploma =
                assertThrows(IllegalArgumentException.class,
                        () -> creditBook.checkRedDiploma("Артём Благинин"));
        Assertions.assertEquals("No student with this name", exceptionRedDiploma.getMessage());
        IllegalArgumentException exceptionHighScholarship =
                assertThrows(IllegalArgumentException.class,
                        () -> creditBook.checkHighScholarship("Артём Благинин"));
        Assertions.assertEquals("No student with this name", exceptionHighScholarship
                .getMessage());
        IllegalArgumentException exceptionScholarship =
                assertThrows(IllegalArgumentException.class,
                        () -> creditBook.checkRedDiploma("Артём Благинин"));
        Assertions.assertEquals("No student with this name", exceptionScholarship.getMessage());
        IllegalArgumentException exceptionStudentInformation =
                assertThrows(IllegalArgumentException.class,
                        () -> creditBook.printFullStudentInformation("Артём Благинин"));
        Assertions.assertEquals("No student with this name", exceptionStudentInformation
                .getMessage());
        IllegalArgumentException exceptionEmptyCreditBook =
                assertThrows(IllegalArgumentException.class,
                        () -> creditBook.getAverageMark("Богданов Михаил Сергеевич"));
        Assertions.assertEquals("Зачетная книжка пустая", exceptionEmptyCreditBook.getMessage());
        IllegalArgumentException exceptionEmptyDiploma =
                assertThrows(IllegalArgumentException.class,
                        () -> creditBook.checkHighScholarship("Богданов Михаил Сергеевич"));
        Assertions.assertEquals("Нету оценок в данной книге", exceptionEmptyDiploma.getMessage());
    }

    /**
     * We add all needed information for redDiploma.
     * Then we check this information and see that method returns
     * true, that is student has red diploma
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void redDiplomaTest() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("red_diploma_marks.txt")) {
            if (file == null) {
                throw new FileNotFoundException("No file with this name");
            }
            Scanner sc = new Scanner(file);
            int numberOfCreditMarks = sc.nextInt();
            int numberOfDiplomaMarks = sc.nextInt();
            var actualInformation = new StudentInformation();
            for (int i = 0; i < numberOfCreditMarks; i++) {
                actualInformation.addCreditBookMarkAndSubject(sc.next(), sc.next());
            }
            for (int i = 0; i < numberOfDiplomaMarks; i++) {
                actualInformation.addDiplomaSubjectAndMark(sc.next(), sc.next());
            }
            BookFit actualCreditBook =
                    new BookFit("Богданов Михаил Сергеевич", actualInformation);
            Assertions.assertTrue(actualCreditBook
                    .checkRedDiploma("Богданов Михаил Сергеевич"));
        }
    }

    /**
     * Checking equality of two credit books.
     * We get information about actual from file
     * and set information about with setters
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void creditBookEquals() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("equals_credit_books.txt")) {
            if (file == null) {
                throw new FileNotFoundException("No file with this name");
            }
            Scanner sc = new Scanner(file);
            int numberOfCreditMarks = sc.nextInt();
            int numberOfDiplomaMarks = sc.nextInt();
            BookFit actualCreditBook =
                    new BookFit("Богданов Михаил Сергеевич");
            for (int i = 0; i < numberOfCreditMarks; i++) {
                actualCreditBook.addCreditBookSubjectAndMark("Богданов Михаил Сергеевич", sc.next(), sc.next());
            }
            for (int i = 0; i < numberOfDiplomaMarks; i++) {
                actualCreditBook.addDiplomaSubjectAndMark("Богданов Михаил Сергеевич", sc.next(), sc.next());
            }
            actualCreditBook.addStudent("Шадрина Настя");

            var expectedCreditBook = new BookFit("Богданов Михаил Сергеевич");
            expectedCreditBook.addDiplomaSubjectAndMark("Богданов Михаил Сергеевич", "ООП", "Отлично");
            expectedCreditBook.addDiplomaSubjectAndMark("Богданов Михаил Сергеевич", "Физика", "Отлично");
            expectedCreditBook.addCreditBookSubjectAndMark("Богданов Михаил Сергеевич", "Музыка", "Отлично");
            expectedCreditBook.addCreditBookSubjectAndMark("Богданов Михаил Сергеевич", "История", "Отлично");
            expectedCreditBook.addStudent("Шадрина Настя");
            Assertions.assertEquals(expectedCreditBook, actualCreditBook);
        }
    }

    /**
     * We add all needed information for scholarship.
     * If student doesn't have bad marks, then he has scholarship
     * Then we just delete subjects with bad marks and see that
     * student now has scholarship
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void scholarshipTest() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("scholarship.txt")) {
            if (file == null) {
                throw new FileNotFoundException("No file with this name");
            }
            Scanner sc = new Scanner(file);
            BookFit actualCreditBook =
                    new BookFit("Богданов Михаил Сергеевич");
            for (int i = 0; i < 6; i++) {
                actualCreditBook.addCreditBookSubjectAndMark("Богданов Михаил Сергеевич", sc.next(), sc.next());
            }
            Assertions.assertFalse(actualCreditBook
                    .checkScholarship("Богданов Михаил Сергеевич"));
            actualCreditBook
                    .deleteCreditBookSubjectAndMark("Богданов Михаил Сергеевич", "География");
            actualCreditBook.deleteCreditBookSubjectAndMark("Богданов Михаил Сергеевич", "ТФКП");
            Assertions.assertTrue(actualCreditBook
                    .checkScholarship("Богданов Михаил Сергеевич"));
        }
    }

    /**
     * We add all needed information for high scholarship.
     * Then we add another student and give him marks of the first student.
     * That is, second student also has high scholarship
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void highScholarshipTest() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("high_scholarship.txt")) {
            if (file == null) {
                throw new FileNotFoundException("No file with this name");
            }
            Scanner sc = new Scanner(file);
            BookFit actualCreditBook =
                    new BookFit("Богданов Михаил Сергеевич");
            for (int i = 0; i < 6; i++) {
                actualCreditBook.addCreditBookSubjectAndMark("Богданов Михаил Сергеевич", sc.next(), sc.next());
            }
            Assertions.assertTrue(actualCreditBook
                    .checkHighScholarship("Богданов Михаил Сергеевич"));
            actualCreditBook.addStudent("Настя Шадрина");
            actualCreditBook.addStudentInformation("Настя Шадрина", actualCreditBook.getStudentInformation("Богданов Михаил Сергеевич"));
            Assertions.assertTrue(actualCreditBook
                    .checkHighScholarship("Настя Шадрина"));
        }
    }

    /** Testing of average mark method.
     * We count average mark ourselves and compare it with function result
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void averageMarkTest() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("scholarship.txt")) {
            if (file == null) {
                throw new FileNotFoundException("No file with this name");
            }
            Scanner sc = new Scanner(file);
            BookFit actualCreditBook =
                    new BookFit("Богданов Михаил Сергеевич");
            for (int i = 0; i < 6; i++) {
                actualCreditBook.addCreditBookSubjectAndMark("Богданов Михаил Сергеевич", sc.next(), sc.next());
            }
            double expectedMark = 4.166;
            Assertions.assertEquals(expectedMark, actualCreditBook.getAverageMark("Богданов Михаил Сергеевич"));
        }
    }

}
