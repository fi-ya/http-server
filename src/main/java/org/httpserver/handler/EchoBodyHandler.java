package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;

public class EchoBodyHandler implements Handler{
    @Override
    public List<String> allowedHttpMethods() {
        return List.of(HttpMethod.POST.getHttpMethod());
    }

    @Override
    public Response handleResponse(Request request) {
        return null;
    }
}
