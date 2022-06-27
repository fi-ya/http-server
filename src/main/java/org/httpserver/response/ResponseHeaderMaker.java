package org.httpserver.response;

public class ResponseHeaderMaker {

    public static String contentLengthHeaderValue(String body) {
        return String.valueOf(body.length());
    }


}
