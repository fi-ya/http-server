package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.Test;
import org.w3c.dom.html.HTMLTableCaptionElement;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleGetHandlerTest {
    @Test
    void returnsGetHeadAndMethodsOnly() {
        SimpleGetHandler simpleGetHandler = new SimpleGetHandler();

        assertTrue(simpleGetHandler.allowedHttpMethods().contains("GET"));
        assertTrue(simpleGetHandler.allowedHttpMethods().contains("HEAD"));
        assertEquals(2, simpleGetHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineOnly() {
//        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
//            put("httpVersion", "HTTP/1.1");
//            put("httpMethod", "GET");
//            put("requestTarget", "/simple_get");
//        }};
        RequestLine mockRequestLine = new RequestLine("GET", "/simple_get", "HTTP/1.1");

        SimpleGetHandler simpleGetHandler = new SimpleGetHandler();

        Response actualResponse = simpleGetHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }
}
