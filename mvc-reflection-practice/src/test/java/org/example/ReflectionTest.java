package org.example;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.junit.Assert.*;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

/** Controller 애노테이션이 설정돼 있는 모든 클래스를 찾아서 출력 */
public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    // reflections api 사용
    @Test
    void controllerScan() {
        // 해당 패키지(org.example) 밑에 Controller라는
        // 애노테이션이 붙어 있는 모든 것(addAll)을 찾아서 HashSet에 담는다.
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        beans.addAll(reflections.getTypesAnnotatedWith(Controller.class));

        logger.debug("beans: [{}]", beans);
    }

    @Test
    void serviceScan() {
        // Ctrl + Alt + m => 메서드 추출하는 명령어
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Service.class));
        logger.debug("beans: [{}]", beans);
    }
    private Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        // 특정 패키지(org.example) 안에 있는 코드를 대상으로 한다.
        Reflections reflections = new Reflections("org.example");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }

    @Test
    void showClass() {
        Class<User> user = User.class;
        logger.debug(user.getName());

        // 유저에 선언된 모든 필드 출력
        logger.debug("User all declared fields : [{}]",
                Arrays.stream(user.getDeclaredFields()).collect(Collectors.toList()));
        // 유저 모델에 선언된 생성자 출력
        logger.debug("User all declared constructors : [{}]",
                Arrays.stream(user.getConstructors()).collect(Collectors.toList()));
        // 유저 모델에 선언된 모든 메서드 출력
        logger.debug("User all declared methods : [{}]",
                Arrays.stream(user.getDeclaredMethods()).collect(Collectors.toList()));
    }

    @Test
    void load() throws ClassNotFoundException {
        // 힙 영역에 로드 되어있는 클래스 타입의 객체 가져오는 방법 세 가지
        // 1. 클래스 이름.class
        Class<User> class1 = User.class;

        // 2. 클래스.getClass
        User user2 = new User("server", "이서버");
        // user2.getClass + (alt + enter)
        Class<? extends User> class2 = user2.getClass();

        // 3. 클래스.forName
        // Class.forName("org.example.User") + (alt + enter)
        Class<?> class3 = Class.forName("org.example.model.User");

        logger.debug("class1 : [{}]", class1);
        logger.debug("class2 : [{}]", class2);
        logger.debug("class3 : [{}]", class3);

        assertThat(class1 == class2).isTrue();
        assertThat(class2 == class3).isTrue();
        assertThat(class1 == class3).isTrue();

        assertThat(class1).isEqualTo(class2);
        assertThat(class1).isEqualTo(class2);
        assertThat(class1).isEqualTo(class2);
    }
}
