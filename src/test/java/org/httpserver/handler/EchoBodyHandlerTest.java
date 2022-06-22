package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EchoBodyHandlerTest {

    @Test
    void returnsPostOnly() {
        EchoBodyHandler echoBodyHandler = new EchoBodyHandler();

        assertTrue(echoBodyHandler.allowedHttpMethods().contains("POST"));
        assertEquals(1, echoBodyHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineAndBodyOnly() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "POST");
            put("requestTarget", "/echo_body");
        }};
        EchoBodyHandler echoBodyHandler = new EchoBodyHandler();

        Response actualResponse = echoBodyHandler.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), "some body"));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertEquals("some body", actualResponse.getResponseBody());
    }
}
