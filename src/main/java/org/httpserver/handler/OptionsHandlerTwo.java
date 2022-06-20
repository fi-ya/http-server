package org.httpserver.handler;

import org.httpserver.Constant;
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
        String responseStatusLine = handleStatusLine(request, StatusCode.OK) + Constant.CRLF;
        String responseHeaders = handleHeaders("Allow", "GET, HEAD, OPTIONS, PUT, POST") + Constant.CRLF + Constant.CRLF;
        String responseBody = handleBody("");

        return new Response(responseStatusLine, responseHeaders, responseBody);
    }
}
