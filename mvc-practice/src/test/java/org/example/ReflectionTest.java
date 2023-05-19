package org.example;

import org.example.annotation.Controller;
import org.example.annotation.Service;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
