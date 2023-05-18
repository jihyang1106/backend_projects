package org.example.calculator;

import org.example.calculator.NewArithmeticOperator;

public class SubstractionOperator implements NewArithmeticOperator {
    @Override
    public boolean supports(String operator) {
        return "-".equals(operator);
    }

//    @Override
//    public int calculate(int operand1, int operand2) {
//        return operand1 - operand2;
//    }

    @Override
    public int calculate(PositiveNumber operand1, PositiveNumber operand2) {
        return operand1.toInt() - operand2.toInt();
    }
}