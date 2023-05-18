package org.example;

import java.util.Objects;

public class RequestLine {

    private final String method; // GET
    private final String urlPath; //  /calculate
//    private String queryString; // operand1=11&operator=*&operand2=55

    // QueryStrings 객체가 알아서 split 해서 key와 value값을 나눠서 List 형태로
    private QueryStrings queryStrings;

    public RequestLine(String method, String urlPath, String queryString) {
        this.method = method;
        this.urlPath = urlPath;
//        this.queryString = queryString;
        this.queryStrings = new QueryStrings(queryString);
    }

    public RequestLine(String requestLine) {
        //GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
        String[] tokens = requestLine.split(" ");
        this.method = tokens[0];  // GET
        String[] urlPathTokens = tokens[1].split("\\?"); // /calculate 와 operand1~
        this.urlPath = urlPathTokens[0];

        if(urlPathTokens.length == 2) { // path와 queryString으로 나눠지면 뒤에 있는 게 queryString
//            this.queryString = urlPathTokens[1];
            this.queryStrings = new QueryStrings(urlPathTokens[1]);
        }

    }

    public boolean isGetRequest() {
        return "GET".equals(this.method);
    }

    public boolean matchPath(String requestPath) {
        return urlPath.equals(requestPath);
    }

    public QueryStrings getQueryStrings() {
        return this.queryStrings;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestLine that = (RequestLine) o;
        return Objects.equals(method, that.method) && Objects.equals(urlPath, that.urlPath) && Objects.equals(queryStrings, that.queryStrings);
    }

    @Override
    public int hashCode() {
        return Objects.hash(method, urlPath, queryStrings);
    }

}
