package org.httpserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Objects;

public class RequestHandler {
    private final BufferedReader clientRequestReader;
    String httpMethod;
    String requestTarget;
    String httpVersion;
    String requestBody;


    public RequestHandler(BufferedReader clientRequestReader) {
        this.clientRequestReader = clientRequestReader;
    }

    public void parseClientRequest() throws IOException {
        parseClientRequestLine();
        LinkedHashMap requestHeaders = parseClientRequestHeaders();
        String contentLengthValue = getContentLengthHeaderValue(requestHeaders);
    }

    public void parseClientRequestLine() throws IOException {
        String clientRequestLine;
        if ((clientRequestLine = readClientRequestLine()) !=null) {
                String[] arrOfSplitRequestLineStr = clientRequestLine.split(" ", 3);
                System.out.println("arrOfSplitRequestLineStr:"+ Arrays.toString(arrOfSplitRequestLineStr));
                this.httpMethod = arrOfSplitRequestLineStr[0];
                this.requestTarget = arrOfSplitRequestLineStr[1];
                this.httpVersion = arrOfSplitRequestLineStr[2];
       }
    }

    public String readClientRequestLine() throws IOException {
        return clientRequestReader.readLine();
    }

    private LinkedHashMap parseClientRequestHeaders() throws IOException {
        LinkedHashMap<String, String> headersMap = new LinkedHashMap<>();
        String line;
        while ((line = clientRequestReader.readLine()) != null) {
            if (line.equals("")) {
                break;
            } else {
                String[] splitHeader = line.split(": ", 2);
                String headerKey = splitHeader[0];
                String headerValue = splitHeader[1];
                headersMap.put(headerKey, headerValue);
            }
        }
        System.out.println("headersmap+"+headersMap);
        return headersMap;
    }

    private String getContentLengthHeaderValue(LinkedHashMap requestHeadersMap) {
        return requestHeadersMap.isEmpty() ? null : (String) requestHeadersMap.get("Content-Length");
    }


    public String responseBuilder() {
        String statusCode = "200";
        String statusText = "OK";
        String SP = " ";
        String CRLF = "\r\n";
        String responseBody = "";
        String responseStatusLine = httpVersion + SP + statusCode + SP + statusText + CRLF;
        String responseHeaders = "" + CRLF;
        String response = "";

        if (Objects.equals(httpMethod, "GET") || Objects.equals(httpMethod, "HEAD")) {
            if (Objects.equals(requestTarget, "/simple_get") || Objects.equals(requestTarget, "/head_request")) {
                response = responseStatusLine + CRLF + responseBody;

            } else if (Objects.equals(requestTarget, "/simple_get_with_body")) {
                responseBody = "Hello world";
                response = responseStatusLine + CRLF + responseBody;
            } else{
                statusCode = "404";
                statusText = "Not Found";
                responseStatusLine = httpVersion + SP + statusCode + SP + statusText + CRLF;
                response = responseStatusLine + CRLF;
            }
           return response;
        }

        if (Objects.equals(httpMethod, "OPTIONS")) {
            if (Objects.equals(requestTarget, "/method_options")) {
                responseHeaders = "Allow: GET, HEAD, OPTIONS" + CRLF;
            } else {
                responseHeaders = "Allow: GET, HEAD, OPTIONS, PUT, POST" + CRLF;
            }
            responseBody = "";
            response = responseStatusLine + responseHeaders + CRLF + responseBody;
        }
        return response;
    }
}
