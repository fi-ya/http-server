package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.response.ResponseHeader;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;

public class MethodNotAllowedHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return Arrays.asList(HttpMethod.HEAD.getHttpMethod(), HttpMethod.OPTIONS.getHttpMethod());
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(StatusCode.METHOD_NOT_ALLOWED)
                .withHeaderName(ResponseHeader.ALLOW_HEADER.getResponseHeader())
                .withHeaderValue(String.format("%s, %s", HttpMethod.HEAD, HttpMethod.OPTIONS))
                .build();
    }
}
