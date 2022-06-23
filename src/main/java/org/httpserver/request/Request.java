package org.httpserver.request;

import java.util.LinkedHashMap;

import static org.httpserver.Constant.*;

public class Request {

    private final String httpMethod;
    private final String requestTarget;
    private final String httpVersion;
    private final LinkedHashMap<String, String> requestHeaders;
    private final String requestBody;

    public Request(LinkedHashMap<String, String> requestLine, LinkedHashMap<String, String> requestHeaders, String requestBody) {
        this.httpMethod = requestLine.get(HTTP_METHOD);
        this.requestTarget = requestLine.get(REQUEST_TARGET);
        this.httpVersion = requestLine.get(HTTP_VERSION);
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
    }

    public String getHttpMethod() {
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
