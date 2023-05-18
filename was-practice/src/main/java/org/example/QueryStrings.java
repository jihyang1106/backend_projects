package org.example;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QueryStrings {

    private List<QueryString> queryStrings = new ArrayList<>();

    // operand1=11&operator=*&operand2=55
    public QueryStrings(String queryStringLine) {
        String[] queryStringTokens = queryStringLine.split("&");
        Arrays.stream(queryStringTokens)
                .forEach(queryString -> {
                    String[] values = queryString.split("=");
                    if(values.length != 2) { // key value 형태가 아님
                        throw new IllegalArgumentException("잘못된 QueryString 포맷을 가진 문자열");
                    }
                    queryStrings.add(new QueryString(values[0], values[1]));
                });
    }

    // 키가 존재하면 value를 준다..!
//    public String getValue(String key) {
//        return this.queryStrings.stream()
//                .filter(queryString -> queryString.exists(key))
//                .map(QueryString::getValue)
//                .findFirst()
//                .orElse(null);
//    }

    public String getValue(String key) {
        return this.queryStrings.stream()
                .filter(queryString -> queryString.exists(key))
                .map(QueryString::getValue)
                .findFirst()
                .orElse(null);
    }


}
