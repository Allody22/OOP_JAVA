package ru.nsu.mbogdanov2;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**Test class for testing base student electronic book options.
 *
 */
class ElectronicStudentBookTest {
    /**A lot of different exceptions.
     * There are mostly "no student" exception and some "invalid mark" exceptions.
     *
     */
    @Test
    void exceptionsTest() {
        var newBook = new ElectronicStudentBook();
        newBook.addStudent("Богданов Михаил");
        IllegalArgumentException exceptionRedDiploma =
                assertThrows(IllegalArgumentException.class,
                        () -> newBook.checkRedDiploma("Артём Благинин"));
        Assertions.assertEquals("Такого студента нет", exceptionRedDiploma.getMessage());
        var studentInfo = new StudentDataForOneSubject("Отлично", "Физика", 4);
        IllegalArgumentException exceptionNewStudentInfo =
                assertThrows(IllegalArgumentException.class,
                        () -> newBook.addStudentInfoAboutOneSubject("Вадим Хлебников",
                                studentInfo));
        Assertions.assertEquals("Такого студента нет", exceptionNewStudentInfo.getMessage());

        var studentInfo2 = new StudentDataForOneSubject("ОченьЖаль", "Физика", 4);
        IllegalArgumentException exceptionInvalidMark =
                assertThrows(IllegalArgumentException.class,
                        () -> newBook.addStudentInfoAboutOneSubject("Богданов Михаил",
                                studentInfo2));
        Assertions.assertEquals("Такой оценки не бывает", exceptionInvalidMark.getMessage());

        var exceptionList = new ArrayList<StudentDataForOneSubject>();
        exceptionList.add(studentInfo);
        var studentInfo3 = new StudentDataForOneSubject("Отлично", "Матан", 4);
        exceptionList.add(studentInfo3);
        IllegalArgumentException exceptionInvalidStudentName =
                assertThrows(IllegalArgumentException.class,
                        () -> newBook.addInformationAboutStudent("Игорь Журавский",
                                exceptionList));
        Assertions.assertEquals("Такого студента нет", exceptionInvalidStudentName.getMessage());
        exceptionList.add(studentInfo2);
        IllegalArgumentException exceptionInvalidMarkForList =
                assertThrows(IllegalArgumentException.class,
                        () -> newBook.addStudentInfoAboutOneSubject("Богданов Михаил",
                                studentInfo2));
        Assertions.assertEquals("Такой оценки не бывает",
                exceptionInvalidMarkForList.getMessage());

        IllegalArgumentException exceptionCheckScholarship =
                assertThrows(IllegalArgumentException.class,
                        () -> newBook.checkScholarShip("Шадрина Настя"));
        Assertions.assertEquals("Такого студента нет", exceptionCheckScholarship.getMessage());
        IllegalArgumentException exceptionCheckHighScholarship =
                assertThrows(IllegalArgumentException.class,
                        () -> newBook.checkHighScholarShip("Шадрина Настя"));
        Assertions.assertEquals("Такого студента нет", exceptionCheckHighScholarship.getMessage());
    }

