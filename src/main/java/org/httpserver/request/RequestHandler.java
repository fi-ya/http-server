package org.httpserver.request;

import java.util.Objects;

public class RequestHandler {
    private final String clientRequest;
    String httpMethod;
    String requestTarget;
    String httpVersion;


    public RequestHandler(String clientRequest) {
        this.clientRequest = clientRequest;
    }

    public void parseClientRequest() {
        String[] arrOfSplitResponseStr = clientRequest.split(" ");
        this.httpMethod = arrOfSplitResponseStr[0];
        this.requestTarget = arrOfSplitResponseStr[1];
        this.httpVersion = arrOfSplitResponseStr[2];
    }

    public String responseBuilder() {
        String statusCode = "200";
        String statusText = "OK";
        String SP = " ";
        String CRLF = "\r\n";
        String responseBody;

        String responseStatusLine = httpVersion + SP + statusCode + SP + statusText + CRLF;

        if (Objects.equals(requestTarget, "/simple_get")) {
            responseBody = "";
        } else {
            responseBody = "Hello world";
        }
        return responseStatusLine + CRLF + responseBody;
    }
}
