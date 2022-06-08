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

    private LinkedHashMap<String, String> requestHeadersStub;
    private  String requestBodyStub;

    @BeforeEach
    void setup(){
        LinkedHashMap<String, String> requestHeadersStub = new LinkedHashMap<>();
        String requestBodyStub = "";

    }

    @Test
    void getHandlerReturnsGetHandler_ForSimpleGetRoute() {

        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpMethod", "GET");
            put("requestTarget", "/simple_get");
            put("httpVersion", "HTTP/1.1");
        }};

        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        Router router = new Router();
        Handler actualHandler = router.getHandler(requestMock);

        SimpleGetHandler expectedHandler = new SimpleGetHandler();

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }

    @Test
    void getHandlerReturnsGetHandler_ForSimpleBodyRoute() {

        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpMethod", "GET");
            put("requestTarget", "/simple_get_with_body");
            put("httpVersion", "HTTP/1.1");
        }};

        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        Router router = new Router();
        Handler actualHandler = router.getHandler(requestMock);

        SimpleGetHandler expectedHandler = new SimpleGetHandler();

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }

    @Test
    void getHandlerReturnsHeadRequestHandler_ForHeadRequestRoute() {

        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpMethod", "HEAD");
            put("requestTarget", "/head_request");
            put("httpVersion", "HTTP/1.1");
        }};

        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);

        Router router = new Router();
        Handler actualHandler = router.getHandler(requestMock);

        HeadRequestHandler expectedHandler = new HeadRequestHandler();

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }

    @Test
    void getHandlerReturnsMethodNotAllowedHandlerForRouteWith_IncorrectHttpMethodSupplied() {

        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpMethod", "GET");
            put("requestTarget", "/head_request");
            put("httpVersion", "HTTP/1.1");
        }};

        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        Router router = new Router();
        Handler actualHandler = router.getHandler(requestMock);

        MethodNotAllowedHandler expectedHandler = new MethodNotAllowedHandler();

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }


}
