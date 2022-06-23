package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.response.ResponseHeaderName.ALLOW;
import static org.httpserver.response.StatusCode.METHOD_NOT_ALLOWED;
import static org.httpserver.server.HttpMethod.HEAD;
import static org.httpserver.server.HttpMethod.OPTIONS;

public class MethodNotAllowedHandler implements Handler {
    public List<HttpMethod> allowedHttpMethods() {
        return List.of(HEAD, OPTIONS);
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(METHOD_NOT_ALLOWED)
                .withHeaderName(ALLOW)
                .withHeaderValue(String.format("%s, %s", HEAD, OPTIONS))
                .build();
    }
}
