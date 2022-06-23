package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.ContentType;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.response.ResponseHeaderName;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.response.ResponseHeaderName.CONTENT_LENGTH;
import static org.httpserver.response.ResponseHeaderName.CONTENT_TYPE;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.server.HttpMethod.GET;
import static org.httpserver.server.HttpMethod.HEAD;

public class SimpleGetWithBodyHandler implements Handler {

    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET, HEAD);
    }

    public Response handleResponse(Request request) {
        String body = "Hello world";

        if (request.getHttpMethod() == HEAD) {
            return new ResponseBuilder()
                    .withStatusCode(OK)
                    .withHeader(CONTENT_TYPE, ContentType.TEXT.getValue())
                    .withHeader(CONTENT_LENGTH, String.valueOf(body.length()))
                    .build();
        }

        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeader(CONTENT_TYPE, ContentType.TEXT.getValue())
                .withHeader(CONTENT_LENGTH, String.valueOf(body.length()))
                .withBody(body)
                .build();
    }
}
