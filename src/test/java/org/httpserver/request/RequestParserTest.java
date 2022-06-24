package org.httpserver.request;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.GET;
import static org.httpserver.server.HttpMethod.POST;
import static org.junit.jupiter.api.Assertions.*;

class RequestParserTest {

    @Test
    void parseRequestThatHasRequestLineOnly() throws IOException {
        String mockRequestString = """
                GET /simple_get HTTP/1.1

                """;
        Request request = getRequest(mockRequestString);


        assertEquals("HTTP/1.1", request.getHttpVersion());
        assertEquals(GET, request.getHttpMethod());
        assertEquals("/simple_get", request.getRequestTarget());
        assertTrue(request.getRequestHeaders().isEmpty());
        assertNull(request.getRequestBody());
    }

    @Test
    void parseRequestThatHasRequestLineAndHeadersOnly() throws IOException {
        String mockRequestString = """
                GET /simple_get HTTP/1.1
                Host: 0.0.0.0:5000
                """;
        Request request = getRequest(mockRequestString);

        assertEquals("HTTP/1.1", request.getHttpVersion());
        assertEquals(GET, request.getHttpMethod());
        assertEquals("/simple_get", request.getRequestTarget());
        assertEquals(new LinkedHashMap<String, String>() {{
            put("Host", "0.0.0.0:5000");
        }}, request.getRequestHeaders());
        assertNull(request.getRequestBody());
    }

    @Test
    void parseRequestThatHas_requestLine_headers_andBody() throws IOException {
        String mockRequestString = """
                POST /echo_body HTTP/1.1
                Content-Type: text/plain;charset=utf-8;
                Content-Length: 9

                some body
                """;
        Request request = getRequest(mockRequestString);

        assertEquals("HTTP/1.1", request.getHttpVersion());
        assertEquals(POST, request.getHttpMethod());
        assertEquals("/echo_body", request.getRequestTarget());
        assertTrue(request.getRequestHeaders().containsKey("Content-Length"));
        assertTrue(request.getRequestHeaders().containsKey("Content-Type"));
        assertEquals("9", request.getRequestHeaders().get("Content-Length"));
        assertEquals("some body",request.getRequestBody());
    }

    private Request getRequest(String mockRequestString) throws IOException {
        ByteArrayInputStream mockInputStream = new ByteArrayInputStream(mockRequestString.getBytes());
        RequestParser requestParser = new RequestParser();

        return requestParser.parseRequest(mockInputStream);
    }
}
