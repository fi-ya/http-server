package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;

import java.util.List;

import static org.httpserver.response.ResponseHeaderName.LOCATION_HEADER;
import static org.httpserver.response.StatusCode.MOVED_PERMANENTLY;
import static org.httpserver.server.HttpMethod.GET;

public class RedirectHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return List.of(GET.getHttpMethod());
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(MOVED_PERMANENTLY)
                .withHeaderName(LOCATION_HEADER.getResponseHeaderName())
                .withHeaderValue("http://127.0.0.1:5000/simple_get")
                .build();
    }
}
