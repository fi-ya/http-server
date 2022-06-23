package org.httpserver.response;

public enum ResponseHeaderValue {
    TEXT_TYPE("text/plain;charset=utf-8"),
    HTML_TYPE("text/html;charset=utf-8");

    private final String responseHeaderValue;

    ResponseHeaderValue(String headerValue) {
        this.responseHeaderValue = headerValue;
    }

    public String getResponseHeaderValue() {
        return responseHeaderValue;
    }
}
