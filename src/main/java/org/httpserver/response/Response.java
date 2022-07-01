package org.httpserver.response;

import java.util.Arrays;

public class Response {

    String responseStatusLine;
    String responseHeaders;
    String responseBody;
    byte[] statusLineBytes;
    byte[] headersBytes;
    byte[] bodyBytes;

    public Response(String responseStatusLine, String responseHeaders, byte[] bodyBytes) {
        this.responseStatusLine = responseStatusLine;
        this.responseHeaders = responseHeaders;
        this.bodyBytes = bodyBytes;
    }

    public String body(byte[] bodyBytes) {
        this.responseBody = Arrays.toString(bodyBytes);
        return responseBody;
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
        return getResponseStatusLine() + getResponseHeaders() + body(bodyBytes);
    }

    public byte[] statusLineBytes() {
        statusLineBytes = responseStatusLine.getBytes();
        return statusLineBytes;
    }

    public byte[] headerBytes() {
        headersBytes = responseHeaders.getBytes();
        return headersBytes;
    }
    public byte[] bodyBytes() {
        bodyBytes = responseBody.getBytes();
        return bodyBytes;
    }
}
