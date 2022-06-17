package org.httpserver.request;

import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestTest {

    private Request request;

    @BeforeEach
    void setup() {
        LinkedHashMap<String, String> requestLine = new LinkedHashMap<>() {{
            put("httpMethod", "GET");
            put("requestTarget", "/simple_get");
            put("httpVersion", "HTTP/1.1");
        }};

        LinkedHashMap<String, String> requestHeaders = new LinkedHashMap<>();
        String requestBody = "";

        request = new Request(requestLine, requestHeaders, requestBody);
    }

    @Test
    void returnsHttpMethod() {
        assertEquals(HttpMethod.GET.getHttpMethod(), request.getHttpMethod());
    }

    @Test
    void returnsRequestTarget() {
        assertEquals("/simple_get", request.getRequestTarget());
    }

    @Test
    void returnsHttpVersion() {
        assertEquals("HTTP/1.1", request.getHttpVersion());
    }

    @Test
    void returnsHeaders() {
        LinkedHashMap<String, String> expectedRequestHeaders = new LinkedHashMap<>();
        assertEquals(expectedRequestHeaders, request.getRequestHeaders());
    }

    @Test
    void returnsRequestBody() {
        assertEquals("", request.getRequestBody());
    }


}
