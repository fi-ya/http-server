package org.httpserver.request;

public enum RequestLine {
    HTTP_VERSION_NUMBER("HTTP/1.1"),
    HTTP_VERSION("httpVersion"),

    HTTP_METHOD("httpMethod"),
    REQUEST_TARGET("requestTarget");

    final String value;

    RequestLine(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
