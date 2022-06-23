package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.List;
import java.util.Objects;

public interface Handler {
    List<HttpMethod> allowedHttpMethods();

    Response handleResponse(Request request);
}
