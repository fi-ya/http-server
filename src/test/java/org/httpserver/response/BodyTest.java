package org.httpserver.response;

import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.POST;
import static org.junit.jupiter.api.Assertions.*;

class BodyTest {

    @Test
    void echo_body() {
        RequestLine mockRequestLine = new RequestLine(POST, "/echo_body", "HTTP/1.1");
        Request mockRequest = new Request(mockRequestLine, new LinkedHashMap<>(), "some body");

        assertEquals("some body", Body.echo_body(mockRequest));
    }

    @Test
    void simple_get_body() {
        assertEquals("Hello world", Body.simple_get_body());
    }

    @Test
    void text_response_body() {
        assertEquals("text response", Body.text_response_body());
    }

    @Test
    void html_response_body() {
        assertEquals("<html><body><p>HTML Response</p></body></html>", Body.html_response_body());
    }
}
