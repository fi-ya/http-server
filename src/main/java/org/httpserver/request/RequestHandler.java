package org.httpserver.request;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Objects;

public class RequestHandler {
    private final String clientRequest;
    String httpMethod;
    String requestTarget;
    String httpVersion ;
    String CRLF = "\r\n";
    String space = " ";
    String statusCode;
    String statusText;
    String responseStatusLine;
    String responseBody;
    String response;


    public RequestHandler(String clientRequest){
        this.clientRequest = clientRequest;
    }

    public void parseClientRequest(){
        String[] arrOfSplitResponseStr = clientRequest.split(" ");
        this.httpMethod = arrOfSplitResponseStr[0];
        this.requestTarget = arrOfSplitResponseStr[1];
        this.httpVersion = arrOfSplitResponseStr[2];
    }

    public String responseBuilder(){
        statusCode = "200";
        statusText = "OK";
        responseStatusLine = httpVersion + space + statusCode + space + statusText + CRLF;

        if(Objects.equals(requestTarget, "/simple_get")){
            responseBody = "";
        } else {
            if (Objects.equals(requestTarget, "/simple_get_with_body")) {
                responseBody = "Hello world";
            }
        }

        response = responseStatusLine + CRLF + responseBody;
        return response;
    }
}
