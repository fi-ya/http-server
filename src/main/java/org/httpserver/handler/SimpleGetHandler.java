package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;

public class SimpleGetHandler implements Handler {

    @Override
    public void processRequest(Request request) {
//        Response response = null;
        System.out.println("method: " + request.getHttpMethod());
//        boolean requestHasAllowedHttpMethod = allowedHttpMethods().contains(request.getHttpMethod());
//        if (requestHasAllowedHttpMethod) {
//            response = responseBuilder(request);
//        }
//        return new Response("responseStatusLine", "responseHeaders", "responseBody");
    }


    @Override
    public List<HttpMethod> allowedHttpMethods() {
        return Arrays.asList(HttpMethod.GET, HttpMethod.HEAD);
    }

    public Response responseBuilder(Request request) {
        String SP = " ";
        String CRLF = "\r\n";
        String statusCode = "200";
        String statusText = "OK";
        String responseStatusLine = request.getHttpVersion() + SP + statusCode + SP + statusText + CRLF;
        String responseHeaders = "" + CRLF;
        String responseBody = "";

        ResponseBuilder responseBuilder = new ResponseBuilder();
        responseBuilder.setResponseStatusLine(responseStatusLine);
        responseBuilder.setResponseHeaders(responseHeaders);
        responseBuilder.setResponseBody(responseBody);

        return responseBuilder.buildResponse();

    }
}
