package org.httpserver.response;

import org.httpserver.client.ClientHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

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

    @Test
    void formatResponseToBytesSuccessfully() throws IOException {

        byte[] actualByte = actualResponse.byteFormatResponse();

        assertEquals("HTTP/1.1 200 OK", new String(actualByte));
        assertEquals("HTTP/1.1 200 OK".getBytes().length, actualByte.length);
    }
}
