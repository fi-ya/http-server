package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeadRequestHandlerTest {

    @Test
    void allowedHttpMethods_ReturnsHeadOnly() {

        HeadRequestHandler headRequestHandler = new HeadRequestHandler();

        List<String> actualAllowedMethods = headRequestHandler.allowedHttpMethods();

        assertEquals("[HEAD]", actualAllowedMethods.toString());
    }

    @Test
    void handleResponse_ReturnsResponseWith_ResponseStatusLineOnly() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "HEAD");
            put("requestTarget", "/head_request");
        }};
        LinkedHashMap<String, String> requestHeadersStub = new LinkedHashMap<>();
        String requestBodyStub = "";
        Request mockRequest = new Request(requestLineStub, requestHeadersStub, requestBodyStub);

        HeadRequestHandler headRequestHandler = new HeadRequestHandler();
        Response actual = headRequestHandler.handleResponse(mockRequest);

        assertEquals("HTTP/1.1 200 OK\r\n", actual.getResponseStatusLine());
        assertTrue(actual.getResponseHeaders().isBlank());
        assertTrue(actual.getResponseBody().isEmpty());
    }
}
