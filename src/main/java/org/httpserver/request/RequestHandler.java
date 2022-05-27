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

//    private void parseRequestHeaders(String clientRequest){
//        System.out.println("client"+clientRequest);
//    }

    public String responseBuilder(){
        statusCode = "200";
        statusText = "OK";
        responseStatusLine = httpVersion + SP + statusCode + SP + statusText + CRLF;

        if (Objects.equals(httpMethod, "GET") || Objects.equals(httpMethod, "HEAD")) {
            if (Objects.equals(requestTarget, "/simple_get")|| Objects.equals(requestTarget, "/head_request")) {
                responseBody = "";
            } else {
                if (Objects.equals(requestTarget, "/simple_get_with_body")) {
                    responseBody = "Hello world";
                }
            }
        }
        response = responseStatusLine + CRLF + responseBody;
        return response;
    }
}
