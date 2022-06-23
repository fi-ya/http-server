package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;

import java.util.List;

import static org.httpserver.response.StatusCode.NOT_FOUND;
import static org.httpserver.server.HttpMethod.GET;

public class PageNotFoundHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return List.of(GET.getHttpMethod());
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(NOT_FOUND)
                .build();
    }
}
