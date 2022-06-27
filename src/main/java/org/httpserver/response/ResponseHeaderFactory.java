package org.httpserver.response;

public class ResponseHeaderFactory {

    public String contentLengthHeaderValue(String body) {
        return String.valueOf(body.length());
    }
}
