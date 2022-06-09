package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class MethodNotAllowedHandlerTest {

    @Test
    void allowedMethods_returnsGetHeadAndOptionsMethodsOnly() {
        MethodNotAllowedHandler methodNotAllowedHandler = new MethodNotAllowedHandler();

        assertEquals("[HEAD, OPTIONS]", methodNotAllowedHandler.allowedHttpMethods().toString());
    }

    @Test
    void handleResponse_ReturnsResponseWith_ResponseStatusLine_AndHeadersOnly() {

        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "GET");
            put("requestTarget", "/head_request");
        }};
        LinkedHashMap<String, String> requestHeadersStub = new LinkedHashMap<>();
        String requestBodyStub = "";
        Request mockRequest = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        MethodNotAllowedHandler methodNotAllowedHandler = new MethodNotAllowedHandler();

        Response actual = methodNotAllowedHandler.handleResponse(mockRequest);

        assertEquals("HTTP/1.1 405 METHOD NOT ALLOWED\r\n", actual.getResponseStatusLine());
        assertEquals("Allow: HEAD, OPTIONS\r\n\r\n", actual.getResponseHeaders());
        assertTrue(actual.getResponseBody().isEmpty());
    }
}
