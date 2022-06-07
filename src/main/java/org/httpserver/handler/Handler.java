package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;

import java.util.List;

public interface Handler {
    List<String> allowedHttpMethods();

    Response handleResponse(Request request);


}
