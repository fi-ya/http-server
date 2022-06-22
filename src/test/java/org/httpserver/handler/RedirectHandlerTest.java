package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RedirectHandlerTest {

    @Test
    void returnsGetOnly() {
        RedirectHandler redirectHandler = new RedirectHandler();

        assertTrue(redirectHandler.allowedHttpMethods().contains("GET"));
        assertEquals(1, redirectHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineAndHeaderOnly() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "GET");
            put("requestTarget", "/redirect");
        }};
        RedirectHandler redirectHandler = new RedirectHandler();

        Response actualResponse = redirectHandler.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 301 MOVED PERMANENTLY\r\n", actualResponse.getResponseStatusLine());
        assertEquals("Location: http://127.0.0.1:5000/simple_get\r\n\r\n", actualResponse.getResponseHeaders());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }
}
