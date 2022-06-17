package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
class RedirectHandlerTest {

    @Test
    void returnsGetOnly() {
        RedirectHandler redirectHandler = new RedirectHandler();

        assertTrue(redirectHandler.allowedHttpMethods().contains("GET"));
        assertEquals(1, redirectHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineAndHeaderOnly() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "GET");
            put("requestTarget", "/redirect");
        }};
        Request requestMock = new Request(requestLineStub, new LinkedHashMap<>(), "");
        RedirectHandler redirectHandler = new RedirectHandler();

        Response actualResponse = redirectHandler.handleResponse(requestMock);

        assertEquals("HTTP/1.1 301 MOVED PERMANENTLY\r\n", actualResponse.getResponseStatusLine());
        assertEquals("Location: http://127.0.0.1:5000/simple_get\r\n\r\n", actualResponse.getResponseHeaders());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }
}
