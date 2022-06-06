package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;

import java.util.List;

public interface Handler {
    default Response handleResponse(Request request) {
        return  responseBuilder(request);
    }
    List<String> allowedHttpMethods();
    Response responseBuilder(Request request);



}
