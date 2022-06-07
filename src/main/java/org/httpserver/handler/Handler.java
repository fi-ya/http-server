package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;

import java.util.List;

public interface Handler {
    List<String> allowedHttpMethods();

    Response handleResponse(Request request);

    default Response buildResponse(String responseStatusLine , String responseHeaders, String responseBody){
        ResponseBuilder responseBuilder = new ResponseBuilder();
        responseBuilder.setResponseStatusLine(responseStatusLine);
        responseBuilder.setResponseHeaders(responseHeaders);
        responseBuilder.setResponseBody(responseBody);

        return responseBuilder.buildResponse();
    }


}
