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
        return isNoHeader(headerName, headerValue) ? "" : headerName + ": " + headerValue;
    }

    private boolean isNoHeader(String headerName, String headerValue) {
        return Objects.equals(headerName, "") && Objects.equals(headerValue, "");
    }

    default String handleBody(String responseBody) {
        return Objects.equals(responseBody, "") ? "" : responseBody;
    }
}