    /** We add create student that has "Удовлетворительно" mark from the last exam.
     * Our student doesn't have scholarship, then we add new subject with the max semester number.
     * Student has only this one mark from the max semester, so he has scholarship now
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    void checkScholarShip() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("scholarship.txt")) {
            if (file == null) {
                throw new FileNotFoundException("No file with this name");
            }
            Scanner sc = new Scanner(file);
            var actualBook = new ElectronicStudentBook();
            actualBook.addStudent("Богданов Михаил");
            for (int i = 0; i < 6; i++) {
                var currentInfo = new StudentDataForOneSubject(sc.next(), sc.next(), sc.nextInt());
                actualBook.addStudentInfoAboutOneSubject("Богданов Михаил", currentInfo);
            }
            Assertions.assertFalse(actualBook.checkScholarShip("Богданов Михаил"));
            var currentInfo = new StudentDataForOneSubject("Отлично", "Спасение Стипендии", 8);
            actualBook.addStudentInfoAboutOneSubject("Богданов Михаил", currentInfo);
            Assertions.assertTrue(actualBook.checkScholarShip("Богданов Михаил"));
        }
    }

    /**
     * Firstly, we create student with conditions for high scholarship.
     * Then, we add new subject with the biggest semester mark.
     * We see that student now doesn't have high scholarship
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    void checkHighScholarShip() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("high_scholarship.txt")) {
            if (file == null) {
                throw new FileNotFoundException("No file with this name");
            }
            Scanner sc = new Scanner(file);
            var actualBook = new ElectronicStudentBook();
            actualBook.addStudent("Шадрина Настя");
            for (int i = 0; i < 6; i++) {
                var currentInfo = new StudentDataForOneSubject(sc.next(), sc.next(), sc.nextInt());
                actualBook.addStudentInfoAboutOneSubject("Шадрина Настя", currentInfo);
            }
            Assertions.assertTrue(actualBook.checkHighScholarShip("Шадрина Настя"));
            var currentInfo = new StudentDataForOneSubject("Хорошо", "Жаль", 8);
            actualBook.addStudentInfoAboutOneSubject("Шадрина Настя", currentInfo);
            Assertions.assertFalse(actualBook.checkHighScholarShip("Шадрина Настя"));
        }
    }

    /**
     * Testing of average mark method.
     * We count average mark ourselves and compare it with function result
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    void getAverageMark() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("scholarship.txt")) {
            if (file == null) {
                throw new FileNotFoundException("No file with this name");
            }
            Scanner sc = new Scanner(file);
            var actualBook = new ElectronicStudentBook();
            actualBook.addStudent("Богданов Михаил");
            for (int i = 0; i < 6; i++) {
                var currentInfo = new StudentDataForOneSubject(sc.next(), sc.next(), sc.nextInt());
                actualBook.addStudentInfoAboutOneSubject("Богданов Михаил", currentInfo);
            }
            double expectedMark = 4.166;
            Assertions.assertEquals(expectedMark, actualBook.getAverageMark("Богданов Михаил"));
        }
    }

    /**
     * We add all needed information for redDiploma.
     * Student has some "Удовлетворительно" marks, but they are not last
     *
     * @throws IOException exception in case there are some troubles with file
     */
    @Test
    void checkRedDiploma() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("red_diploma.txt")) {
            if (file == null) {
                throw new FileNotFoundException("No file with this name");
            }
            Scanner sc = new Scanner(file);
            var actualBook = new ElectronicStudentBook();
            actualBook.addStudent("Богданов Михаил");
            for (int i = 0; i < 8; i++) {
                var currentInfo = new StudentDataForOneSubject(sc.next(), sc.next(), sc.nextInt());
                actualBook.addStudentInfoAboutOneSubject("Богданов Михаил", currentInfo);
            }
            Assertions.assertTrue(actualBook.checkRedDiploma("Богданов Михаил"));
        }
    }

    @Test
    void testEquals() throws IOException {
        try (var file = getClass().getClassLoader()
                .getResourceAsStream("equals_credit_books.txt")) {
            if (file == null) {
                throw new FileNotFoundException("No file with this name");
            }
            Scanner sc = new Scanner(file);
            var actualBook = new ElectronicStudentBook();
            actualBook.addStudent("Богданов Михаил");
            for (int i = 0; i < 4; i++) {
                var currentInfo = new StudentDataForOneSubject(sc.next(), sc.next(), sc.nextInt());
                actualBook.addStudentInfoAboutOneSubject("Богданов Михаил", currentInfo);
            }
            var listOfStudentData = new ArrayList<StudentDataForOneSubject>();
            var newInfo4 = new StudentDataForOneSubject("Отлично", "Музыка", 6);
            listOfStudentData.add(newInfo4);
            var newInfo = new StudentDataForOneSubject("Отлично", "История", 1);
            listOfStudentData.add(newInfo);
            var newInfo3 = new StudentDataForOneSubject("Хорошо", "Физика", 2);
            listOfStudentData.add(newInfo3);
            var newInfo2 = new StudentDataForOneSubject("Удовлетворительно", "ООП", 4);
            listOfStudentData.add(newInfo2);
            var expectedBook = new ElectronicStudentBook();
            expectedBook.addStudent("Богданов Михаил");
            expectedBook.addInformationAboutStudent("Богданов Михаил", listOfStudentData);
            Assertions.assertEquals(actualBook, expectedBook);

            actualBook.addStudent("Bogdanov Mikhail");
            Assertions.assertNotEquals(actualBook, expectedBook);
        }

    }
}