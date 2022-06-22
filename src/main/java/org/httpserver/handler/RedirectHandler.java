package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.List;

public class RedirectHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return List.of(HttpMethod.GET.getHttpMethod());
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(StatusCode.MOVED_PERMANENTLY)
                .withHeaderName(Constant.LOCATION_HEADER)
                .withHeaderValue("http://127.0.0.1:5000/simple_get")
                .build();
    }
}
