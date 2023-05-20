package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {

    // 키는 urlPath, 벨류는 그에 관련한 controller
    // ex) key: /users 경로일때 value : UserController
    private Map<String, Controller> mapping = new HashMap<>();

    void init() {
        // 어떠한 경로도 설정해주지 않으면, HomeController 실행!
        mapping.put("/", new HomeController());
    }

    // uriPath와 일치하는 컨트롤러를 리턴해주는 메서드
    public Controller findHandler(String path) {
        return mapping.get(path);
    }
}
