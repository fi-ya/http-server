package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.GET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        JsonHandler jsonHandler = new JsonHandler();

        assertTrue(jsonHandler.allowedHttpMethods().contains(GET));
        assertEquals(1, jsonHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponse_withStatusLineAndBody_withJson() {
        RequestLine mockRequestLine = new RequestLine(GET, "/json_response", "HTTP/1.1");
        JsonHandler jsonHandler = new JsonHandler();

        Response actualResponse = jsonHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().contains("Content-Type"));
        assertTrue(actualResponse.getResponseHeaders().contains("application/json;charset=utf-8"));
        assertEquals("{\"key1\":\"value1\",\"key2\":\"value2\"}", actualResponse.getResponseBody());
    }
}
