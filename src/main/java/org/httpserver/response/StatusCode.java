package org.httpserver.response;

public enum StatusCode {
    OK("200"),
    MOVED_PERMANENTLY("301"),
    NOT_FOUND("404"),
    METHOD_NOT_ALLOWED("405");

    final String statusCode;

    StatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
