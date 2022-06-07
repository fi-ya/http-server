package org.httpserver.request;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestParserTest {

    @Test
    void parseRequestLineSuccessfully() throws IOException {
        String mockRequestString = "GET /simple_get HTTP/1.1\n" +
                "\n";
        ByteArrayInputStream mockInputStream = new ByteArrayInputStream(mockRequestString.getBytes());

        RequestParser requestParser = new RequestParser();
        Request request = requestParser.parseRequest(mockInputStream);

        assertEquals("HTTP/1.1",request.getHttpVersion());

    }

}
