package org.example;

import org.example.calculator.Calculator;
import org.example.calculator.PositiveNumber;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DivisionCalculatorTest {

    @Test
    void CreateTest() {
        Calculator calculator = new Calculator();

        int result = calculator.calculate(new PositiveNumber(10), "/", new PositiveNumber(5));

        assertThat(result).isEqualTo(2);
    }
}
