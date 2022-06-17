package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.List;
import java.util.Objects;

public class EchoBodyHandler implements Handler {
    @Override
    public List<String> allowedHttpMethods() {
        return List.of(HttpMethod.POST.getHttpMethod());
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
        return request.getRequestBody();
    }
}
