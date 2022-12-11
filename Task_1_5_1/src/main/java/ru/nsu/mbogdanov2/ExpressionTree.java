package ru.nsu.mbogdanov2;

import java.util.Scanner;


/**
 * I decided to use expression tree in order to calculate prefix expression.
 * How it looks like:
 * + 1 2  is      +      is 3
 * 1    2
 * Another example of prefix expression in expression tree
 * + * 1 3 * 22 4  is       +        equals 91
 * *       *
 * 1    3   22   4
 */
public class ExpressionTree {

    TreeNode root;

    public ExpressionTree(Scanner input) {
        root = build(input);
    }

    /**
     * We parse input string and separate numbers from operations.
     * We need another case for sin and cos operation
     *
     * @param input string with expression
     * @return node of the tree
     */
    private TreeNode build(Scanner input) {
        boolean valueCheck;
        String token;
        double value;
        TreeNode node;

        valueCheck = input.hasNextDouble();
        if (valueCheck) {
            value = input.nextDouble();
            node = new TreeNode(true, false, "\0", value);
        } else {
            token = input.next();
            if (token.equals("cos") || token.equals("sin")) {
                node = new TreeNode(false, true, token, 0.0);
                node.left = build(input);

            } else {
                node = new TreeNode(false, false, token, 0.0);
                node.left = build(input);
                node.right = build(input);
            }
        }
        return node;
    }

    /**
     * Private recursive method to count the result of expression.
     *
     * @return the value of the expression tree.
     */
    public double calculation() throws IllegalArgumentException {
        return root == null ? 0.0 : calculation(root);
    }

    /**
     * My system of calculating.
     * log 1 3 = log(1) at the base of 3 = 0
     * sqrt 1 3 = sqrt 1 the power of the 3 = 1
     * This function goes down the tree and calculate all parts of the expression
     *
     * @param node current operation or number
     * @return result of the expression on the level of tree
     */
    private double calculation(TreeNode node) throws IllegalArgumentException {
        double result;
        if (node.valueCheck) {
            result = node.value;
        } else {
            double left;
            double right = 0;
            String operator = node.operation;
            if (node.singleOperation) {
                left = calculation(node.left);
            } else {
                left = calculation(node.left);
                right = calculation(node.right);
            }

            switch (operator) {
                case "-":
                    result = left - right;
                    break;

                case "log":
                    double logResult = Math.log(left) / Math.log(right);
                    if (checkInaccuracy(logResult)) {
                        result = Math.round(logResult);
                        break;
                    }
                    result = logResult;
                    break;

                case "sqrt":
                    double number = Math.pow(left, 1 / right);
                    if (checkInaccuracy(number)) {
                        result = Math.round(Math.pow(left, 1 / right));
                        break;
                    }
                    result = Math.pow(left, 1 / right);
                    break;

                case "*":
                    result = left * right;
                    break;

                case "sin":
                    result = Math.sin(left);
                    break;

                case "cos":
                    result = Math.cos(left);
                    break;

                case "/":
                    if (right == 0) {
                        throw new IllegalArgumentException("You can't divide by zero");
                    }
                    result = left / right;
                    break;

                case "+":
                    result = left + right;
                    break;

                case "pow":
                    result = Math.pow(left, right);
                    break;
                default:
                    throw new IllegalArgumentException("Something wrong with this expression");
            }
        }
        return result;
    }

    /**
     * private class of the tree node.
     * It helps to store information more compact and readable
     */
    private static class TreeNode {
        private final boolean valueCheck;
        private final String operation;
        private final boolean singleOperation;
        private final double value;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(boolean valCheck, boolean singleOperation, String op, double value) {
            this.valueCheck = valCheck;
            this.singleOperation = singleOperation;
            this.operation = op;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    /**
     * There are some problems with rounding of numbers in computers
     * So this method finds the real result of the calculating
     *
     * @param number double number
     * @return real result
     */
    private static boolean checkInaccuracy(double number) {
        return (Math.ceil(number) - number) <= 0.00000000000001;
    }
}