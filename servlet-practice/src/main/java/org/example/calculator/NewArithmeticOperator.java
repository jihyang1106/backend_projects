package org.example.calculator;

public interface NewArithmeticOperator {

    boolean supports(String operator);

//    int calculate(int operand1, int operand2);
    int calculate(final PositiveNumber operand1, final PositiveNumber operand2);
}
