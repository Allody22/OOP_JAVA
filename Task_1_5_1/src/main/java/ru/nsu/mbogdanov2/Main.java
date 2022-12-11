package ru.nsu.mbogdanov2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		System.out.print("Press enter to continue\n");
		System.out.print("If you want to close calculator, then write <Q>");
		Scanner sc = new Scanner(System.in);
		while (!sc.nextLine().equals("Q")) {
			System.out.print("Enter your prefix expression: ");
			String inString = sc.nextLine();
			Calculator myCalculator = new Calculator();
			Number result = myCalculator.calculate(inString);
			System.out.println("Result: " + result.doubleValue());
		}
		sc.close();
	}
}