package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;

public class OptionsHandlerTwo implements Handler {

    @Override
    public List<String> allowedHttpMethods() {
        return Arrays.asList(HttpMethod.GET.getHttpMethod(), HttpMethod.HEAD.getHttpMethod(), HttpMethod.OPTIONS.getHttpMethod(), HttpMethod.POST.getHttpMethod(), HttpMethod.PUT.getHttpMethod());
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder().withStatusCode(StatusCode.OK).withHeaderName("Allow").withHeaderValue("GET, HEAD, OPTIONS, PUT, POST").build(request);
    }
}
