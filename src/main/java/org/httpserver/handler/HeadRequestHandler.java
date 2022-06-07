package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.List;

public class HeadRequestHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return List.of(HttpMethod.HEAD);
    }

    public Response handleResponse(Request request) {
        String CRLF = "\r\n";

        String responseStatusLine = handleStatusLine(request) + CRLF;
        String responseHeaders = handleHeaders() + CRLF;
        String responseBody = handleBody();

        ResponseBuilder responseBuilder = new ResponseBuilder();
        responseBuilder.setResponseStatusLine(responseStatusLine);
        responseBuilder.setResponseHeaders(responseHeaders);
        responseBuilder.setResponseBody(responseBody);

        return responseBuilder.buildResponse();
    }

    private String handleStatusLine(Request request) {
        String SP = " ";
        String statusCode = "200";
        String statusText = "OK";
        return request.getHttpVersion() + SP + statusCode + SP + statusText;
    }

    private String handleHeaders() {
        return "";
    }

    private String handleBody() {
        return "";
    }
}
