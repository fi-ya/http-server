package org.httpserver.server;

import org.httpserver.handler.*;
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
        requestLineStub = new LinkedHashMap<>() {{
            put("httpVersion", "HTTP/1.1");
        }};
        requestHeadersStub = new LinkedHashMap<>();
        requestBodyStub = "";
    }

    @Test
    void returnGetHandler_whenGetRoute() {
        requestLineStub.put("httpMethod", "GET");
        requestLineStub.put("requestTarget", "/simple_get");

        Handler actualHandler = getActualHandler();

        assertEquals(SimpleGetHandler.class, actualHandler.getClass());
    }

    @Test
    void returnGetHandler_whenSimpleGetWithBodyRoute() {
        requestLineStub.put("httpMethod", "GET");
        requestLineStub.put("requestTarget", "/simple_get_with_body");

        Handler actualHandler = getActualHandler();

        assertEquals(SimpleGetWithBodyHandler.class, actualHandler.getClass());
    }

    @Test
    void returnHeadHandler_whenHeadRequestRoute() {
        requestLineStub.put("httpMethod", "HEAD");
        requestLineStub.put("requestTarget", "/head_request");

        Handler actualHandler = getActualHandler();

        assertEquals(HeadRequestHandler.class, actualHandler.getClass());
    }

    @Test
    void returnMethodNotAllowedHandler_whenHttpMethodIncorrect() {
        requestLineStub.put("httpMethod", "GET");
        requestLineStub.put("requestTarget", "/head_request");

        Handler actualHandler = getActualHandler();

        assertEquals(MethodNotAllowedHandler.class, actualHandler.getClass());
    }

    @Test
    void returnOptionsHandler_whenMethodOptionsRoute() {
        requestLineStub.put("httpMethod", "OPTIONS");
        requestLineStub.put("requestTarget", "/method_options");

        Handler actualHandler = getActualHandler();

        assertEquals(OptionsHandler.class, actualHandler.getClass());
    }

    @Test
    void returnOptionsHandlerTwo_whenMethodOptions2Route() {
        requestLineStub.put("httpMethod", "OPTIONS");
        requestLineStub.put("requestTarget", "/method_options2");

        Handler actualHandler = getActualHandler();

        assertEquals(OptionsHandlerTwo.class, actualHandler.getClass());
    }

    @Test
    void returnPostHandler_whenEchoBodyRoute() {
        requestLineStub.put("httpMethod", "POST");
        requestLineStub.put("requestTarget", "/echo_body");

        Handler actualHandler = getActualHandler();

        assertEquals(EchoBodyHandler.class, actualHandler.getClass());
    }

    @Test
    void returnRedirectHandler_whenRedirectRoute() {
        requestLineStub.put("httpMethod", "GET");
        requestLineStub.put("requestTarget", "/redirect");

        Handler actualHandler = getActualHandler();

        assertEquals(RedirectHandler.class, actualHandler.getClass());
    }

    @Test
    void returnPageNotFoundHandler_whenResourceNotAvailable() {
        requestLineStub.put("httpMethod", "GET");
        requestLineStub.put("requestTarget", "/page_not_exist");

        Handler actualHandler = getActualHandler();

        assertEquals(PageNotFoundHandler.class, actualHandler.getClass());
    }

    private Handler getActualHandler() {
        Request requestMock = new Request(requestLineStub, requestHeadersStub, requestBodyStub);
        Router router = new Router();

        return router.getHandler(requestMock);
    }
}
