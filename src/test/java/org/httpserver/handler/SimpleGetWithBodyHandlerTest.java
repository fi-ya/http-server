package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;
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
//        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
//            put("httpVersion", "HTTP/1.1");
//            put("httpMethod", "GET");
//            put("requestTarget", "/simple_get_with_body");
//        }};

        RequestLine mockRequestLine = new RequestLine("GET", "/simple_get_with_body", "HTTP/1.1");
        SimpleGetWithBodyHandler simpleGetWithBodyHandler = new SimpleGetWithBodyHandler();

        Response actualResponse = simpleGetWithBodyHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertEquals("Hello world", actualResponse.getResponseBody());
    }

}
