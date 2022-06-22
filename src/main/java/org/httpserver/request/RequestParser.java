package org.httpserver.request;

import org.httpserver.Constant;
import org.httpserver.response.ResponseHeader;
import org.httpserver.server.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedHashMap;

public class RequestParser {

    public Request parseRequest(InputStream clientRequestInputStream) throws IOException {
        BufferedReader requestReader = new BufferedReader(new InputStreamReader(clientRequestInputStream));

        String requestLineRead = requestReader.readLine();

        LinkedHashMap<String, String> requestLineMap = getRequestLine(requestLineRead);
        // RequestLine requestLine = getRequestLine(requestLineRead)

        LinkedHashMap<String, String> requestHeadersMap = getRequestHeaders(requestReader);
        String contentLengthHeaderValue = getContentLengthHeaderValue(requestHeadersMap);

        String requestBody = getRequestMessageBody(contentLengthHeaderValue, requestReader);

        // return buildRequest(httpMethod, reqesutTraget, body, requestHeadersMap, requestBody);
        return buildRequest(requestLineMap, requestHeadersMap, requestBody);
    }


    private LinkedHashMap<String, String> getRequestLine(String requestLineRead) throws IOException {
        String clientRequestLine;
        LinkedHashMap<String, String> requestLineMap = new LinkedHashMap<>();
        if ((clientRequestLine = requestLineRead) != null) {
            String[] arrOfSplitRequestLineStr = clientRequestLine.split(" ", 3);
            requestLineMap.put(Constant.HTTP_METHOD, arrOfSplitRequestLineStr[0]);
            requestLineMap.put(Constant.REQUEST_TARGET, arrOfSplitRequestLineStr[1]);
            requestLineMap.put(Constant.HTTP_VERSION, arrOfSplitRequestLineStr[2]);
//            HttpMethod.findHttpMethod(arrOfSplitRequestLineStr[0]);
        }


        return requestLineMap;
    }

    private LinkedHashMap<String, String> getRequestHeaders(BufferedReader requestReader) throws IOException {
        LinkedHashMap<String, String> headersMap = new LinkedHashMap<>();
        String headerLine;

        while ((headerLine = requestReader.readLine()) != null) {
            if (headerLine.equals(Constant.EMPTY_STRING)) {
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
        return requestHeadersMap.isEmpty() ? null : requestHeadersMap.get(ResponseHeader.CONTENT_LENGTH_HEADER.getResponseHeader());
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

    public Request buildRequest(LinkedHashMap<String, String> requestLineMap, LinkedHashMap<String, String> requestHeadersMap, String requestBody) {
        return new Request(requestLineMap, requestHeadersMap, requestBody);
    }
}
