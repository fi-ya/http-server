package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;

public class OptionsHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return Arrays.asList(HttpMethod.GET.getHttpMethod(), HttpMethod.HEAD.getHttpMethod(), HttpMethod.OPTIONS.getHttpMethod());
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(StatusCode.OK)
                .withHeaderName(Constant.ALLOW_HEADER)
                .withHeaderValue(String.format("%s, %s, %s", HttpMethod.GET, HttpMethod.HEAD, HttpMethod.OPTIONS))
                .build();
    }
}
