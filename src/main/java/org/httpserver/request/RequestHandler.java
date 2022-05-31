package org.httpserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Objects;

public class RequestHandler {


    public String processClientRequest(BufferedReader clientRequestReader) throws IOException {
        LinkedHashMap requestLineMap = parseClientRequestLine(clientRequestReader);
        LinkedHashMap requestHeadersMap = parseClientRequestHeaders(clientRequestReader);
        String contentLengthValue = getContentLengthHeaderValue(requestHeadersMap);
        String requestBody = parseClientRequestMessageBody(contentLengthValue, clientRequestReader);

        return responseBuilder(requestLineMap, requestBody);
    }

    public LinkedHashMap parseClientRequestLine(BufferedReader clientRequestReader) throws IOException {
        String clientRequestLine;
        LinkedHashMap<String, String> requestLineMap = new LinkedHashMap<>();
        if ((clientRequestLine = clientRequestReader.readLine()) != null) {
            String[] arrOfSplitRequestLineStr = clientRequestLine.split(" ", 3);
            requestLineMap.put("httpMethod", arrOfSplitRequestLineStr[0]);
            requestLineMap.put("requestTarget", arrOfSplitRequestLineStr[1]);
            requestLineMap.put("httpVersion", arrOfSplitRequestLineStr[2]);
        }
        return requestLineMap;
    }


    private LinkedHashMap parseClientRequestHeaders(BufferedReader clientRequestReader) throws IOException {
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

    private String parseClientRequestMessageBody(String contentLengthValue, BufferedReader clientRequestReader) throws IOException {
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


    public String responseBuilder(LinkedHashMap requestLineMap, String requestBody) {
        String httpVersion = (String) requestLineMap.get("httpVersion");
        String httpMethod = (String) requestLineMap.get("httpMethod");
        String requestTarget = (String) requestLineMap.get("requestTarget");
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
