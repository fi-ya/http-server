package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
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
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "POST");
            put("requestTarget", "/echo_body");
        }};
        Request requestMock = new Request(requestLineStub, new LinkedHashMap<>(), "some body");
        EchoBodyHandler echoBodyHandler = new EchoBodyHandler();

        Response actualResponse = echoBodyHandler.handleResponse(requestMock);

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertEquals("some body", actualResponse.getResponseBody());
    }
}
