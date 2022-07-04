package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.GET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HtmlHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        HtmlHandler htmlHandler = new HtmlHandler();

        assertTrue(htmlHandler.allowedHttpMethods().contains(GET));
        assertEquals(1, htmlHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponse_withStatusLine_withHeaders_AndBody_withText() {
        RequestLine mockRequestLine = new RequestLine(GET, "/html_response", "HTTP/1.1");
        HtmlHandler htmlHandler = new HtmlHandler();

        Response actualResponse = htmlHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().contains("Content-Type"));
        assertTrue(actualResponse.getResponseHeaders().contains("text/html;charset=utf-8"));
        assertEquals("<html><body><p>HTML Response</p></body></html>", actualResponse.getResponseBody());
    }
}
