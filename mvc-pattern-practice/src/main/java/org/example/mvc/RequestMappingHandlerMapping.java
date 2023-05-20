package org.example.mvc;

import org.example.mvc.controller.Controller;
import org.example.mvc.controller.HomeController;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping {

    // key: /users {value} UserController
    private Map<String, Controller> mapping = new HashMap<>();

    void init() {
        // 어떠한 경로도 설정해주지 않으면, HomeController 실행!
        mapping.put("/", new HomeController());
    }

    public Controller findHandler(String uriPath) {
        return mapping.get(uriPath);
    }
}
