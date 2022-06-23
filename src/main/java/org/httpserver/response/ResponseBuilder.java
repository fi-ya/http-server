package org.httpserver.response;

import org.httpserver.Constant;

import java.util.Objects;

import static org.httpserver.Constant.CRLF;
import static org.httpserver.Constant.HTTP_VERSION_NUMBER;

public class ResponseBuilder {
    private String httpVersion = HTTP_VERSION_NUMBER;
    private StatusCode statusCode;
    private String headerName = "";
    private String headerValue = "";
    private String body = "";

    public ResponseBuilder withHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
        return this;
    }

    public ResponseBuilder withStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ResponseBuilder withHeaderName(String headerName) {
        this.headerName = headerName;
        return this;
    }

    public ResponseBuilder withHeaderValue(String headerValue) {
        this.headerValue = headerValue;
        return this;
    }

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
        return (isNoHeader() ? "" : headerName + ": " + headerValue + CRLF) + CRLF;
    }

    private boolean isNoHeader() {
        return Objects.equals(headerName, "") && Objects.equals(headerValue, "");
    }

    private String handleBody() {
        return Objects.equals(body, "") ? "" : body;
    }
}
