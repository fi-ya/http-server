package org.httpserver.response;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.httpserver.Constant.CRLF;
import static org.httpserver.Constant.HTTP_VERSION_NUMBER;

public class ResponseBuilder {
    private final String httpVersion = HTTP_VERSION_NUMBER;
    private StatusCode statusCode;
    private final HashMap<String, String> headers = new HashMap<>();
    private String body = "";

    public ResponseBuilder withStatusCode(StatusCode statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public ResponseBuilder withHeader(String[] headerElements) {
        headers.put(headerElements[0], headerElements[1]);
        return this;
    }

    public ResponseBuilder withBody(String body) {
        this.body = body;
        return this;
    }

    public Response build() {
        return new Response(handleStatusLine(), handleHeaders(), handleBody());
    }

    private String handleStatusLine() {
        return String.format("%s %s %s", httpVersion, statusCode.getStatusCodeNumber(), statusCode.getStatusCodeName()) + CRLF;
    }

    private String handleHeaders() {
        StringBuilder allHeaders = new StringBuilder();

        if (headers.isEmpty()) {
            return CRLF;
        }

        for (Map.Entry<String, String> header : headers.entrySet()) {
            allHeaders.append(String.format("%s: %s", header.getKey(), header.getValue())).append(CRLF);
        }

        return allHeaders + CRLF;
    }

    private String handleBody() {
        return Objects.equals(body, "") ? "" : body;
    }

    private String bodyLength(){ return String.valueOf(body.length());}
}
