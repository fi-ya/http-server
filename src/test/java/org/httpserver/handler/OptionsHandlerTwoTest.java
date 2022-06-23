package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionsHandlerTwoTest {

    @Test
    void returnsGetHeadOptionsPostPutMethodsOnly() {
        OptionsHandlerTwo optionsHandlerTwo = new OptionsHandlerTwo();

        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains(GET));
        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains(HEAD));
        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains(OPTIONS));
        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains(POST));
        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains(PUT));
        assertEquals(5, optionsHandlerTwo.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineHeadersAndEmptyBody_whenMethodOptionsTwo() {
        RequestLine mockRequestLine = new RequestLine(OPTIONS, "/method_options2", "HTTP/1.1");
        OptionsHandlerTwo optionsHandlerTwo = new OptionsHandlerTwo();

        Response actualResponse = optionsHandlerTwo.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertEquals("Allow: GET, HEAD, OPTIONS, POST, PUT\r\n\r\n", actualResponse.getResponseHeaders());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }
}
