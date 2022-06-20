package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.StatusCode;

import java.util.List;
import java.util.Objects;

public interface Handler {
    List<String> allowedHttpMethods();

    Response handleResponse(Request request);
}
