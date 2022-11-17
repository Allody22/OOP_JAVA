package ru.nsu.mbogdanov2;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test class for credit and diploma books.
 */
public class StudentInformationTests {
    /**
     * We scan information from file.
     * And assert it with expected information made by setters
     * Then we check that expected and actual object are equal
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void studentInformationEquals() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("student_Information_equals.txt")) {
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
            var expectedInformation = new StudentInformation();
            expectedInformation.addDiplomaSubjectAndMark("ООП", "Отлично");
            expectedInformation.addCreditBookMarkAndSubject("Математика", "Удовлетворительно");
            expectedInformation.addCreditBookMarkAndSubject("Физика", "Отлично");
            expectedInformation.addCreditBookMarkAndSubject("ОКР", "Отлично");
            expectedInformation.addDiplomaSubjectAndMark("Биоинформатика",
                    "Отлично");
            Assertions.assertEquals(expectedInformation, actualInformation);
        }
    }

    /**
     * We check function that returns mark for the subject.
     * Firstly, we scan actual file information
     * Then we put subjects and marks with put method in expected information value
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void setAndGetCreditBookMarksTest() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("credit_book_marks.txt")) {
            if (file == null) {
                throw new FileNotFoundException("No file with this name");
            }
            Scanner sc = new Scanner(file);
            var actualInformation = new StudentInformation();
            for (int i = 0; i < 5; i++) {
                actualInformation.addCreditBookMarkAndSubject(sc.next(), sc.next());
            }
            var expectedInformation = new HashMap<>();
            expectedInformation.put("а", "Отлично");
            expectedInformation.put("б", "Отлично");
            expectedInformation.put("в", "Удовлетворительно");
            expectedInformation.put("г", "Хорошо");
            expectedInformation.put("д", "Зачёт");
            Assertions.assertEquals(expectedInformation, actualInformation.getCreditBookMarks());
        }
    }

    /** Set function test.
     * We put all information with one method into expectedInformation object
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void setAndGetDiplomaMarksTest() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("diploma_book_marks.txt")) {
            if (file == null) {
                throw new FileNotFoundException("No file with this name");
            }
            Scanner sc = new Scanner(file);
            var actualInformation = new StudentInformation();
            for (int i = 0; i < 4; i++) {
                actualInformation.addDiplomaSubjectAndMark(sc.next(), sc.next());
            }
            var expectedInformation = new StudentInformation();
            Map<String, String> expectedDiplomaMarks = new HashMap<>();
            expectedDiplomaMarks.put("а", "Отлично");
            expectedDiplomaMarks.put("б", "Отлично");
            expectedDiplomaMarks.put("в", "Удовлетворительно");
            expectedDiplomaMarks.put("г", "Хорошо");
            expectedInformation.setDiplomaMarks(expectedDiplomaMarks);
            Assertions.assertEquals(expectedDiplomaMarks, actualInformation.getDiplomaMarks());
        }
    }

    /** Testing of getter method for one subject.
     * We create actual and expected objects with some differences in subjects
     * And check marks for some subjects
     * Then we delete redundant subjects in actualInformation objects
     * And see that out object are equal now
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    public void getAndDeleteMarksTest() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("get_and_delete_marks.txt")) {
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
            var expectedInformation = new StudentInformation();
            expectedInformation.addDiplomaSubjectAndMark("ООП", "Отлично");
            expectedInformation.addCreditBookMarkAndSubject("Физика", "Отлично");
            expectedInformation.addCreditBookMarkAndSubject("ОКР", "Отлично");
            expectedInformation.addDiplomaSubjectAndMark("Биоинформатика",
                    "Отлично");
            Assertions.assertEquals(expectedInformation
                    .getDiplomaMarkForSubject("ООП"), actualInformation.getDiplomaMarkForSubject("ООП"));
            Assertions.assertEquals(expectedInformation.getCreditBookMarkForSubject("ОКР"),
                    actualInformation.getCreditBookMarkForSubject("ОКР"));
            actualInformation.deleteCreditBookMarkForSubject("Математика");
            actualInformation.deleteDiplomaMarkForSubject("История");
            Assertions.assertEquals(expectedInformation, actualInformation);
        }
    }
    /** Testing different exceptions.
     *
     */
    @Test
    public void exceptionsTest() {
        var expectedInformation = new StudentInformation();
        IllegalArgumentException exceptionAddWrongDiplomaMark =
                assertThrows(IllegalArgumentException.class,
                        () -> expectedInformation.addDiplomaSubjectAndMark("Физика","супер-пупер"));
        Assertions.assertEquals("Такую оценку в " +
                "диплом ставить нельзя", exceptionAddWrongDiplomaMark.getMessage());
        IllegalArgumentException exceptionAddWrongCreditBookMark =
                assertThrows(IllegalArgumentException.class,
                        () -> expectedInformation.addCreditBookMarkAndSubject("Физика","супер-пупер"));
        Assertions.assertEquals("Такую оценку в зачётную " +
                "книгу нельзя писать", exceptionAddWrongCreditBookMark.getMessage());
        IllegalArgumentException exceptionDeleteWrongDiplomaMark =
                assertThrows(IllegalArgumentException.class,
                        () -> expectedInformation.deleteDiplomaMarkForSubject("Физика"));
        Assertions.assertEquals("Неправильно название " +
                "дипломного предмета для удаления", exceptionDeleteWrongDiplomaMark.getMessage());
        IllegalArgumentException exceptionDeleteWrongCreditBookMark =
                assertThrows(IllegalArgumentException.class,
                        () -> expectedInformation.deleteCreditBookMarkForSubject("Физика"));
        Assertions.assertEquals("Неправильно название предмета " +
                "зачетной книги для удаления", exceptionDeleteWrongCreditBookMark.getMessage());
    }
}
