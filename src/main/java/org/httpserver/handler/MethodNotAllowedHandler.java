package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;

public class MethodNotAllowedHandler implements Handler{
    @Override
    public List<String> allowedHttpMethods() {
        return Arrays.asList(HttpMethod.HEAD, HttpMethod.OPTIONS);
    }

    @Override
    public Response responseBuilder(Request request) {
        String SP = " ";
        String CRLF = "\r\n";
        String statusCode = "405";
        String statusText = "Method Not Allowed";
        String responseStatusLine = request.getHttpVersion() + SP + statusCode + SP + statusText + CRLF;
        String responseHeaders = "Allow: " + allowedHttpMethods() + CRLF;
        String responseBody = "";

        ResponseBuilder responseBuilder = new ResponseBuilder();

        responseBuilder.setResponseStatusLine(responseStatusLine);
        responseBuilder.setResponseHeaders(responseHeaders);
        responseBuilder.setResponseBody(responseBody);

        return responseBuilder.buildResponse();
    }
}
