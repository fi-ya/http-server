package org.httpserver.request;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class RequestParserTest {

    @Test
    void parseRequestThatHasRequestLineOnly() throws IOException {
        String mockRequestString = "GET /simple_get HTTP/1.1\n" +
                "\n";
        ByteArrayInputStream mockInputStream = new ByteArrayInputStream(mockRequestString.getBytes());

        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parseRequest(mockInputStream);

        assertEquals("HTTP/1.1", request.getHttpVersion());
        assertEquals("GET", request.getHttpMethod());
        assertEquals("/simple_get", request.getRequestTarget());
        assertTrue(request.getRequestHeaders().isEmpty());
        assertNull(request.getRequestBody());
    }

    @Test
    void parseRequestThatHasRequestLineAndHeadersOnly() throws IOException {
        String mockRequestString = "GET /simple_get HTTP/1.1\n" +
                "Host: 0.0.0.0:5000\n";
        ByteArrayInputStream mockInputStream = new ByteArrayInputStream(mockRequestString.getBytes());

        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parseRequest(mockInputStream);

        assertEquals("HTTP/1.1", request.getHttpVersion());
        assertEquals("GET", request.getHttpMethod());
        assertEquals("/simple_get", request.getRequestTarget());
        assertEquals(new LinkedHashMap<String, String>() {{
            put("Host", "0.0.0.0:5000");
        }}, request.getRequestHeaders());
        assertNull(request.getRequestBody());
    }

}
