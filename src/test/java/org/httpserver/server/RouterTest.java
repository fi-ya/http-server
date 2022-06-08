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
    private  String requestBodyStub;

    @BeforeEach
    void setup(){
        requestLineStub = new LinkedHashMap<String, String>() {{
            put("httpVersion", "HTTP/1.1");
        }};
       requestHeadersStub = new LinkedHashMap<>();
       requestBodyStub = "";
    }

    @Test
    void getHandlerReturnsGetHandler_ForSimpleGetRoute() {

        requestLineStub.put("httpMethod", "GET");
        requestLineStub.put("requestTarget", "/simple_get");
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        Router router = new Router();
        Handler actualHandler = router.getHandler(requestMock);

        SimpleGetHandler expectedHandler = new SimpleGetHandler();

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }

    @Test
    void getHandlerReturnsGetHandler_ForSimpleBodyRoute() {

        requestLineStub.put("httpMethod", "GET");
        requestLineStub.put("requestTarget", "/simple_get_with_body");
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        Router router = new Router();
        Handler actualHandler = router.getHandler(requestMock);

        SimpleGetHandler expectedHandler = new SimpleGetHandler();

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }

    @Test
    void getHandlerReturnsHeadRequestHandler_ForHeadRequestRoute() {

        requestLineStub.put("httpMethod", "HEAD");
        requestLineStub.put("requestTarget", "/head_request");
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        Router router = new Router();

        Handler actualHandler = router.getHandler(requestMock);

        HeadRequestHandler expectedHandler = new HeadRequestHandler();

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }

    @Test
    void getHandlerReturnsMethodNotAllowedHandlerForRouteWith_IncorrectHttpMethodSupplied() {

        requestLineStub.put("httpMethod", "GET");
        requestLineStub.put("requestTarget", "/head_request");
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        Router router = new Router();

        Handler actualHandler = router.getHandler(requestMock);

        MethodNotAllowedHandler expectedHandler = new MethodNotAllowedHandler();

        assertEquals(expectedHandler.getClass(), actualHandler.getClass());
    }


}
