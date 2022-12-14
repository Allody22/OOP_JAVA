package ru.nsu.mbogdanov2;

import java.util.Scanner;

/**
 * Main class for jar file.
 * It works until the user writes Q
 */
public class Main {
    /**
     * Static main method for good jar file functionality.
     */
    public static void main(String[] args) throws IllegalArgumentException {
        System.out.print("Always press enter to continue\n");
        System.out.print("If you want to close calculator, "
                + "then write after this line or result of your expression <Q>");
        Scanner sc = new Scanner(System.in);
        while (!sc.nextLine().equals("Q")) {
            System.out.print("Enter your prefix expression: ");
            String expression = sc.nextLine();
            if (expression.length() == 0) {
                throw new IllegalArgumentException("Expression length is zero");
            }
            try {
                var calc = new ExpressionTree(new Scanner(expression));
                double result = calc.calculation();
                System.out.println("Result: " + result);
            } catch (IllegalArgumentException invalid) {
                System.out.println(invalid.getMessage());
            }

        }
        sc.close();
    }
}