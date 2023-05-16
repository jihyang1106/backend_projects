package org.example.gradeCalculator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;

public class CourseTest {

    @DisplayName("과목(코스)를 생성한다.")
    @Test
    void createTest() {
        // Course가 제대로 생성되면 어떠한 예외도 발생시키지 않는다.
        assertThatCode(()-> new Course("OOP", 3, "A+")).doesNotThrowAnyException();
    }


}
