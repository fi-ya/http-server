package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.List;

public class RedirectHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return List.of(HttpMethod.GET.getHttpMethod());
    }

    @Override
    public Response handleResponse(Request request) {
        String responseStatusLine = handleStatusLine(request, StatusCode.MOVED_PERMANENTLY) + Constant.CRLF;
        String responseHeaders = handleHeaders("Location","http://127.0.0.1:5000/simple_get" ) + Constant.CRLF + Constant.CRLF;
        String responseBody = handleBody("");

        return new Response(responseStatusLine, responseHeaders, responseBody);
    }

}
