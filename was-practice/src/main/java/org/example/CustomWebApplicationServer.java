package org.example;

import org.example.calculator.Calculator;
import org.example.calculator.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationServer {

    // 서버이기에 port 작성하기
    private final int port;

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    // 생성자
    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        // 해당 포트로 server socket을 만든 다음에 accept로 클라이언트를 기다린다.
        // 클라이언트가 들어오면 .accept( )안으로 들어간다.
        try(ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client.");

            while((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomWebApplicationServer] client connected");

                /**
                 * Step1 - 사용자 요청을 메인 Thread가 처리하도록 한다.
                 */
                // InputStream을 lineByLine으로 읽으려면 InputStreamReader가 필요하다. 또 한 번 더 BufferedReader로 감싸주었다.
                try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                    DataOutputStream dos = new DataOutputStream(out);

                    // 프로토콜 보여주는 부분 삭제
//                    String line;
//                    while((line = br.readLine()) != "") {
//                        System.out.println(line);
//                    }

                    // 프로토콜에 맞게 HttpRequest 객체를 생성하고,
                    HttpRequest httpRequest = new HttpRequest(br);
                    // get요청이면서 path가 calculate인 경우 queryString을 갖고 온다.
                    if(httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
                        QueryStrings queryStrings = httpRequest.getQueryStrings();

                        int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                        String operator = queryStrings.getValue("operator");
                        int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

                        // Response에서 사용할 body 구하기
                        byte[] body = String.valueOf(result).getBytes();

                        HttpResponse response = new HttpResponse(dos);
                        response.response200Header("application/json", body.length);
                        response.responseBody(body);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
