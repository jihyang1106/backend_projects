package org.example.calculator;

import org.example.calculator.Calculator;
import org.example.calculator.PositiveNumber;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.params.provider.Arguments.arguments;

/*  - 간단한 사칙연산
    - 양수로만 계산 가능
    - 나눗셈에서 0을 나누는 경우 IllegalArgument 예외 throw
    - MVC패턴(Model-View-Controller) 기반으로 구현
 */

public class CalculatorTest {

//    (1)
//    @DisplayName("덧셈 연산을 수행한다.")
//    @Test
//    void additionTest() {
//        int result = Calculator.calculate(1, "+", 2);
//        assertThat(result).isEqualTo(3);
//    }
//
//    @DisplayName("뺄셈 연산을 수행한다.")
//    @Test
//    void subtractionTest() {
//        int result = Calculator.calculate(1, "-", 2);
//        assertThat(result).isEqualTo(-1);
//    }

//    (2)
    @DisplayName("연산을 수행한다.")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest(int operand1, String operator, int operand2, int result) {
//        int calculateResult = Calculator.calculate(operand1, operator, operand2);
        int calculateResult = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
        assertThat(calculateResult).isEqualTo(result);
    }

    private static Stream<Arguments> formulaAndResult() {
        return Stream.of(
                arguments(1,"+",2,3),
                arguments(5,"-",1,4),
                arguments(4,"*",2,8),
                arguments(4,"/",2,2)
        );
    }

//    @DisplayName("나눗셈에서 0을 나누는 경우 IllegalArgument 예외 throw")
//    @Test
//    void calculateException() {
//        assertThatCode(()-> Calculator.calculate(10, "/", 0))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessage("0으로 나눌 수 없습니다.");
//    }

    @DisplayName("나눗셈에서 0을 나누는 경우 IllegalArgument 예외 throw")
    @Test
    void calculateException() {
        assertThatCode(()-> Calculator.calculate(new PositiveNumber(10), "/", new PositiveNumber(0)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0 또는 음수를 전달할 수 없습니다.");
    }
}


