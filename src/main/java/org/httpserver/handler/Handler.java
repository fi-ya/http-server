package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.StatusCode;

import java.util.List;
import java.util.Objects;

public interface Handler {
    List<String> allowedHttpMethods();

    Response handleResponse(Request request);

    default String handleStatusLine(Request request, StatusCode statusText) {
        String httpVersion = request.getHttpVersion();
        String statusCode = statusText.getStatusCode();
        String statusTextString = String.valueOf(statusText).replace("_", " ");

        return String.format("%s %s %s", httpVersion, statusCode, statusTextString);
    }

    default String handleHeaders(String header, String value) {
        if (Objects.equals(header, "") && Objects.equals(value, "")) {
            return "";
        } else {
            return header + ": " + value;
        }
    }

    default String handleBody(String body) {
        if (Objects.equals(body, "")) {
            return "";
        } else {
            return body;
        }
    }

}
