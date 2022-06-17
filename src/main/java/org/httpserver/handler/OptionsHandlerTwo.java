package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
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
        String responseStatusLine = handleStatusLine(request) + Constant.CRLF;
        String responseHeaders = handleHeaders(request) + Constant.CRLF + Constant.CRLF;
        String responseBody = handleBody();

        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.buildResponse(responseStatusLine, responseHeaders, responseBody);
    }

    private String handleStatusLine(Request request) {
        String httpVersion = request.getHttpVersion();
        String statusCode = StatusCode.OK.getStatusCode();
        String statusText = String.valueOf(StatusCode.OK);

        return String.format("%s %s %s", httpVersion, statusCode, statusText);
    }

    private String handleHeaders(Request request) {
        return "Allow: GET, HEAD, OPTIONS, PUT, POST";
    }

    private String handleBody() {
        return "";
    }
}