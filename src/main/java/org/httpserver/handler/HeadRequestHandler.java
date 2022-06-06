package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;

public class HeadRequestHandler implements Handler{
    @Override
    public List<String> allowedHttpMethods() {
        return List.of(HttpMethod.HEAD);
    }

    public Response responseBuilder(Request request) {
        String SP = " ";
        String CRLF = "\r\n";
        String statusCode = "200";
        String statusText = "OK";
        String responseStatusLine = request.getHttpVersion() + SP + statusCode + SP + statusText + CRLF;
        String responseHeaders = "" + CRLF;
        String responseBody = "";

        ResponseBuilder responseBuilder = new ResponseBuilder();
        responseBuilder.setResponseStatusLine(responseStatusLine);
        responseBuilder.setResponseHeaders(responseHeaders);
        responseBuilder.setResponseBody(responseBody);

        return responseBuilder.buildResponse();
    }
}
