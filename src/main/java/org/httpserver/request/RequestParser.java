package org.httpserver.request;

import org.httpserver.server.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

import static org.httpserver.response.ResponseHeaderName.CONTENT_LENGTH;

public class RequestParser {
    public Request parseRequest(InputStream clientRequestInputStream) throws IOException {
        BufferedReader requestReader = new BufferedReader(new InputStreamReader(clientRequestInputStream));
        String requestLineRead = requestReader.readLine();

        RequestLine requestLine = getRequestLine(requestLineRead);
        LinkedHashMap<String, String> requestHeadersMap = getRequestHeaders(requestReader);
        String contentLengthHeaderValue = getContentLengthHeaderValue(requestHeadersMap);
        String requestBody = getRequestMessageBody(contentLengthHeaderValue, requestReader);

        return buildRequest(requestLine, requestHeadersMap, requestBody);
    }

    private RequestLine getRequestLine(String requestLineRead) {
        String clientRequestLine;

        HttpMethod requestHttpMethod = null;
        String requestTarget = null;
        String requestHttpVersion = null;

        if ((clientRequestLine = requestLineRead) != null) {
            String[] arrOfSplitRequestLineStr = clientRequestLine.split(" ", 3);
            requestHttpMethod = HttpMethod.findHttpMethod(arrOfSplitRequestLineStr[0]);
            requestTarget = arrOfSplitRequestLineStr[1];
            requestHttpVersion = arrOfSplitRequestLineStr[2];
        }
        return new RequestLine(requestHttpMethod, requestTarget, requestHttpVersion);
    }

    private LinkedHashMap<String, String> getRequestHeaders(BufferedReader requestReader) throws IOException {
        LinkedHashMap<String, String> headersMap = new LinkedHashMap<>();
        String headerLine;

        while ((headerLine = requestReader.readLine()) != null) {
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
        return requestHeadersMap.isEmpty() ? null : requestHeadersMap.get(CONTENT_LENGTH);
    }

    private String getRequestMessageBody(String contentLengthValue, BufferedReader requestReader) throws IOException {
        String requestBody = null;

        if (contentLengthValue == null) {
            return null;
        } else {
            int contentLengthInt = Integer.parseInt(contentLengthValue);

            StringBuilder requestMessageBodyStrObj = new StringBuilder();
            for (int i = 0; i < contentLengthInt; i++) {
                requestMessageBodyStrObj.append((char) requestReader.read());
                requestBody = requestMessageBodyStrObj.toString();
            }
        }
        return requestBody;
    }

    public Request buildRequest(RequestLine requestLineMap, LinkedHashMap<String, String> requestHeadersMap, String requestBody) {
        return new Request(requestLineMap, requestHeadersMap, requestBody);
    }
}
