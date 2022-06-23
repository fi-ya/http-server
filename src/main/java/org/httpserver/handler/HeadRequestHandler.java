package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.server.HttpMethod.HEAD;

public class HeadRequestHandler implements Handler {
    public List<HttpMethod> allowedHttpMethods() {
        return List.of(HEAD);
    }

    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(OK)
                .build();
    }
}
