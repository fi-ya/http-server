package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;

import java.io.IOException;
import java.util.List;

public interface Handler {
    Response processRequest(Request request) throws IOException;
    List<String> allowedHttpMethods();
    Response responseBuilder(Request request);



}
