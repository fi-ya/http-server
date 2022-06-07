package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;

import java.util.List;

public interface Handler {
    List<String> allowedHttpMethods();

    Response handleResponse(Request request);


}
