package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.Test;
import org.w3c.dom.html.HTMLTableCaptionElement;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleGetHandlerTest {
    @Test
    void returnsGetHeadAndMethodsOnly() {
        SimpleGetHandler simpleGetHandler = new SimpleGetHandler();

        assertTrue(simpleGetHandler.allowedHttpMethods().contains(HttpMethod.GET.getHttpMethod()));
        assertTrue(simpleGetHandler.allowedHttpMethods().contains(HttpMethod.HEAD.getHttpMethod()));
        assertEquals(2, simpleGetHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineOnly() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put(Constant.HTTP_VERSION, Constant.HTTP_VERSION_NUMBER);
            put(Constant.HTTP_METHOD, HttpMethod.GET.getHttpMethod());
            put(Constant.REQUEST_TARGET, "/simple_get");
        }};
        SimpleGetHandler simpleGetHandler = new SimpleGetHandler();

        Response actualResponse = simpleGetHandler.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), Constant.EMPTY_STRING));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }
}
