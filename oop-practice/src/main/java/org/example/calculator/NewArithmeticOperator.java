package org.example.calculator;

public interface NewArithmeticOperator {

    boolean supports(String operator);

//    int calculate(int operand1, int operand2);

    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
