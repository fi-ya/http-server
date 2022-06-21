package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleGetWithBodyHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        SimpleGetWithBodyHandler simpleGetWithBodyHandler = new SimpleGetWithBodyHandler();

        assertTrue(simpleGetWithBodyHandler.allowedHttpMethods().contains("GET"));
        assertEquals(1, simpleGetWithBodyHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithResponseStatusLineAndBody() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "GET");
            put("requestTarget", "/simple_get_with_body");
        }};
        SimpleGetWithBodyHandler simpleGetWithBodyHandler = new SimpleGetWithBodyHandler();

        Response actualResponse = simpleGetWithBodyHandler.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertEquals("Hello world", actualResponse.getResponseBody());
    }

}
