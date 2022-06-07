package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class SimpleGetHandler implements Handler {

    @Override
    public List<String> allowedHttpMethods() {
        return Arrays.asList(HttpMethod.GET, HttpMethod.HEAD);
    }

    public Response handleResponse(Request request) {
        String CRLF = "\r\n";

        String responseStatusLine = handleStatusLine(request) + CRLF;
        String responseHeaders = handleHeaders() + CRLF;
        String responseBody = handleBody(request);

        return buildResponse(responseStatusLine, responseHeaders, responseBody);
    }

    private String handleStatusLine(Request request){
        String SP = " ";
        String statusCode = "200";
        String statusText = "OK";
        return request.getHttpVersion() + SP + statusCode + SP + statusText;
    }

    private String handleHeaders(){
        return "";
    }

    private String handleBody(Request request){
        if (Objects.equals(request.getRequestTarget(), "/simple_get_with_body")){
            return "Hello world";
        } else{
            return "";
        }
    }
}
