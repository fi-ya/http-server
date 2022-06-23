package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;

import java.util.Arrays;
import java.util.List;

import static org.httpserver.response.ResponseHeaderName.ALLOW_HEADER;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.server.HttpMethod.*;

public class OptionsHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return Arrays.asList(GET.getHttpMethod(), HEAD.getHttpMethod(), OPTIONS.getHttpMethod());
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeaderName(ALLOW_HEADER.getResponseHeaderName())
                .withHeaderValue(String.format("%s, %s, %s", GET, HEAD, OPTIONS))
                .build();
    }
}
