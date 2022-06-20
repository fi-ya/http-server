package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;

public class MethodNotAllowedHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return Arrays.asList(HttpMethod.HEAD.getHttpMethod(), HttpMethod.OPTIONS.getHttpMethod());
    }

    @Override
    public Response handleResponse(Request request) {
        String responseStatusLine = handleStatusLine(request, StatusCode.METHOD_NOT_ALLOWED) + Constant.CRLF;
        String responseHeaders = handleHeaders("Allow", "HEAD, OPTIONS") + Constant.CRLF + Constant.CRLF;
        String responseBody = handleBody("");


        return new Response(responseStatusLine, responseHeaders, responseBody);
    }
}
