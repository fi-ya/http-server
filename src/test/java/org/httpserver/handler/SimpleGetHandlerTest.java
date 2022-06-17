package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleGetHandlerTest {

    private LinkedHashMap<String, String> requestLineStub;
    private LinkedHashMap<String, String> requestHeadersStub;
    private String requestBodyStub;

    @BeforeEach
    void setup() {
        requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "GET");
        }};
        requestHeadersStub = new LinkedHashMap<>();
        requestBodyStub = "";
    }

    @Test
    void returnsGetHeadAndMethodsOnly() {
        SimpleGetHandler simpleGetHandler = new SimpleGetHandler();

        assertTrue(simpleGetHandler.allowedHttpMethods().contains("GET"));
        assertTrue(simpleGetHandler.allowedHttpMethods().contains("HEAD"));
        assertEquals(2, simpleGetHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithResponseStatusLineOnly() {
        requestLineStub.put("requestTarget", "/simple_get");
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        SimpleGetHandler simpleGetHandler = new SimpleGetHandler();

        Response actualResponse = simpleGetHandler.handleResponse(requestMock);

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }

    @Test
    void returnsResponseWithResponseStatusLineAndBody() {
        requestLineStub.put("requestTarget", "/simple_get_with_body");
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        SimpleGetHandler simpleGetHandler = new SimpleGetHandler();

        Response actualResponse = simpleGetHandler.handleResponse(requestMock);

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertEquals("Hello world", actualResponse.getResponseBody());
    }
}
