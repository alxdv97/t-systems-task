package com.tsystems.javaschool.tasks.calculator;

import java.util.LinkedList;

public class Calculator {

    /**
     * Evaluate statement represented as string.
     *
     * @param statement mathematical statement containing digits, '.' (dot) as decimal mark,
     *                  parentheses, operations signs '+', '-', '*', '/'<br>
     *                  Example: <code>(1 + 38) * 4.5 - 1 / 2.</code>
     * @return string value containing result of evaluation or null if statement is invalid
     */
    public String evaluate(String statement) {
        try {

            LinkedList<Double> st = new LinkedList<>();
            LinkedList<Character> op = new LinkedList<>();

            for (int i = 0; i < statement.length(); i++) {
                char c = statement.charAt(i);
                if (isDelimiter(c))
                    continue;
                if (c == '(')
                    op.add('(');
                else if (c == ')') {
                    while (op.getLast() != '(')
                        processOperator(st, op.removeLast());
                    op.removeLast();
                } else if (isOperator(c)) {
                    while (!op.isEmpty() && priority(op.getLast()) >= priority(c))
                        processOperator(st, op.removeLast());
                    op.add(c);
                } else {
                    StringBuilder operand = new StringBuilder();
                    while (i < statement.length() && (Character.isDigit(statement.charAt(i)) || statement.charAt(i) == '.')) {
                        operand.append(statement.charAt(i++));
                    }
                    --i;
                    st.add(Double.parseDouble(operand.toString()));
                }
            }
            while (!op.isEmpty())
                processOperator(st, op.removeLast());
            String result = String.valueOf(st.get(0));
            if (result.split("\\.")[1].equals("0")) {
                result = result.split("\\.")[0];
            }
            return result;
        } catch (RuntimeException e){
            return null;
        }
    }


    private boolean isDelimiter(char c) {
        return c == ' ';
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '%';
    }

    private int priority(char op) {
        switch (op) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
            case '%':
                return 2;
            default:
                return -1;
        }
    }

    private void processOperator(LinkedList<Double> st, char op) {
        double r = st.removeLast();
        double l = st.removeLast();
        switch (op) {
            case '+':
                st.add(l + r);
                break;
            case '-':
                st.add(l - r);
                break;
            case '*':
                st.add(l * r);
                break;
            case '/':
                st.add(l / r);
                break;
            case '%':
                st.add(l % r);
                break;
        }
    }

}
