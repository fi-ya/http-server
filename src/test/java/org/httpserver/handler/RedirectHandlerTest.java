package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.GET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RedirectHandlerTest {

    @Test
    void returnsGetOnly() {
        RedirectHandler redirectHandler = new RedirectHandler();

        assertTrue(redirectHandler.allowedHttpMethods().contains(GET));
        assertEquals(1, redirectHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineAndHeaderOnly() {
        RequestLine mockRequestLine = new RequestLine(GET, "/redirect", "HTTP/1.1");
        RedirectHandler redirectHandler = new RedirectHandler();

        Response actualResponse = redirectHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));
        System.out.println("ac " + actualResponse.getResponseHeaders());

        assertEquals("HTTP/1.1 301 Moved Permanently\r\n", actualResponse.getResponseStatusLine());
        assertEquals("Location: " + System.getenv("REDIRECT_URL") + "\r\n\r\n", actualResponse.getResponseHeaders());
        assertTrue(new String(actualResponse.getBodyBytes()).isEmpty());
    }
}
