package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.StatusCode;

import java.util.Objects;

public class ResponseBuilder {

    public Response build(Request request, StatusCode statusCode, String headerName, String headerValue, String body) {
        String responseStatusLine = handleStatusLine(request, statusCode) + Constant.CRLF;
        String responseHeaders = handleHeaders(headerName, headerValue) + Constant.CRLF;
        String responseBody = handleBody(body);
        return new Response(responseStatusLine, responseHeaders, responseBody);
    }

    private String handleStatusLine(Request request, StatusCode statusText) {
        String httpVersion = request.getHttpVersion();
        String statusCode = statusText.getStatusCode();
        String statusTextString = String.valueOf(statusText).replace("_", " ");

        return String.format("%s %s %s", httpVersion, statusCode, statusTextString);
    }

    private String handleHeaders(String headerName, String headerValue) {
        return isNoHeader(headerName, headerValue) ? "" : headerName + ": " + headerValue;
    }

    private boolean isNoHeader(String headerName, String headerValue) {
        return Objects.equals(headerName, "") && Objects.equals(headerValue, "");
    }

    private String handleBody(String responseBody) {
        return Objects.equals(responseBody, "") ? "" : responseBody;
    }
}
