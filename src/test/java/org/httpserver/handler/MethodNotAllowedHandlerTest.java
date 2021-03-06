package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MethodNotAllowedHandlerTest {

    @Test
    void allowedMethods_returnsHeadAndOptionsMethodsOnly() {
        MethodNotAllowedHandler methodNotAllowedHandler = new MethodNotAllowedHandler();

        assertTrue(methodNotAllowedHandler.allowedHttpMethods().contains(HEAD));
        assertTrue(methodNotAllowedHandler.allowedHttpMethods().contains(OPTIONS));
        assertEquals(2, methodNotAllowedHandler.allowedHttpMethods().size());
    }

    @Test
    void handleResponse_ReturnsResponseWith_ResponseStatusLine_AndHeadersOnly() {
        RequestLine mockRequestLine = new RequestLine(GET, "/head_request", "HTTP/1.1");
        MethodNotAllowedHandler methodNotAllowedHandler = new MethodNotAllowedHandler();

        Response actual = methodNotAllowedHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 405 Method Not Allowed\r\n", actual.getResponseStatusLine());
        assertEquals("Allow: HEAD, OPTIONS\r\n\r\n", actual.getResponseHeaders());
        assertTrue(new String(actual.getBodyBytes()).isEmpty());
    }
}
