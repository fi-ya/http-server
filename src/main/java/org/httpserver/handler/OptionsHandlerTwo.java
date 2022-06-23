package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.response.ResponseHeaderName.ALLOW;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.server.HttpMethod.*;

public class OptionsHandlerTwo implements Handler {

    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET, HEAD, OPTIONS, POST, PUT);
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeaderName(ALLOW)
                .withHeaderValue(String.format("%s, %s, %s, %s, %s", GET, HEAD, OPTIONS, POST, PUT))
                .build();
    }
}
