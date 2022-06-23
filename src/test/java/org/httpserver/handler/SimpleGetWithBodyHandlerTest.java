package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.GET;
import static org.httpserver.server.HttpMethod.HEAD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleGetWithBodyHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        SimpleGetWithBodyHandler simpleGetWithBodyHandler = new SimpleGetWithBodyHandler();

        assertTrue(simpleGetWithBodyHandler.allowedHttpMethods().contains(GET));
        assertTrue(simpleGetWithBodyHandler.allowedHttpMethods().contains(HEAD));
        assertEquals(2, simpleGetWithBodyHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWith_responseStatusLine_withHeaders_andBody() {
        RequestLine mockRequestLine = new RequestLine(GET, "/simple_get_with_body", "HTTP/1.1");
        SimpleGetWithBodyHandler simpleGetWithBodyHandler = new SimpleGetWithBodyHandler();

        Response actualResponse = simpleGetWithBodyHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        System.out.println("header"+ actualResponse.getResponseHeaders());
        assertTrue(actualResponse.getResponseHeaders().contains("Content-Length"));
        assertTrue(actualResponse.getResponseHeaders().contains("11"));
        assertEquals("Hello world", actualResponse.getResponseBody());
    }

    @Test
    void returnsResponseWith_responseStatusLine_andHeadersOnly() {
        RequestLine mockRequestLine = new RequestLine(HEAD, "/simple_get_with_body", "HTTP/1.1");
        SimpleGetWithBodyHandler simpleGetWithBodyHandler = new SimpleGetWithBodyHandler();

        Response actualResponse = simpleGetWithBodyHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().contains("Content-Length"));
        assertTrue(actualResponse.getResponseHeaders().contains("11"));
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }

}
