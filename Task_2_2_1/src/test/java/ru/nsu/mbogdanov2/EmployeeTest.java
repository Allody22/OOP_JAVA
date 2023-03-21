package ru.nsu.mbogdanov2;

import org.junit.jupiter.api.Test;
import org.testng.Assert;
import ru.nsu.mbogdanov2.model.people.Employee;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Test class for employee.
 * We make our own EmployeeImpl class, because Employee class is abstract.
 * We override work method for some tests.
 */
public class EmployeeTest {

    @Test
    public void testGetId() {
        int expectedId = 1;
        Employee employee = new EmployeeImpl(expectedId);
        int actualId = employee.getId();
        Assert.assertEquals(expectedId, actualId);
    }

    @Test
    public void testRunAndStop() throws InterruptedException {
        Employee employee = new EmployeeImpl(1);
        Thread thread = new Thread(employee);
        thread.start();
        Thread.sleep(1000);
        employee.stop();
        thread.join(1000);
        Assert.assertFalse(employee.runEmployee);
    }

    @Test
    public void testConstructor() {
        int expectedId = 1;
        Employee employee = new EmployeeImpl(expectedId);
        Assert.assertEquals(expectedId, employee.getId());
        Assert.assertFalse(employee.runEmployee);
    }

    @Test
    public void testWork() {
        class TestEmployee extends Employee {
            public TestEmployee(int id) {
                super(id);
            }
            @Override
            public void work() {
                System.out.print("Work method called");
            }
        }
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        Employee employee = new TestEmployee(1);
        employee.work();
        Assert.assertEquals("Work method called", outContent.toString());
        System.setOut(System.out);
    }

    @Test
    public void testStop() throws InterruptedException {
        Employee employee = new EmployeeImpl(1);
        Thread thread = new Thread(employee);
        thread.start();
        Thread.sleep(1000);
        employee.stop();
        Assert.assertFalse(employee.runEmployee);
    }

    private static class EmployeeImpl extends Employee {

        public EmployeeImpl(int id) {
            super(id);
        }

        @Override
        public void work() {
            // Do nothing
        }
    }
}