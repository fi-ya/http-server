package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.List;

public class HeadRequestHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return List.of(HttpMethod.HEAD.getHttpMethod());
    }

    public Response handleResponse(Request request) {
        String responseStatusLine = handleStatusLine(request, StatusCode.OK) + Constant.CRLF;
        String responseHeaders = handleHeaders("", "") + Constant.CRLF;
        String responseBody = handleBody("");

        ResponseBuilder responseBuilder = new ResponseBuilder();

        return responseBuilder.buildResponse(responseStatusLine, responseHeaders, responseBody);
    }
}
