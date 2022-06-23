package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;

import java.util.Arrays;
import java.util.List;

import static org.httpserver.response.ResponseHeaderName.ALLOW_HEADER;
import static org.httpserver.response.StatusCode.METHOD_NOT_ALLOWED;
import static org.httpserver.server.HttpMethod.HEAD;
import static org.httpserver.server.HttpMethod.OPTIONS;

public class MethodNotAllowedHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return Arrays.asList(HEAD.getHttpMethod(), OPTIONS.getHttpMethod());
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(METHOD_NOT_ALLOWED)
                .withHeaderName(ALLOW_HEADER.getResponseHeaderName())
                .withHeaderValue(String.format("%s, %s", HEAD, OPTIONS))
                .build();
    }
}
