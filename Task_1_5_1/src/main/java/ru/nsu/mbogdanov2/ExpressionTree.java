package ru.nsu.mbogdanov2;

import java.util.Scanner;


public class ExpressionTree {

    TreeNode root;

    public ExpressionTree(Scanner input) {
        root = build(input);
    }

    /**
     * We parse input string and separate numbers from operations
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
     * Private recursive method to count the result of expression
     *
     * @return the value of the expression tree.
     */
    public double calculation() {
        return root == null ? 0.0 : calculation(root);
    }

    private double calculation(TreeNode node) {
        double result = 0;
        if (node.valueCheck) {
            result = node.value;
        } else {
            double left, right = 0;
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

                case "log": // Логирфм (левого) по основанию правого
                    double logResult = Math.log(right) / Math.log(left);
                    if (checkInaccuracy(logResult)) {
                        result = Math.round(logResult);
                        break;
                    }
                    result = logResult;
                    break;

                case "sqrt": // корень левого в степени правого
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

                case "/":
                    result = left / right;
                    break;

                case "+":
                    result = left + right;
                    break;

                case "pow":
                    result = Math.pow(left, right);
                    break;
                default:
                    System.out.println("Такая операция пока не доступна");
            }
        }
        return result;
    }

    /**
     * private class of the tree node
     * It helps to store information more compact and readable
     *
     */
    private static class TreeNode {
        private final boolean valueCheck;
        private final String operation;
        private final boolean singleOperation;
        private final double value;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(boolean valueCheck, boolean singleOperation, String operation, double value) {
            this.valueCheck = valueCheck;
            this.singleOperation = singleOperation;
            this.operation = operation;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public String toString() {
            return valueCheck ? Double.toString(value) : Character.toString(Integer.parseInt(operation));
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
        return (Math.round(number) - number) < 0.00000000000001;
    }
}