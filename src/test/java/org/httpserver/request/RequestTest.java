package org.httpserver.request;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.GET;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestTest {

    private Request request;

    @BeforeEach
    void setup() {
        RequestLine mockRequestLine = new RequestLine(GET, "/simple_get", "HTTP/1.1");
        LinkedHashMap<String, String> requestHeaders = new LinkedHashMap<>();
        String requestBody = "";

        request = new Request(mockRequestLine, requestHeaders, requestBody);
    }

    @Test
    void returnsHttpMethod() {
        assertEquals(GET, request.getHttpMethod());
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
