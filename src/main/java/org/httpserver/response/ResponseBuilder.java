package org.httpserver.response;

import org.httpserver.Constant;

import java.util.Objects;

public class ResponseBuilder {
    private String httpVersion = Constant.HTTP_VERSION_NUMBER;
    private StatusCode statusCode;
    private String headerName = Constant.EMPTY_STRING;
    private String headerValue = Constant.EMPTY_STRING;
    private String body = Constant.EMPTY_STRING;

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
        return String.format("%s %s %s", httpVersion, statusCode.getStatusCodeNumber(), statusCode.getStatusCodeName()) + Constant.CRLF;
    }

    private String handleHeaders() {
        return (isNoHeader() ? Constant.EMPTY_STRING : headerName + ": " + headerValue + Constant.CRLF) + Constant.CRLF;
    }

    private boolean isNoHeader() {
        return Objects.equals(headerName, Constant.EMPTY_STRING) && Objects.equals(headerValue, Constant.EMPTY_STRING);
    }

    private String handleBody() {
        return Objects.equals(body, Constant.EMPTY_STRING) ? Constant.EMPTY_STRING : body;
    }
}
