package org.httpserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Objects;

public class RequestHandler {
    private final BufferedReader clientRequestReader;
    String httpMethod;
    String requestTarget;
    String httpVersion;


    public RequestHandler(BufferedReader clientRequestReader) {
        this.clientRequestReader = clientRequestReader;
    }

    public String processClientRequest() throws IOException {
        parseClientRequestLine();
        LinkedHashMap requestHeaders = parseClientRequestHeaders();
        String contentLengthValue = getContentLengthHeaderValue(requestHeaders);
        String requestBody = parseClientRequestMessageBody(contentLengthValue);

        return responseBuilder(requestBody);
    }

    public void parseClientRequestLine() throws IOException {
        String clientRequestLine;

        if ((clientRequestLine = readClientRequestLine()) != null) {
            String[] arrOfSplitRequestLineStr = clientRequestLine.split(" ", 3);
            this.httpMethod = arrOfSplitRequestLineStr[0];
            this.requestTarget = arrOfSplitRequestLineStr[1];
            this.httpVersion = arrOfSplitRequestLineStr[2];
        }
    }

    private String readClientRequestLine() throws IOException {
        return clientRequestReader.readLine();
    }

    private LinkedHashMap parseClientRequestHeaders() throws IOException {
        LinkedHashMap<String, String> headersMap = new LinkedHashMap<>();
        String headerLine;

        while ((headerLine = clientRequestReader.readLine()) != null) {
            if (headerLine.equals("")) {
                break;
            } else {
                String[] splitHeader = headerLine.split(": ", 2);
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

    private String parseClientRequestMessageBody(String contentLengthValue) throws IOException {
        String requestBody = null;

        if (contentLengthValue == null) {
            return null;
        } else {
            int contentLengthInt = Integer.parseInt(contentLengthValue);

            StringBuilder requestMessageBodyStrObj = new StringBuilder();
            for (int i = 0; i < contentLengthInt; i++) {
                requestMessageBodyStrObj.append((char) clientRequestReader.read());
                requestBody = requestMessageBodyStrObj.toString();
            }
        }
        return requestBody;
    }


    public String responseBuilder(String requestBody) {
        String statusCode = "200";
        String statusText = "OK";
        String SP = " ";
        String CRLF = "\r\n";
        String responseBody = "";
        String responseStatusLine = httpVersion + SP + statusCode + SP + statusText + CRLF;
        String responseHeaders = "" + CRLF;
        String response = "";

        if (Objects.equals(httpMethod, "GET") && Objects.equals(requestTarget, "/head_request")) {
            statusCode = "405";
            statusText = "Method Not Allowed";
            responseStatusLine = httpVersion + SP + statusCode + SP + statusText + CRLF;
            responseHeaders = "Allow: HEAD, OPTIONS" + CRLF;
            responseBody = "";
            response = responseStatusLine + responseHeaders + CRLF + responseBody;
            return response;
        }

        if (Objects.equals(httpMethod, "GET") || Objects.equals(httpMethod, "HEAD")) {
            if (Objects.equals(requestTarget, "/simple_get") || Objects.equals(requestTarget, "/head_request")) {
                response = responseStatusLine + CRLF + responseBody;
            } else if (Objects.equals(requestTarget, "/simple_get_with_body")) {
                responseBody = "Hello world";
                response = responseStatusLine + CRLF + responseBody;
            } else if (Objects.equals(requestTarget, "/redirect")) {
                statusCode = "301";
                statusText = "Moved Permanently";
                responseStatusLine = httpVersion + SP + statusCode + SP + statusText + CRLF;
                responseHeaders = "Location: http://127.0.0.1:5000/simple_get" + CRLF;
                responseBody = "";
                response = responseStatusLine + responseHeaders + CRLF + responseBody;
            } else {
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

        if (Objects.equals(httpMethod, "POST") && Objects.equals(requestTarget, "/echo_body")) {
            responseBody = requestBody;
            response = responseStatusLine + responseHeaders + responseBody;
        }

        return response;
    }
}
