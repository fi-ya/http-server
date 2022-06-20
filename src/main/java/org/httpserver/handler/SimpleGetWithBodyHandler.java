package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.List;

public class SimpleGetWithBodyHandler implements Handler {

    @Override
    public List<String> allowedHttpMethods() {
        return List.of(HttpMethod.GET.getHttpMethod());
    }

    public Response handleResponse(Request request) {
        StatusCode statusCode = StatusCode.OK;
        String headerName = "";
        String headerValue = "";
        String body = "Hello world";

        return new ResponseBuilder().build(request, statusCode, headerName, headerValue, body);
    }


}
