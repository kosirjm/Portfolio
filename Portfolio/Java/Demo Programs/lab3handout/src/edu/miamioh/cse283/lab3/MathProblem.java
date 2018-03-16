package edu.miamioh.cse283.lab3;

import java.util.Random;
import java.util.Scanner;

public class MathProblem {

    /**
     * This method solves a simple math problem given in the form: a [+-/*] b.
     *
     * @param amp is the string representing the math problem.
     * @return the answer, or null if the math problem was malformed.
     */
    public static Double solve(String amp) {
        Scanner s = new Scanner(amp);
        double a = s.nextDouble();
        String op = s.next();
        double b = s.nextDouble();

        if (op.contains("-")) {
            return a - b;
        } else if (op.contains("+")) {
            return a + b;
        } else if (op.contains("/")) {
            return a / b;
        } else if (op.contains("*")) {
            return a * b;
        }

        return null;
    }

    /**
     * This method returns a randomly-generated math problem in the form: a
     * [+-/*] b.
     *
     * @return a string holding a randomly-generated math problem.
     */
    public static String generate() {
        Random rand = new Random();

        double a = rand.nextFloat();
        double b = rand.nextFloat();
        String op = null;
        switch (rand.nextInt(4)) {
            case 0:
                op = " + ";
                break;
            case 1:
                op = " - ";
                break;
            case 2:
                op = " / ";
                break;
            case 3:
                op = " * ";
                break;
        }

        return Double.toString(a) + op + Double.toString(b);
    }
}
