package org.example;

import java.io.BufferedReader;
import java.io.IOException;

public class HttpRequest {

    private final RequestLine requestLine;

//    private final HttpHeaders httpHeaders;
//    private final Body body;

    public HttpRequest(BufferedReader br) throws IOException {
        this.requestLine = new RequestLine(br.readLine()); // 프로토콜의 첫 번째 라인
    }

    public boolean isGetRequest() {
        return requestLine.isGetRequest();
    }

    public boolean matchPath(String RequestPath) {
        return requestLine.matchPath(RequestPath);
    }

    public QueryStrings getQueryStrings() {
        return requestLine.getQueryStrings();
    }
}
