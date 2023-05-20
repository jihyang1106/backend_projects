package org.example.mvc;

import org.apache.coyote.Request;
import org.example.mvc.controller.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// HttpServlet을 상속받아서 DispatcherServlet 클래스가 Servlet이 되면서 톰켓이 실행할 수 있다.
// 어떤 경로로 요청이 와도 DispatcherServlet이 실행이 된다.
@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    // 톰켓이 HttpServlet을 싱글톤 객체로 하나 만들면서 init 메서드가 호출되면서 해당 맵 초기화
    private RequestMappingHandlerMapping reqHandlerMapping;

    @Override
    public void init() throws ServletException {
        // 꼭 객체를 생성하고 초기화 하기! (그냥 초기화만 하면 오류)
        reqHandlerMapping = new RequestMappingHandlerMapping();
        reqHandlerMapping.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("[DispatcherServlet] service started.");

//        try {
//            // 핸들러 매핑을 통해서 handler를 찾는다. => 요청 url에 대한 처리할 수 있는 handler를 달라!
//            Controller handler = reqHandlerMapping.findHandler(req.getRequestURI());
//            // handler로 부터 적절한 controller를 받으면 controller에게 작업을 위임한다.
//            String viewName = handler.handleRequest(req, resp);
//
//            // 해당하는 뷰로 전달!
//            RequestDispatcher reqDispatcher = req.getRequestDispatcher(viewName);
//            reqDispatcher.forward(req,resp);
//        }catch (Exception e) {
//            log.error("exception occured [{}]", e.getMessage(), e);
//            throw new ServletException(e);
//        }
            // 핸들러 매핑을 통해서 handler를 찾는다. => 요청 url에 대한 처리할 수 있는 handler를 달라!
            Controller handleRequest = reqHandlerMapping.findHandler(req.getRequestURI());
            // handler로 부터 적절한 controller를 받으면 controller에게 작업을 위임한다.
            String viewName = handleRequest.handleRequest(req, resp);

            // 해당하는 뷰로 전달!
            RequestDispatcher reqDispatcher = req.getRequestDispatcher(viewName);
            reqDispatcher.forward(req,resp);

    }
}
