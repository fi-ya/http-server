package org.httpserver.response;

public enum ResponseHeader {

    ALLOW_HEADER("Allow"),
    LOCATION_HEADER("Location"),
    CONTENT_LENGTH_HEADER("Content-Length"),
    CONTENT_TYPE_HEADER("Content-Type"),
    TEXT_TYPE("text/plain;charset=utf-8");

    private final String responseHeader;

    ResponseHeader(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseHeader() {
        return responseHeader;
    }
}
