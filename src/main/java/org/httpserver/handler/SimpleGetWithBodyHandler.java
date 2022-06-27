package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.*;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.response.ResponseHeaderMaker.contentLengthHeaderValue;
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

        ResponseBuilder responseBuilder =  new ResponseBuilder()
                .withStatusCode(OK)
                .withHeader(CONTENT_TYPE, ContentType.TEXT.getValue())
                .withHeader(CONTENT_LENGTH, contentLengthHeaderValue(body));

        return (request.getHttpMethod() == HEAD) ? responseBuilder.build() : responseBuilder.withBody(body).build();
    }
}
