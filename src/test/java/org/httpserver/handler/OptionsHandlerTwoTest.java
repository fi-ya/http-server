package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class OptionsHandlerTwoTest {

    @Test
    void returnsGetHeadOptionsPostPutMethodsOnly() {
        OptionsHandlerTwo optionsHandlerTwo = new OptionsHandlerTwo();

        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains("GET"));
        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains("HEAD"));
        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains("OPTIONS"));
        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains("POST"));
        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains("PUT"));
        assertEquals(5, optionsHandlerTwo.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineHeadersAndEmptyBody_whenMethodOptionsTwo() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "OPTIONS");
            put("requestTarget", "/method_options2");
        }};
        Request requestMock = new Request(requestLineStub, new LinkedHashMap<>(), "");
        OptionsHandlerTwo optionsHandlerTwo = new OptionsHandlerTwo();

        Response actualResponse = optionsHandlerTwo.handleResponse(requestMock);

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertEquals("Allow: GET, HEAD, OPTIONS, PUT, POST\r\n\r\n", actualResponse.getResponseHeaders());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }
}
