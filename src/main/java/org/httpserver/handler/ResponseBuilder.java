package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.response.Response;
import org.httpserver.response.StatusCode;

import java.util.Objects;

public class ResponseBuilder {
    private String httpVersion = "HTTP/1.1";

    private StatusCode statusCode;
    private String headerName = "";
    private String headerValue = "";
    private String body = "";
    private String statusCodeText;

    public ResponseBuilder withHttpVersion(String httpVersion) {
        this.httpVersion = httpVersion;
        return this;
    }
    ResponseBuilder withStatusCode(StatusCode statusCode){
        this.statusCode = statusCode;
        return this;
    }

    ResponseBuilder withStatusCodeText(String statusCodeText){
        this.statusCodeText = statusCodeText;
        return this;
    }

    ResponseBuilder withHeaderName(String headerName){
        this.headerName = headerName;
        return this;
    }

    ResponseBuilder withHeaderValue(String headerValue){
        this.headerValue = headerValue;
        return this;
    }

    ResponseBuilder withBody(String body){
        this.body = body;
        return this;
    }

    public Response build() {
        return new Response(handleStatusLine(), handleHeaders(), handleBody());
    }

    private String handleStatusLine() {
        return String.format("%s %s %s", httpVersion, statusCode.getStatusCode(), statusCodeText) + Constant.CRLF;
    }
    private String handleHeaders() {
        return (isNoHeader() ? "" : headerName + ": " + headerValue + Constant.CRLF) + Constant.CRLF;
    }

    private boolean isNoHeader() {
        return Objects.equals(headerName, "") && Objects.equals(headerValue, "");
    }

    private String handleBody() {
        return Objects.equals(body, "") ? "" : body;
    }
}
