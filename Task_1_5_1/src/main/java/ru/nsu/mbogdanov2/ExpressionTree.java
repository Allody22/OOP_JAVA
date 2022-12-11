package ru.nsu.mbogdanov2;

import java.util.Scanner;


public class ExpressionTree {

    private static class TreeNode {
        private final boolean valueCheck; //пока что я храню и операции и числа в одних вершинах, поэтому такая проверка
        private final String operation;
        private double value;
        private TreeNode left;
        private TreeNode right;

        private TreeNode(boolean valueCheck, String operation, double value) {
            this.valueCheck = valueCheck;
            this.operation = operation;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public String toString() {
            return valueCheck ? Double.toString(value) : Character.toString(Integer.parseInt(operation));
        }
    }

    TreeNode root;

    public ExpressionTree(Scanner input) {
        root = build(input);
    }

    /**
     * We parse input string and separate numbers from operations
     *
     */
    private TreeNode build(Scanner input) {
        boolean valueCheck;
        String token;
        double value;
        TreeNode node;

        valueCheck = input.hasNextDouble();
        if (valueCheck) {
            value = input.nextDouble();
            node = new TreeNode(true, "\0", value);
        } else {
            token = input.next();
            node = new TreeNode(false, token, 0.0);
            node.left = build(input);
            node.right = build(input);
        }
        return node;
    }

    /**
     * Private recursive method to count the result of expression
     *
     * @return the value of the expression tree.
     */
    public double evaluate() {
        return root == null ? 0.0 : evaluate(root);
    }

    private double evaluate(TreeNode node) {
        double result = 0;
        if (node.valueCheck) {
            result = node.value;
        } else {
            double left, right;
            String operator = node.operation;
            left = evaluate(node.left);
            right = evaluate(node.right);

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

    private static boolean checkInaccuracy(double number) {
        return (Math.round(number) - number) < 0.00000000000001;
    }
}