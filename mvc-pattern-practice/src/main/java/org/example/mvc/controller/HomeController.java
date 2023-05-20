package org.example.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

    // HomeController가 호출되면 "home"이라는 화면을 보여준다.
    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse res) {
        return "home.jsp";
//        return "home";
    }
}
