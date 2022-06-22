package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HeadRequestHandlerTest {

    @Test
    void allowedHttpMethods_ReturnsHeadOnly() {
        HeadRequestHandler headRequestHandler = new HeadRequestHandler();

        assertTrue(headRequestHandler.allowedHttpMethods().contains(HttpMethod.HEAD.getHttpMethod()));
        assertEquals(1, headRequestHandler.allowedHttpMethods().size());
    }

    @Test
    void handleResponse_ReturnsResponseWith_ResponseStatusLineOnly() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put(Constant.HTTP_VERSION, Constant.HTTP_VERSION_NUMBER);
            put(Constant.HTTP_METHOD, HttpMethod.HEAD.getHttpMethod());
            put(Constant.REQUEST_TARGET, "/head_request");
        }};
        HeadRequestHandler headRequestHandler = new HeadRequestHandler();

        Response actual = headRequestHandler.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), Constant.EMPTY_STRING));

        assertEquals("HTTP/1.1 200 OK\r\n", actual.getResponseStatusLine());
        assertTrue(actual.getResponseHeaders().isBlank());
        assertTrue(actual.getResponseBody().isEmpty());
    }
}
