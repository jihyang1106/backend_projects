package org.example.calculator;

import org.example.calculator.ArithmeticOperator;

import java.util.List;

public class Calculator {

    // (3)번
    private static final List<NewArithmeticOperator>
            arithmeticOperators = List.of(new AdditionOperator(),
            new SubstractionOperator(), new MultiplicationOperator(),
            new DivisionOperator());
    public static int calculate(PositiveNumber positiveOperand1, String operator, PositiveNumber positiveOperand2) {
//        (1)
//        if("+".equals(operator))return operand1 + operand2;
//        else if("-".equals(operator)) return operand1 - operand2;
//        else if("*".equals(operator)) return operand1 * operand2;
//        else if("/".equals(operator)) return operand1 / operand2;
//        return 0;

//        (2)
//        return ArithmeticOperator.calculate(operand1, operator, operand2);

//        (3)
        return arithmeticOperators.stream()
                .filter(arithmeticOperators -> arithmeticOperators.supports(operator))
                .map(arithmeticOperators -> arithmeticOperators.calculate(positiveOperand1, positiveOperand2)) // int로 받기 위해 map으로
                .findFirst() // 첫번째 값 받기
                .orElseThrow(()-> new IllegalArgumentException("올바른 사칙연산이 아닙니다."));
    }
}
