package org.httpserver.response;

import org.httpserver.request.Request;
import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals("", actualResponse.getResponseBody());
    }

    @Test
    void stringFormatResponse() {
        assertEquals("HTTP/1.1 200 OK", actualResponse.stringFormatResponse());
    }
}
