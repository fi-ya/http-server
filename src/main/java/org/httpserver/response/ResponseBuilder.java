package org.httpserver.response;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.httpserver.Constant.CRLF;
import static org.httpserver.Constant.HTTP_VERSION_NUMBER;

public class ResponseBuilder {
    private String httpVersion = HTTP_VERSION_NUMBER;
    private StatusCode statusCode;
    private String headerName = "";
    private String headerValue = "";

    private final HashMap<String, String> headers = new HashMap<>();
    private String body = "";
    private HashMap<String, String> headerMap;

    public ResponseBuilder withHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
        return this;
    }

    public ResponseBuilder withStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ResponseBuilder withHeader(String headerName, String headerValue) {
        headers.put(headerName, headerValue);
        return this;
    }

//    public ResponseBuilder withHeaderValue(String headerValue, String head) {
//        this.headerValue = headerValue;
//        return this;
//    }

    public ResponseBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public Response build() {
        return new Response(handleStatusLine(), handleHeaders(), handleBody());
    }

    private String handleStatusLine() {
        return String.format("%s %s %s", httpVersion, statusCode.getStatusCodeNumber(), statusCode.getStatusCodeName()) + CRLF;
    }

    private String handleHeaders() {
        StringBuilder allHeaders = new StringBuilder();

        if (isNoHeader()) {
            return CRLF;
        }

        for (Map.Entry<String, String> header : headers.entrySet()) {
            allHeaders.append(String.format("%s: %s", header.getKey(), header.getValue())).append(CRLF);
        }

        return allHeaders + CRLF;
    }

    private boolean isNoHeader() {
        return headers.isEmpty();
    }

    private String handleBody() {
        return Objects.equals(body, "") ? "" : body;
    }
}
