package org.httpserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Objects;

public class RequestHandler {
    private final BufferedReader clientRequestReader;
    String httpMethod;
    String requestTarget;
    String httpVersion ;
    String CRLF = "\r\n";
    String SP = " ";
    String statusCode;
    String statusText;
    String responseStatusLine;
    String responseHeaders;
    String responseBody;
    String response;

    LinkedHashMap headersMap;


    public RequestHandler(BufferedReader clientRequestReader){
        this.clientRequestReader = clientRequestReader;
    }

    public void parseClientRequest() throws IOException {
        parseClientRequestLine();
        parseClientRequestHeaders();
    }
    public void parseClientRequestLine() throws IOException {
        String clientRequestLine = readClientRequestLine();
        String[] arrOfSplitRequestLineStr = clientRequestLine.split(" ", 3);
        this.httpMethod = arrOfSplitRequestLineStr[0];
        this.requestTarget = arrOfSplitRequestLineStr[1];
        this.httpVersion = arrOfSplitRequestLineStr[2];
    }

    public String readClientRequestLine() throws IOException {
        return clientRequestReader.readLine();
    }

    private void parseClientRequestHeaders() throws IOException {
        LinkedHashMap <String, String> headersMap = new LinkedHashMap<>();
        String line;
        while((line = clientRequestReader.readLine()) != null) {
            if (line.equals("")) {
                break;
            } else {
                String[] splitHeader = line.split(" ", 2);
                String headerKey = splitHeader[0];
                String headerValue = splitHeader[1];
                headersMap.put(headerKey, headerValue);
            }
        }
        headersMap = this.headersMap;
    }
    public String responseBuilder() {
        statusCode = "200";
        statusText = "OK";
        responseStatusLine = httpVersion + SP + statusCode + SP + statusText + CRLF;
        responseHeaders = "" + CRLF ;

        if (Objects.equals(httpMethod, "GET") || Objects.equals(httpMethod, "HEAD")) {
            if (Objects.equals(requestTarget, "/simple_get") || Objects.equals(requestTarget, "/head_request")) {
                responseBody = "";
            } else {
                if (Objects.equals(requestTarget, "/simple_get_with_body")) {
                    responseBody = "Hello world";
                }
            }
            response = responseStatusLine + CRLF + responseBody;
        } else if (Objects.equals(httpMethod, "OPTIONS")) {
            if (Objects.equals(requestTarget, "/method_options")) {
                responseHeaders = "Allow: GET, HEAD, OPTIONS" + CRLF ;
            } else {
                responseHeaders = "Allow: GET, HEAD, OPTIONS, PUT, POST" + CRLF ;
            }
            responseBody = "";
            response = responseStatusLine + responseHeaders + CRLF + responseBody;
        }
        return response;
    }
}
