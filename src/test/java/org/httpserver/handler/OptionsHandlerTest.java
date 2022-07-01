package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionsHandlerTest {

    @Test
    void returnsGetHeadAndOptionsMethodsOnly() {
        OptionsHandler optionsHandler = new OptionsHandler();

        assertTrue(optionsHandler.allowedHttpMethods().contains(GET));
        assertTrue(optionsHandler.allowedHttpMethods().contains(HEAD));
        assertTrue(optionsHandler.allowedHttpMethods().contains(OPTIONS));
        assertEquals(3, optionsHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineHeadersAndEmptyBody_whenMethodOptions() {
        RequestLine mockRequestLine = new RequestLine(OPTIONS, "/method_options", "HTTP/1.1");
        OptionsHandler optionsHandler = new OptionsHandler();

        Response actualResponse = optionsHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertEquals("Allow: GET, HEAD, OPTIONS\r\n\r\n", actualResponse.getResponseHeaders());
        assertTrue(new String(actualResponse.getBodyBytes()).isEmpty());
    }
}
