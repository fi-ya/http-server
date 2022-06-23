package org.httpserver.server;

import org.httpserver.handler.*;
import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RouterTest {

    private RequestLine mockRequestLine;

    private LinkedHashMap<String, String> requestHeadersStub;
    private String requestBodyStub;

    @BeforeEach
    void setup() {
        requestHeadersStub = new LinkedHashMap<>();
        requestBodyStub = "";
    }

    @Test
    void returnGetHandler_whenGetRoute() {
        mockRequestLine = new RequestLine("GET", "/simple_get", "HTTP/1.1");
//        requestLineStub.put("httpMethod", "GET");
//        requestLineStub.put("requestTarget", "/simple_get");

        Handler actualHandler = getActualHandler();

        assertEquals(SimpleGetHandler.class, actualHandler.getClass());
    }

    @Test
    void returnGetHandler_whenSimpleGetWithBodyRoute() {
        mockRequestLine = new RequestLine("GET", "/simple_get_with_body", "HTTP/1.1");
//        requestLineStub.put("httpMethod", "GET");
//        requestLineStub.put("requestTarget", "/simple_get_with_body");

        Handler actualHandler = getActualHandler();

        assertEquals(SimpleGetWithBodyHandler.class, actualHandler.getClass());
    }

    @Test
    void returnHeadHandler_whenHeadRequestRoute() {
        mockRequestLine = new RequestLine("HEAD", "/head_request", "HTTP/1.1");
//        requestLineStub.put("httpMethod", "HEAD");
//        requestLineStub.put("requestTarget", "/head_request");

        Handler actualHandler = getActualHandler();

        assertEquals(HeadRequestHandler.class, actualHandler.getClass());
    }

    @Test
    void returnMethodNotAllowedHandler_whenHttpMethodIncorrect() {
        mockRequestLine = new RequestLine("GET", "/head_request", "HTTP/1.1");
//        requestLineStub.put("httpMethod", "GET");
//        requestLineStub.put("requestTarget", "/head_request");

        Handler actualHandler = getActualHandler();

        assertEquals(MethodNotAllowedHandler.class, actualHandler.getClass());
    }

    @Test
    void returnOptionsHandler_whenMethodOptionsRoute() {
        mockRequestLine = new RequestLine("OPTIONS", "/method_options", "HTTP/1.1");
//        requestLineStub.put("httpMethod", "OPTIONS");
//        requestLineStub.put("requestTarget", "/method_options");

        Handler actualHandler = getActualHandler();

        assertEquals(OptionsHandler.class, actualHandler.getClass());
    }

    @Test
    void returnOptionsHandlerTwo_whenMethodOptions2Route() {
        mockRequestLine = new RequestLine("OPTIONS", "/method_options2", "HTTP/1.1");
//        requestLineStub.put("httpMethod", "OPTIONS");
//        requestLineStub.put("requestTarget", "/method_options2");

        Handler actualHandler = getActualHandler();

        assertEquals(OptionsHandlerTwo.class, actualHandler.getClass());
    }

    @Test
    void returnPostHandler_whenEchoBodyRoute() {
        mockRequestLine = new RequestLine("POST", "/echo_body", "HTTP/1.1");
//        requestLineStub.put("httpMethod", "POST");
//        requestLineStub.put("requestTarget", "/echo_body");

        Handler actualHandler = getActualHandler();

        assertEquals(EchoBodyHandler.class, actualHandler.getClass());
    }

    @Test
    void returnRedirectHandler_whenRedirectRoute() {
        mockRequestLine = new RequestLine("GET", "/redirect", "HTTP/1.1");
//        requestLineStub.put("httpMethod", "GET");
//        requestLineStub.put("requestTarget", "/redirect");

        Handler actualHandler = getActualHandler();

        assertEquals(RedirectHandler.class, actualHandler.getClass());
    }

    @Test
    void returnPageNotFoundHandler_whenResourceNotAvailable() {
        mockRequestLine = new RequestLine("GET", "/page_not_exist", "HTTP/1.1");
//        requestLineStub.put("httpMethod", "GET");
//        requestLineStub.put("requestTarget", "/page_not_exist");

        Handler actualHandler = getActualHandler();

        assertEquals(PageNotFoundHandler.class, actualHandler.getClass());
    }

    @Test
    void returnTextHandler_whenTextResponseRoute() {
        mockRequestLine = new RequestLine("GET", "/text_response", "HTTP/1.1");
//        requestLineStub.put("httpMethod", "GET");
//        requestLineStub.put("requestTarget", "/text_response");

        Handler actualHandler = getActualHandler();

        assertEquals(TextHandler.class, actualHandler.getClass());
    }

    private Handler getActualHandler() {
        Request requestMock = new Request(mockRequestLine, requestHeadersStub, requestBodyStub);
        Router router = new Router();

        return router.getHandler(requestMock);
    }
}
