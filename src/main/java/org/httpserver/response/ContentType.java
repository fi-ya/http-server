package org.httpserver.response;

public enum ContentType {
    TEXT("text/plain;charset=utf-8"),
    HTML("text/html;charset=utf-8"),
    JPEG("image/jpeg"),
    PNG("image/png"),
    GIF("image/gif");

    private final String value;

    ContentType(String headerValue) {
        this.value = headerValue;
    }

    public String getValue() {
        return value;
    }
}
