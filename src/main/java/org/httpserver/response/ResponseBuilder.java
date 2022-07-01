package org.httpserver.response;

import java.util.HashMap;
import java.util.Map;

import static org.httpserver.Constant.CRLF;
import static org.httpserver.Constant.HTTP_VERSION_NUMBER;

public class ResponseBuilder {
    private final String httpVersion = HTTP_VERSION_NUMBER;
    private StatusCode statusCode;
    private final HashMap<String, String> headers = new HashMap<>();
    public byte[] bodyByte = "".getBytes();

    public ResponseBuilder withStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ResponseBuilder withHeader(String[] headerElements) {
        headers.put(headerElements[0], headerElements[1]);
        return this;
    }

    public ResponseBuilder withBodyByte(byte[] bodyByte) {
        this.bodyByte = bodyByte;
        return this;
    }

    public Response build() {
        return new Response(buildStatusLine(), buildHeaders(), buildBodyByte());
    }

    private String buildStatusLine() {
        return String.format("%s %s %s", httpVersion, statusCode.getStatusCodeNumber(), statusCode.getStatusCodeName()) + CRLF;
    }

    private String buildHeaders() {
        StringBuilder allHeaders = new StringBuilder();

        if (headers.isEmpty()) {
            return CRLF;
        }

        for (Map.Entry<String, String> header : headers.entrySet()) {
            allHeaders.append(String.format("%s: %s", header.getKey(), header.getValue())).append(CRLF);
        }

        return allHeaders + CRLF;
    }

    private byte[] buildBodyByte() {
        return new String(bodyByte).equals("") ? "".getBytes() : bodyByte;
    }
}
