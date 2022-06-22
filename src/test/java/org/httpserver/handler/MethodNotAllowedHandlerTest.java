package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class MethodNotAllowedHandlerTest {

    @Test
    void allowedMethods_returnsHeadAndOptionsMethodsOnly() {
        MethodNotAllowedHandler methodNotAllowedHandler = new MethodNotAllowedHandler();

        assertTrue(methodNotAllowedHandler.allowedHttpMethods().contains(HttpMethod.HEAD.getHttpMethod()));
        assertTrue(methodNotAllowedHandler.allowedHttpMethods().contains(HttpMethod.OPTIONS.getHttpMethod()));
        assertEquals(2, methodNotAllowedHandler.allowedHttpMethods().size());
    }

    @Test
    void handleResponse_ReturnsResponseWith_ResponseStatusLine_AndHeadersOnly() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put(Constant.HTTP_VERSION, Constant.HTTP_VERSION_NUMBER);
            put(Constant.HTTP_METHOD, HttpMethod.GET.getHttpMethod());
            put(Constant.REQUEST_TARGET, "/head_request");
        }};
        MethodNotAllowedHandler methodNotAllowedHandler = new MethodNotAllowedHandler();

        Response actual = methodNotAllowedHandler.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), Constant.EMPTY_STRING));

        assertEquals("HTTP/1.1 405 METHOD NOT ALLOWED\r\n", actual.getResponseStatusLine());
        assertEquals("Allow: HEAD, OPTIONS\r\n\r\n", actual.getResponseHeaders());
        assertTrue(actual.getResponseBody().isEmpty());
    }
}
