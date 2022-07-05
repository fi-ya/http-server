package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.POST;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EchoBodyHandlerTest {

    @Test
    void returnsPostOnly() {
        EchoBodyHandler echoBodyHandler = new EchoBodyHandler();

        assertTrue(echoBodyHandler.allowedHttpMethods().contains(POST));
        assertEquals(1, echoBodyHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineAndBodyOnly() {
        RequestLine mockRequestLine = new RequestLine(POST, "/echo_body", "HTTP/1.1");
        EchoBodyHandler echoBodyHandler = new EchoBodyHandler();

        Response actualResponse = echoBodyHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), "some body"));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().contains("Content-Length"));
        assertTrue(actualResponse.getResponseHeaders().contains("9"));
        assertEquals("some body", new String(actualResponse.getBodyBytes()));
    }
}
