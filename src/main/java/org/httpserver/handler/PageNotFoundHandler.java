package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.List;

public class PageNotFoundHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return List.of(HttpMethod.GET.getHttpMethod());
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder().withStatusCode(StatusCode.NOT_FOUND).build(request);
    }
}
