package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.HEAD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeadRequestHandlerTest {

    @Test
    void allowedHttpMethods_ReturnsHeadOnly() {
        HeadRequestHandler headRequestHandler = new HeadRequestHandler();

        assertTrue(headRequestHandler.allowedHttpMethods().contains(HEAD));
        assertEquals(1, headRequestHandler.allowedHttpMethods().size());
    }

    @Test
    void handleResponse_ReturnsResponseWith_ResponseStatusLineOnly() {
        RequestLine mockRequestLine = new RequestLine(HEAD, "/head_request", "HTTP/1.1");
        HeadRequestHandler headRequestHandler = new HeadRequestHandler();

        Response actual = headRequestHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actual.getResponseStatusLine());
        assertTrue(actual.getResponseHeaders().isBlank());
        assertTrue(actual.getResponseBody().isEmpty());
    }
}
