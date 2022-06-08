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
        LinkedHashMap<String, String> requestLine = new LinkedHashMap<String, String>() {{
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


}
