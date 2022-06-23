package org.httpserver.request;

import org.httpserver.server.HttpMethod;

import java.util.LinkedHashMap;

public class Request {

    private final HttpMethod httpMethod;
    private final String requestTarget;
    private final String httpVersion;
    private final LinkedHashMap<String, String> requestHeaders;
    private final String requestBody;

    public Request(RequestLine requestLine, LinkedHashMap<String, String> requestHeaders, String requestBody) {
        this.httpMethod = requestLine.getHttpMethod();
        this.requestTarget = requestLine.getRequestTarget();
        this.httpVersion = requestLine.getHttpVersion();
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public String getRequestTarget() {
        return requestTarget;
    }

    public String getHttpVersion() {
        return httpVersion;
    }

    public LinkedHashMap<String, String> getRequestHeaders() {
        return requestHeaders;
    }

    public String getRequestBody() {
        return requestBody;
    }


}
