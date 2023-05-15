package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Validate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class PasswordValidatorTest {
//1. 비밀번호는 최소 8자 이상 12자 이하
//2. 비밀번호가 8자 미만 또는 12자 초과인 경우 lllegalArgumentException 예외 발생
//3. 경계조건에 대해 테스트 코드 작성
//4. 더 낮은 결합도를 만들 수 있다.
    @DisplayName("비밀번호가 최소 8자 이상, 12자 이하면 예외가 발생하지 않는다.")
    @ParameterizedTest
    @ValueSource(strings = {"serverwizard"})
    void validatePasswordTest(String pwd) {
        assertThatCode(() -> PasswordValidator.validate(pwd))
                .doesNotThrowAnyException();
    }

    @DisplayName("비밀번호가 8자 미만 또는 12자 초과인 경우 lllegalArgumentException 예외 발생 ")
    @ParameterizedTest
    @ValueSource(strings = {"aabbcce", "aaaabbbbccccd"})
    void validatePasswordTest2(String password) {
        assertThatCode(() -> PasswordValidator.validate(password))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 8자 이상 12자 이하!");
    }


}
