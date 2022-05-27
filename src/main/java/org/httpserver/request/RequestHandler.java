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
    String requestMessageBody;
    String statusCode;
    String statusText;
    String responseStatusLine;
    String responseHeaders;
    String responseBody;
    String response;



    public RequestHandler(BufferedReader clientRequestReader){
        this.clientRequestReader = clientRequestReader;
    }

    public void parseClientRequest() throws IOException {
        parseClientRequestLine();
        LinkedHashMap requestHeaders = parseClientRequestHeaders();
        String contentLengthValue = getContentLengthHeaderValue(requestHeaders);
        parseClientRequestMessageBody(contentLengthValue);
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

    private LinkedHashMap parseClientRequestHeaders() throws IOException {
        LinkedHashMap<String, String> headersMap = new LinkedHashMap<>();
        String line;
        while((line = clientRequestReader.readLine()) != null) {
            if (line.equals("")) {
                break;
            } else {
                String[] splitHeader = line.split(": ", 2);
                String headerKey = splitHeader[0];
                String headerValue = splitHeader[1];
                headersMap.put(headerKey, headerValue);
            }
        }
        return headersMap;
    }

    private String getContentLengthHeaderValue(LinkedHashMap requestHeadersMap) {
        return requestHeadersMap.isEmpty() ? null : (String) requestHeadersMap.get("Content-Length");
    }

    private void parseClientRequestMessageBody(String contentLengthValue) throws IOException {
        System.out.println("contentLengthValue "+contentLengthValue);
        if (contentLengthValue == null){
            requestMessageBody = null;
        } else{
            int contentLengthInt = Integer.parseInt(contentLengthValue);
            System.out.println("length "+contentLengthInt);
            StringBuilder requestMessageBody = new StringBuilder();
            for(int i = 0; i< contentLengthInt; i++){
                requestMessageBody.append(clientRequestReader.readLine());
                System.out.println("mes body"+ requestMessageBody);
            }
//            System.out.println("mes body"+ requestMessageBody.toString().trim());
        }
    }
//    GET /simple_get HTTP/1.1\r\nContent-Length: 3\r\nhii
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
