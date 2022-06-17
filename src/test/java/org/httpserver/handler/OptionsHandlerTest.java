package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionsHandlerTest {

    @Test
    void returnsGetHeadAndOptionsMethodsOnly() {
        OptionsHandler optionsHandler = new OptionsHandler();

        assertTrue(optionsHandler.allowedHttpMethods().contains("GET"));
        assertTrue(optionsHandler.allowedHttpMethods().contains("HEAD"));
        assertTrue(optionsHandler.allowedHttpMethods().contains("OPTIONS"));
    }

    @Test
    void returnsResponseWithStatusLineHeadersAndEmptyBody_whenMethodOptions() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "GET");
            put("requestTarget", "/method_options");
        }};
        LinkedHashMap<String, String> requestHeadersStub = new LinkedHashMap<>();
        String requestBodyStub = "";
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        OptionsHandler optionsHandler = new OptionsHandler();

        Response actualResponse = optionsHandler.handleResponse(requestMock);

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertEquals("Allow: GET, HEAD, OPTIONS\r\n", actualResponse.getResponseHeaders());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }

    @Test
    void returnsResponseWithStatusLineHeadersAndEmptyBody_whenMethodOptions2() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "OPTIONS");
            put("requestTarget", "/method_options2");
        }};
        LinkedHashMap<String, String> requestHeadersStub = new LinkedHashMap<>();
        String requestBodyStub = "";
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        OptionsHandler optionsHandler = new OptionsHandler();

        Response actualResponse = optionsHandler.handleResponse(requestMock);

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertEquals("Allow: GET, HEAD, OPTIONS, PUT, POST\r\n", actualResponse.getResponseHeaders());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }
}
