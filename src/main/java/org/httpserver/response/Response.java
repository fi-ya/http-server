package org.httpserver.response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class Response {

    String responseStatusLine;
    String responseHeaders;
    String responseBody;
    byte[] statusLineBytes;
    byte[] headersBytes;
    byte[] bodyBytes;

    public Response(String responseStatusLine, String responseHeaders, String responseBody) {
        this.responseStatusLine = responseStatusLine;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
    }

    public String getResponseStatusLine() {
        return responseStatusLine;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public byte[] getStatusLineBytes() {
        return statusLineBytes;
    }

    public byte[] getHeadersBytes() {
        return headersBytes;
    }

    public byte[] getBodyBytes() {
        return bodyBytes;
    }

    public String stringFormatResponse() {
        return getResponseStatusLine() + getResponseHeaders() + getResponseBody();
    }

    public byte[] setStatusLineBytes() {
        statusLineBytes = responseStatusLine.getBytes();
        return statusLineBytes;
    }

    public byte[] setHeaderBytes() {
        headersBytes = responseHeaders.getBytes();
        return headersBytes;
    }

    public byte[] setBodyBytes() {
        bodyBytes = responseBody.getBytes();
        return bodyBytes;
    }
}
