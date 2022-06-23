package org.httpserver.response;

public enum ResponseHeaderName {

    ALLOW_HEADER("Allow"),
    LOCATION_HEADER("Location"),
    CONTENT_LENGTH_HEADER("Content-Length"),
    CONTENT_TYPE_HEADER("Content-Type");


    private final String responseHeader;

    ResponseHeaderName(String responseHeader) {
        this.responseHeader = responseHeader;
    }

    public String getResponseHeaderName() {
        return responseHeader;
    }
}
