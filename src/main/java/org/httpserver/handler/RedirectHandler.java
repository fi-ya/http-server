package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.response.ResponseHeader;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.server.HttpMethod.GET;

public class RedirectHandler implements Handler {
    @Override
    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET);
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(StatusCode.MOVED_PERMANENTLY)
                .withHeaderName(ResponseHeader.LOCATION_HEADER.getResponseHeader())
                .withHeaderValue("http://127.0.0.1:5000/simple_get")
                .build();
    }
}
