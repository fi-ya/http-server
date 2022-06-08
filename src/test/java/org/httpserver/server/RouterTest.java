package org.httpserver.server;

import org.httpserver.handler.Handler;
import org.httpserver.handler.HeadRequestHandler;
import org.httpserver.handler.SimpleGetHandler;
import org.httpserver.request.Request;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouterTest {

    @Test
    void getHandlerReturnsGetHandlerForSimpleGetRoute() {

        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpMethod", "GET");
            put("requestTarget", "/simple_get");
            put("httpVersion", "HTTP/1.1");
        }};
        LinkedHashMap<String, String> requestHeadersStub = new LinkedHashMap<>();
        String requestBodyStub = "";
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);

        Router router = new Router();
        Handler actualHandler = router.getHandler(requestMock);
        SimpleGetHandler expectedHandler = new SimpleGetHandler();

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }

    @Test
    void getHandlerReturnsGetHandlerForSimpleBodyRoute() {

        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpMethod", "GET");
            put("requestTarget", "/simple_get_with_body");
            put("httpVersion", "HTTP/1.1");
        }};
        LinkedHashMap<String, String> requestHeadersStub = new LinkedHashMap<>();
        String requestBodyStub = "";
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);

        Router router = new Router();
        Handler actualHandler = router.getHandler(requestMock);
        SimpleGetHandler expectedHandler = new SimpleGetHandler();

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }

    @Test
    void getHandlerReturnsGetHandlerForHeadRequestRoute() {

        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpMethod", "HEAD");
            put("requestTarget", "/head_request");
            put("httpVersion", "HTTP/1.1");
        }};
        LinkedHashMap<String, String> requestHeadersStub = new LinkedHashMap<>();
        String requestBodyStub = "";
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);

        Router router = new Router();
        Handler actualHandler = router.getHandler(requestMock);
        HeadRequestHandler expectedHandler = new HeadRequestHandler();

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }


}
