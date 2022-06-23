package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;

import java.util.List;

import static org.httpserver.response.ResponseHeaderName.CONTENT_TYPE_HEADER;
import static org.httpserver.response.ResponseHeaderValue.TEXT_TYPE;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.server.HttpMethod.GET;

public class TextHandler implements Handler {
    public List<String> allowedHttpMethods() {
        return List.of(GET.getHttpMethod());
    }

    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeaderName(CONTENT_TYPE_HEADER.getResponseHeaderName())
                .withHeaderValue(TEXT_TYPE.getResponseHeaderValue())
                .withBody("text response")
                .build();
    }
}
