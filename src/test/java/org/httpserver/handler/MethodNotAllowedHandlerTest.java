package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class MethodNotAllowedHandlerTest {

    @Test
    void allowedMethods_returnsGetHeadAndOptionsMethodsOnly() {
        MethodNotAllowedHandler methodNotAllowedHandler = new MethodNotAllowedHandler();

        assertTrue(methodNotAllowedHandler.allowedHttpMethods().contains("HEAD"));
        assertTrue(methodNotAllowedHandler.allowedHttpMethods().contains("OPTIONS"));
        assertEquals(3, methodNotAllowedHandler.allowedHttpMethods().size());
    }

    @Test
    void handleResponse_ReturnsResponseWith_ResponseStatusLine_AndHeadersOnly() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "GET");
            put("requestTarget", "/head_request");
        }};
        MethodNotAllowedHandler methodNotAllowedHandler = new MethodNotAllowedHandler();

        Response actual = methodNotAllowedHandler.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 405 METHOD NOT ALLOWED\r\n", actual.getResponseStatusLine());
        assertEquals("Allow: HEAD, OPTIONS\r\n\r\n", actual.getResponseHeaders());
        assertTrue(actual.getResponseBody().isEmpty());
    }
}
