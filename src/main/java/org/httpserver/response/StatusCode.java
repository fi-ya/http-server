package org.httpserver.response;

public enum StatusCode {
    OK("200", "OK"),
    MOVED_PERMANENTLY("301", "Moved Permanently"),
    NOT_FOUND("404", "Not Found"),
    METHOD_NOT_ALLOWED("405", "Method Not Allowed");

    final String statusCode;
    final String statusCodeName;

    StatusCode(String statusCode, String statusCodeName) {
        this.statusCode = statusCode;
        this.statusCodeName = statusCodeName;
    }

    public String getStatusCode() {
        return statusCode;
    }
}
