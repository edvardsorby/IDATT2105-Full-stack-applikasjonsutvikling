package no.ntnu.idatt2105.calculator.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Stack;

@Service
public class CalculationService {


    private double calculate(double number1, double number2, char operator) {
        return switch (operator) {
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            case 'x' -> number1 * number2;
            case '/' -> number1 / number2;
            default -> 0;
        };
    }

    /**
     * Separates a String calculation into an array of numbers and operators
     */
    public String[] stringToArray(String calculation) {
        ArrayList<String> temp = new ArrayList<>();
        String number = "";

        for (int i = 0; i < calculation.length(); i++) {
            char c = calculation.charAt(i);

            if (isAnOperator(c)) {
                temp.add(number);
                number = "";
                temp.add(String.valueOf(c));
            } else if (i == calculation.length()-1) {
                number += c;
                temp.add(number);
            } else {
                number += c;
            }
        }


        return temp.toArray(String[]::new);
    }

    public boolean isAnOperator(char c) {
        return c == '+' || c == '-' || c == 'x' || c == '/';
    }

    /**
     * Converts an infix notation array ({"3", "+", "4"}) to reverse Polish notation ({"3", "4", "+"})
     * @param infix the infix notation array
     * @return the reverse Polish notation array
     */
    public static String[] infixToRPN(String[] infix) {

        ArrayList<String> buf = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        for (String c : infix) {
            if (!isAnOperator(c)) {
                buf.add(c);
            } else {
                while (!stack.isEmpty() && operatorPriority(c) <= operatorPriority(stack.peek())) {
                    buf.add(stack.pop());
                }
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            buf.add(stack.pop());
        }


        String[] rpn = buf.toArray(new String[0]);
        return rpn;
    }

    public static boolean isAnOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("x") || c.equals("/");
    }

    private static int operatorPriority(String c) {

        if (c.equals("+") || c.equals("-"))
            return 1;
        else if (c.equals("x") || c.equals("/"))
            return 2;
        else
            return -1;
    }

    /**
     * Calculates the answer based on reverse Polish notation, to follow the correct  order of operations.
     */
    public double rpnCalculator(String[] calculation) {

        double ans = 0;
        Stack<Double> stack = new Stack<>();

        for (String current : calculation) {
            if (isAnOperator(current)) {
                double number1 = stack.pop();
                double number2 = stack.pop();
                stack.push(calculate(number2, number1, current.charAt(0)));
            } else {
                stack.push(Double.valueOf(current));
            }
        }

        ans = stack.pop();

        return ans;
    }

}
