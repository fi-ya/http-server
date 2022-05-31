package org.httpserver.request;

import java.util.LinkedHashMap;

public class Request {

    private final String httpMethod;
    private final String requestTarget;
    private final String httpVersion;
    private final LinkedHashMap<String, String> requestHeaders;
    private final String requestBody;

    public Request(LinkedHashMap<String, String> requestLine, LinkedHashMap<String, String> requestHeaders, String requestBody) {
        this.httpMethod = requestLine.get("httpMethod");
        this.requestTarget = requestLine.get("requestTarget");
        this.httpVersion = requestLine.get("httpVersion");
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
    }

    public String getHttpMethod() {
        return this.httpMethod;
    }

    public String getRequestTarget() {
        return this.requestTarget;
    }

    public String getHttpVersion() {
        return this.httpVersion;
    }

    public LinkedHashMap<String, String> getRequestHeaders() {
        return this.requestHeaders;
    }

    public String getRequestBody() {
        return this.requestBody;
    }


}
