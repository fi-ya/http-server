package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;

import java.util.List;

import static org.httpserver.response.ResponseHeaderName.CONTENT_TYPE;
import static org.httpserver.response.ContentType.TEXT;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.server.HttpMethod.GET;

public class TextHandler implements Handler {
    public List<String> allowedHttpMethods() {
        return List.of(GET.getHttpMethod());
    }

    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeaderName(CONTENT_TYPE)
                .withHeaderValue(TEXT.getValue())
                .withBody("text response")
                .build();
    }
}
