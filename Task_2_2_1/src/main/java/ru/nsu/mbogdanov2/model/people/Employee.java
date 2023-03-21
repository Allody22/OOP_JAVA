package ru.nsu.mbogdanov2.model.people;

/**
 * The implementation of this abstract method allows you to simulate the work of an employee.
 */
public abstract class Employee implements Runnable {
    private final int id;
    public boolean runEmployee;

    /**
     * Constructor of an abstract class. Allows to set the employee id.
     *
     * @param id - employee's id.
     */
    public Employee(int id) {
        this.id = id;
        this.runEmployee = false;
    }

    /**
     * Returns id of the employee.
     *
     * @return - id of the employee.
     */
    public int getId() {
        return id;
    }

    /**
     * The implementation of this abstract method should be the execution of the employee task.
     */
    public abstract void work();

    /**
     * Simulates the permanent work of an employee.
     * Execution stops when the stop method is called.
     */
    @Override
    public void run() {
        runEmployee = true;
        while (runEmployee) {
            work();
        }
    }

    /**
     * Stops the run method.
     */
    public void stop() {
        runEmployee = false;
    }
}
