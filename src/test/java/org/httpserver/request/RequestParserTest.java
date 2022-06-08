package org.httpserver.request;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class RequestParserTest {

    @Test
    void parseRequestThatHasRequestLineOnly() throws IOException {
        String mockRequestString = "GET /simple_get HTTP/1.1\n" +
                "\n";
        ByteArrayInputStream mockInputStream = new ByteArrayInputStream(mockRequestString.getBytes());
        LinkedHashMap<String, String> expectedHeadersMap = new LinkedHashMap<>();

        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parseRequest(mockInputStream);

        assertEquals("HTTP/1.1",request.getHttpVersion());
        assertEquals("GET",request.getHttpMethod());
        assertEquals("/simple_get",request.getRequestTarget());
        assertEquals( expectedHeadersMap,request.getRequestHeaders());
        assertNull(request.getRequestBody());
    }

    @Test
    void parseRequestThatHasRequestLineAndHeadersOnly() throws IOException {
        String mockRequestString = "GET http://localhost:5000/simple_get HTTP/1.1\n" +
                "Host: 0.0.0.0:5000\n";
        ByteArrayInputStream mockInputStream = new ByteArrayInputStream(mockRequestString.getBytes());
        LinkedHashMap<String, String> expectedHeadersMap = new LinkedHashMap<String, String>() {{
            put("Host", "0.0.0.0:5000");
        }};

        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parseRequest(mockInputStream);

        assertEquals("HTTP/1.1",request.getHttpVersion());
        assertEquals("GET",request.getHttpMethod());
        assertEquals("http://localhost:5000/simple_get",request.getRequestTarget());
        assertEquals( expectedHeadersMap,request.getRequestHeaders());
        assertNull(request.getRequestBody());
    }

}
