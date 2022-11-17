package ru.nsu.mbogdanov2;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookTests {
    @Test
    public void exceptionTest() {
        BookFIT creditBook = new BookFIT();
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
    }

    @Test
    public void redDiplomaTest() {
        BookFIT creditBook = new BookFIT();
        creditBook.addStudent("Богданов Михаил Сергеевич");
    }
}
