package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;

public class MethodNotAllowedHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return Arrays.asList(HttpMethod.HEAD, HttpMethod.OPTIONS);
    }

    @Override
    public Response handleResponse(Request request) {
        String CRLF = "\r\n";

        String responseStatusLine = handleStatusLine(request) + CRLF;
        String responseHeaders = handleHeaders() + CRLF + CRLF;
        String responseBody = handleBody();

        return buildResponse(responseStatusLine, responseHeaders, responseBody);
    }

    private String handleStatusLine(Request request) {
        String SP = " ";
        String statusCode = "405";
        String statusText = "Method Not Allowed";
        return request.getHttpVersion() + SP + statusCode + SP + statusText;
    }

    private String handleHeaders() {
        return "Allow: HEAD, OPTIONS";
    }

    private String handleBody() {
        return "";
    }
}
