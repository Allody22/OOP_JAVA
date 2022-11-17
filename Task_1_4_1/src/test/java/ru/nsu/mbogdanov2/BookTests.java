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
                        () -> creditBook.checkRedDiploma("Vfvsd"));
        Assertions.assertEquals("No student with this name", exceptionRedDiploma.getMessage());
        IllegalArgumentException exceptionHighScholarship =
                assertThrows(IllegalArgumentException.class,
                        () -> creditBook.checkHighScholarship("Vfvsd"));
        Assertions.assertEquals("No student with this name", exceptionHighScholarship.getMessage());
        IllegalArgumentException exceptionScholarship =
                assertThrows(IllegalArgumentException.class,
                        () -> creditBook.checkRedDiploma("Vfvsd"));
        Assertions.assertEquals("No student with this name", exceptionScholarship.getMessage());
        IllegalArgumentException exceptionStudentInformation =
                assertThrows(IllegalArgumentException.class,
                        () -> creditBook.printFullStudentInformation("Vfvsd"));
        Assertions.assertEquals("No student with this name", exceptionStudentInformation.getMessage());
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

    /** Checking equality of two credit books.
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
            var actualInformation = new StudentInformation();
            for (int i = 0; i < numberOfCreditMarks; i++) {
                actualInformation.addCreditBookMarkAndSubject(sc.next(), sc.next());
            }
            for (int i = 0; i < numberOfDiplomaMarks; i++) {
                actualInformation.addDiplomaSubjectAndMark(sc.next(), sc.next());
            }
            BookFit actualCreditBook =
                    new BookFit("Богданов Михаил Сергеевич", actualInformation);
            actualCreditBook.addStudent("Шадрина Настя");

            var expectedInformation = new StudentInformation();
            expectedInformation.addDiplomaSubjectAndMark("ООП", "Отлично");
            expectedInformation.addCreditBookMarkAndSubject("Музыка", "Отлично");
            expectedInformation.addCreditBookMarkAndSubject("История", "Отлично");
            expectedInformation.addDiplomaSubjectAndMark("Физика", "Отлично");
            var expectedCreditBook = new BookFit("Богданов Михаил Сергеевич",
                    expectedInformation);
            expectedCreditBook.addStudent("Шадрина Настя");
            Assertions.assertEquals(expectedCreditBook, actualCreditBook);
        }
    }


}
