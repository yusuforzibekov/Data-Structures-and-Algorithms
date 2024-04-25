package com.epam.bsp.stack;

import java.util.Stack;

public class Solution {

    /**
     * Returns the evaluation result of a given list of RPN tokens.
     *
     * @param rpnTokens a list of RPN tokens
     * @return the evaluation result
     */
    public static int evaluateRpnTokens(String[] rpnTokens) {
        //put your code here
        Stack<Integer> stack = new Stack<>();
        for (String token: rpnTokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    int subtrahend = stack.pop();
                    int minuend = stack.pop();
                    stack.push(minuend - subtrahend);
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int divisor = stack.pop();
                    int dividend = stack.pop();
                    stack.push(dividend / divisor);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
                    break;
            }
        }
        return stack.pop();
    }

    /**
     * Returns true if a given string is valid parentheses expression.
     *
     * @param expression input string for validation.
     * @return whether a given string is valid parentheses expression.
     */
    public static boolean isValidParentheses(String expression) {
        //put your code here
        Stack<Character> stack = new Stack<Character>();
        for (char c : expression.toCharArray()) {
            if(c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            else if (stack.isEmpty() || stack.pop() != c)
                return false;
        }
        return stack.isEmpty();
    }
}
