package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class TextHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        TextHandler textHandler = new TextHandler();

        assertTrue(textHandler.allowedHttpMethods().contains("GET"));
        assertEquals(1, textHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponse_withStatusLineAndBody_withText() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "GET");
            put("requestTarget", "/text_response");
        }};
        TextHandler textHandler = new TextHandler();

        Response actualResponse = textHandler.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().contains("Content-Type"));
        assertTrue(actualResponse.getResponseHeaders().contains("text/plain;charset=utf-8"));
        assertEquals("text response", actualResponse.getResponseBody());
    }

}
