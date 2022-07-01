package org.httpserver.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ResponseTest {

    private Response actualResponse;

    @BeforeEach
    void setup() {
        String responseStatusLine = "HTTP/1.1 200 OK";
        String responseHeaders = "";
        byte[] requestBody = "".getBytes();

        actualResponse = new Response(responseStatusLine, responseHeaders, requestBody);
    }

    @Test
    void getResponseStatusLine() {
        assertEquals("HTTP/1.1 200 OK", actualResponse.getResponseStatusLine());
    }

    @Test
    void getResponseHeaders() {
        assertEquals("", actualResponse.getResponseHeaders());
    }

    @Test
    void getResponseBody() {
        assertEquals("", new String(actualResponse.getBodyBytes()));
    }

    @Test
    void stringFormatResponse() {
        assertEquals("HTTP/1.1 200 OK", actualResponse.stringFormatResponse());
    }
}
