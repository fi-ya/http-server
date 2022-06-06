package org.httpserver.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;

public class RequestParser {

    public Request parseRequest(BufferedReader clientRequestReader) throws IOException {
        LinkedHashMap<String, String> requestLineMap = getRequestLine(clientRequestReader);
        LinkedHashMap<String, String> requestHeadersMap = getRequestHeaders(clientRequestReader);
        String contentLengthHeaderValue = getContentLengthHeaderValue(requestHeadersMap);
        String requestBody = getRequestMessageBody(contentLengthHeaderValue, clientRequestReader);

        return new Request(requestLineMap, requestHeadersMap, requestBody);
    }


    public LinkedHashMap<String, String> getRequestLine(BufferedReader clientRequestReader) throws IOException {
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

    private LinkedHashMap<String, String> getRequestHeaders(BufferedReader clientRequestReader) throws IOException {
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

    private String getContentLengthHeaderValue(LinkedHashMap<String, String> requestHeadersMap) {
        return requestHeadersMap.isEmpty() ? null : requestHeadersMap.get("Content-Length");
    }

    private String getRequestMessageBody(String contentLengthValue, BufferedReader clientRequestReader) throws IOException {
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
}
