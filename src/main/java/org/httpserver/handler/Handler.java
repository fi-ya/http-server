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

    default String handleHeaders(String headerName, String headerValue) {
        if (Objects.equals(headerName, "") && Objects.equals(headerValue, "")) {
            return "";
        } else {
            return headerName + ": " + headerValue;
        }
    }

    default String handleBody(String responseBody) {
        if (Objects.equals(responseBody, "")) {
            return "";
        } else {
            return responseBody;
        }
    }

}
