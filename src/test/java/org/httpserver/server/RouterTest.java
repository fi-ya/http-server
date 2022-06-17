package org.httpserver.server;

import org.httpserver.handler.Handler;
import org.httpserver.handler.HeadRequestHandler;
import org.httpserver.handler.MethodNotAllowedHandler;
import org.httpserver.handler.SimpleGetHandler;
import org.httpserver.request.Request;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouterTest {

    private LinkedHashMap<String, String> requestLineStub;
    private LinkedHashMap<String, String> requestHeadersStub;
    private String requestBodyStub;

    @BeforeEach
    void setup() {
        requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpVersion", "HTTP/1.1");
        }};
        requestHeadersStub = new LinkedHashMap<>();
        requestBodyStub = "";
    }

    @Test
    void returnGetHandler_whenGetRoute() {
        requestLineStub.put("httpMethod", "GET");
        requestLineStub.put("requestTarget", "/simple_get");
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        Router router = new Router();

        Handler actualHandler = router.getHandler(requestMock);
        SimpleGetHandler expectedHandler = new SimpleGetHandler();

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }

    @Test
    void returnGetHandler_whenSimpleGetWithBodyRoute() {
        requestLineStub.put("httpMethod", "GET");
        requestLineStub.put("requestTarget", "/simple_get_with_body");
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        Router router = new Router();

        Handler actualHandler = router.getHandler(requestMock);
        SimpleGetHandler expectedHandler = new SimpleGetHandler();

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }

    @Test
    void returnHeadHandler_whenHeadRequestRoute() {
        requestLineStub.put("httpMethod", "HEAD");
        requestLineStub.put("requestTarget", "/head_request");
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        Router router = new Router();

        HeadRequestHandler expectedHandler = new HeadRequestHandler();
        Handler actualHandler = router.getHandler(requestMock);

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }

    @Test
    void returnMethodNotAllowedHandler_whenHttpMethodIncorrect() {
        requestLineStub.put("httpMethod", "GET");
        requestLineStub.put("requestTarget", "/head_request");
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        Router router = new Router();

        MethodNotAllowedHandler expectedHandler = new MethodNotAllowedHandler();
        Handler actualHandler = router.getHandler(requestMock);

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }


}
