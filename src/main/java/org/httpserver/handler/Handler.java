package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;

public interface Handler {
    void processRequest(Request request);

    List<HttpMethod> allowedHttpMethods();
//    Response processRequest(Request request) throw IOException;
    Response responseBuilder(Request request);



}
