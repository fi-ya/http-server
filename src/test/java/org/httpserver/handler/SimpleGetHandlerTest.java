package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

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
    void allowedMethods_returnsGe_AndHead_MethodsOnly() {
        SimpleGetHandler simpleGetHandler = new SimpleGetHandler();

        assertEquals("[GET, HEAD]", simpleGetHandler.allowedHttpMethods().toString());
    }

    @Test
    void handleResponse_ReturnsResponseWith_ResponseStatusLineOnly() {
        requestLineStub.put("requestTarget", "/simple_get");
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        SimpleGetHandler simpleGetHandler = new SimpleGetHandler();

        Response actualResponse = simpleGetHandler.handleResponse(requestMock);

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }

    @Test
    void handleResponse_ReturnsResponseWith_ResponseStatusLine_AndBody() {
        requestLineStub.put("requestTarget", "/simple_get_with_body");
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        SimpleGetHandler simpleGetHandler = new SimpleGetHandler();

        Response actualResponse = simpleGetHandler.handleResponse(requestMock);

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertEquals("Hello world", actualResponse.getResponseBody());
    }
}
