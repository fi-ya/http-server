package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SimpleGetHandler implements Handler {

    @Override
    public List<String> allowedHttpMethods() {
        System.out.println("hh"+ Arrays.asList(HttpMethod.GET.getHttpMethod(), HttpMethod.HEAD.getHttpMethod()));
        return Arrays.asList(HttpMethod.GET.getHttpMethod(), HttpMethod.HEAD.getHttpMethod());
    }

    public Response handleResponse(Request request) {
        String CRLF = "\r\n";

        String responseStatusLine = handleStatusLine(request) + CRLF;
        String responseHeaders = handleHeaders() + CRLF;
        String responseBody = handleBody(request);

        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.buildResponse(responseStatusLine, responseHeaders, responseBody);
    }

    private String handleStatusLine(Request request) {
        String SP = " ";
        String statusCode = StatusCode.OK.getStatusCode();
        String statusText = String.valueOf(StatusCode.OK);

        return request.getHttpVersion() + SP + statusCode + SP + statusText;
    }

    private String handleHeaders() {
        return "";
    }

    private String handleBody(Request request) {
        if (Objects.equals(request.getRequestTarget(), "/simple_get_with_body")) {
            return "Hello world";
        } else {
            return "";
        }
    }
}
