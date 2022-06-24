package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.response.ResponseHeaderName.ALLOW;
import static org.httpserver.response.ResponseHeaderName.LOCATION;
import static org.httpserver.response.StatusCode.MOVED_PERMANENTLY;
import static org.httpserver.server.HttpMethod.*;

public class RedirectHandler implements Handler {
    @Override
    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET);
    }

    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(MOVED_PERMANENTLY)
                .withHeader(LOCATION, "http://127.0.0.1:5000/simple_get")
                .build();
    }
}
