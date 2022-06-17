package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeadRequestHandlerTest {

    @Test
    void allowedHttpMethods_ReturnsHeadOnly() {
        HeadRequestHandler headRequestHandler = new HeadRequestHandler();

        assertTrue(headRequestHandler.allowedHttpMethods().contains("HEAD"));
    }

    @Test
    void handleResponse_ReturnsResponseWith_ResponseStatusLineOnly() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "HEAD");
            put("requestTarget", "/head_request");
        }};
        HeadRequestHandler headRequestHandler = new HeadRequestHandler();

        Response actual = headRequestHandler.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actual.getResponseStatusLine());
        assertTrue(actual.getResponseHeaders().isBlank());
        assertTrue(actual.getResponseBody().isEmpty());
    }
}
