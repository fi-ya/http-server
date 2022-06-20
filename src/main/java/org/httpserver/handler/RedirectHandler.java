package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
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
                .withStatusCodeText(StatusCode.MOVED_PERMANENTLY.name().replace("_", " "))
                .withHeaderName("Location").withHeaderValue("http://127.0.0.1:5000/simple_get")
                .build();
    }
}
