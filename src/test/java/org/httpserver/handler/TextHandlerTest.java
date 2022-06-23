package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.GET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        TextHandler textHandler = new TextHandler();

        assertTrue(textHandler.allowedHttpMethods().contains(GET));
        assertEquals(1, textHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponse_withStatusLineAndBody_withText() {
        RequestLine mockRequestLine = new RequestLine(GET, "/text_response", "HTTP/1.1");
        TextHandler textHandler = new TextHandler();

        Response actualResponse = textHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().contains("Content-Type"));
        assertTrue(actualResponse.getResponseHeaders().contains("text/plain;charset=utf-8"));
        assertEquals("text response", actualResponse.getResponseBody());
    }

}
