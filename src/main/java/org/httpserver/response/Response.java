package org.httpserver.response;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Response {
    String responseStatusLine;
    String responseHeaders;
    byte[] statusLineBytes;
    byte[] headersBytes;
    byte[] bodyBytes;

    public Response(String responseStatusLine, String responseHeaders, byte[] bodyBytes) {
        this.responseStatusLine = responseStatusLine;
        this.responseHeaders = responseHeaders;
        this.bodyBytes = bodyBytes;
    }
    public byte[] statusLineBytes() {
        statusLineBytes = responseStatusLine.getBytes();
        return statusLineBytes;
    }

    public byte[] headerBytes() {
        headersBytes = responseHeaders.getBytes();
        return headersBytes;
    }

    public String getResponseStatusLine() {
        return responseStatusLine;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public byte[] getBodyBytes() {
        return bodyBytes;
    }

    public String stringFormatResponse() {
        return new String(statusLineBytes()) + new String(headerBytes()) + new String(getBodyBytes());
    }

    public byte[] byteFormatResponse() throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(statusLineBytes());
        outputStream.write(headerBytes());
        outputStream.write(getBodyBytes());

        return outputStream.toByteArray();
    }
}
