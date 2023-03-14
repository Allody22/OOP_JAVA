package ru.nsu.mbogdanov2;

import ru.nsu.mbogdanov2.pizzeria.PizzeriaLaunch;

/**
 * ApplicationExecution is a class with main method, which allows starting application.
 */
public class PizzeriaApplication {
    /**
     * Launches an application from the command line.
     *
     * @param args - arguments from the command.
     */
    public static void main(String[] args) {
        new PizzeriaLaunch().run();
    }
}