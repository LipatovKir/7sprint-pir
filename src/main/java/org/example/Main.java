package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.ArrayDeque;
import java.util.Deque;


class ReversePolishNotationCalculatorTest {

    ReversePolishNotationCalculator calculator = new ReversePolishNotationCalculator();
    String expression = "";

    @Test
    public void checkCalculateAddition() {
        expression = "5 6 +";
        int expect = 11;
        int result = calculator.calculatePolishNotation(expression);
        Assertions.assertEquals(expect, result);
    }

    @Test
    public void checkCalculateSubtraction() {
        expression = "5 10 -";
        int expect = -5;
        int result = calculator.calculatePolishNotation(expression);
        Assertions.assertEquals(expect, result);
    }

    @Test
    public void checkCalculateMultiplication() {
        expression = "6 8 *";
        int expect = 48;
        int result = calculator.calculatePolishNotation(expression);
        Assertions.assertEquals(expect, result);
    }

    @Test
    public void checkCalculateSeveralActions() {
        expression = "1 9 8 2 * + -";
        int expect = -24;
        int result = calculator.calculatePolishNotation(expression);
        Assertions.assertEquals(expect, result);
    }

    @Test
    public void checkCalculateSpaceAndNegativeValues() {
        expression = "1 9 8 -2 *    +               -";
        int expect = 8;
        int result = calculator.calculatePolishNotation(expression);
        Assertions.assertEquals(expect, result);
    }

    class ReversePolishNotationCalculator {

        public int calculatePolishNotation(String str) {
            String[] parts = str.split(" ");
            Deque<Integer> numbers = new ArrayDeque<>();
            int index = 0;
            while (index != parts.length) {
                if (parts[index].isBlank()) {
                    index++;
                    continue;
                }

                if (isOperation(parts[index])) {
                    int operandOne = numbers.pop();
                    int operandTwo = numbers.pop();
                    if (parts[index].equals("+")) {
                        numbers.push(operandOne + operandTwo);
                    } else if (parts[index].equals("-")) {
                        numbers.push(operandTwo - operandOne);
                    } else if (parts[index].equals("*")) {
                        numbers.push(operandOne * operandTwo);
                    }
                } else {
                    numbers.push(Integer.parseInt(parts[index]));
                }
                index++;
            }
            return numbers.pop();
        }

        private boolean isOperation(String part) {
            if (part.equals("+")
                    || part.equals("-")
                    || part.equals("*")) {
                return true;
            }
            return false;
        }
    }
}
