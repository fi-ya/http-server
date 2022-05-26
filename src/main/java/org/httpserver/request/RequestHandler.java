package org.httpserver.request;

import java.lang.reflect.Array;
import java.util.Arrays;

public class RequestHandler {
    private final String clientRequest;
    String httpMethod;
    String requestTarget;
    String httpVersion ;
    String CRLF = "\r\n";
    String space = " ";
    String statusCode;
    String responseText;


    public RequestHandler(String clientRequest){
        this.clientRequest = clientRequest;
    }

    public void parseClientRequest(){
        String[] arrOfSplitResponseStr = clientRequest.split(" ");
        this.httpMethod = arrOfSplitResponseStr[0];
        this.requestTarget = arrOfSplitResponseStr[1];
        this.httpVersion = arrOfSplitResponseStr[2];
//        System.out.println("parse:" + this.httpVersion );
    }

    public String responseBuilder(){
        statusCode = "200 OK";
        responseText = "";
        String responseStatusLine = httpVersion + space + statusCode + space + responseText + CRLF;
//        System.out.println("response:" + responseStatusLine);
        return responseStatusLine;
    }
}
