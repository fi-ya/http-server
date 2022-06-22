package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionsHandlerTest {

    @Test
    void returnsGetHeadAndOptionsMethodsOnly() {
        OptionsHandler optionsHandler = new OptionsHandler();

        assertTrue(optionsHandler.allowedHttpMethods().contains(HttpMethod.GET.getHttpMethod()));
        assertTrue(optionsHandler.allowedHttpMethods().contains(HttpMethod.HEAD.getHttpMethod());
        assertTrue(optionsHandler.allowedHttpMethods().contains(HttpMethod.OPTIONS.getHttpMethod()));
        assertEquals(3, optionsHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineHeadersAndEmptyBody_whenMethodOptions() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put(Constant.HTTP_VERSION, Constant.HTTP_VERSION_NUMBER);
            put(Constant.HTTP_METHOD, HttpMethod.GET.getHttpMethod());
            put(Constant.REQUEST_TARGET, "/method_options");
        }};
        OptionsHandler optionsHandler = new OptionsHandler();

        Response actualResponse = optionsHandler.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), Constant.EMPTY_STRING));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertEquals("Allow: GET, HEAD, OPTIONS\r\n\r\n", actualResponse.getResponseHeaders());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }
}
