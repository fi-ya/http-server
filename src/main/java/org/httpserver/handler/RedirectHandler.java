package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
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
        String CRLF = "\r\n";

        String responseStatusLine = handleStatusLine(request) + CRLF;
        String responseHeaders = handleHeaders(request) + CRLF + CRLF;
        String responseBody = handleBody();

        ResponseBuilder responseBuilder = new ResponseBuilder();
        return responseBuilder.buildResponse(responseStatusLine, responseHeaders, responseBody);
    }

    private String handleStatusLine(Request request) {
        String SP = " ";
        String statusCode = StatusCode.MOVED_PERMANENTLY.getStatusCode();
        String statusText = String.valueOf(StatusCode.MOVED_PERMANENTLY).replace("_"," ");

        return request.getHttpVersion() + SP + statusCode + SP + statusText;
    }

    private String handleHeaders(Request request) {
        return "Location: http://127.0.0.1:5000/simple_get";
    }

    private String handleBody() {
        return "";
    }
}
