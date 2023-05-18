package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {

    private final int port;

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
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
                 * Step3 - Thread Pool을 적용해 안정적인 서비스가 가능하도록 한다.
                 * */
                executorService.execute(new ClientRequestHandler(clientSocket));
//                new Thread(new ClientRequestHandler(clientSocket)).start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
