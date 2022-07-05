package org.httpserver.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

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
    void returnsResponse_withStatusLineAndBody_withJson() throws IOException {
        RequestLine mockRequestLine = new RequestLine(GET, "/json_response", "HTTP/1.1");
        JsonHandler jsonHandler = new JsonHandler();

        Response actualResponse = jsonHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().contains("Content-Type"));
        assertTrue(actualResponse.getResponseHeaders().contains("application/json;charset=utf-8"));
        assertEquals(new HashMap() {{
            put("key1", "value1");
            put("key2", "value2");
        }}, new ObjectMapper().readValue(actualResponse.getBodyBytes(), new TypeReference<>(){}));
    }
}
