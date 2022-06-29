package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.server.HttpMethod.GET;

public class HealthCheckHandler implements Handler{
    @Override
    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET);
    }

    @Override
    public Response handleResponse(Request request) {
        return null;
    }
}
