package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.response.ResponseHeader;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;

import static org.httpserver.server.HttpMethod.*;

public class OptionsHandlerTwo implements Handler {

    @Override
    public List<HttpMethod> allowedHttpMethods() {
        return Arrays.asList(GET, HEAD, OPTIONS, POST, PUT);
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(StatusCode.OK)
                .withHeaderName(ResponseHeader.ALLOW_HEADER.getResponseHeader())
                .withHeaderValue(String.format("%s, %s, %s, %s, %s", GET, HEAD, OPTIONS, POST, PUT))
                .build();
    }
}
