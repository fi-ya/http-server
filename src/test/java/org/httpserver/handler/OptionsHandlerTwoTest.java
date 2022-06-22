package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionsHandlerTwoTest {

    @Test
    void returnsGetHeadOptionsPostPutMethodsOnly() {
        OptionsHandlerTwo optionsHandlerTwo = new OptionsHandlerTwo();

        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains(HttpMethod.GET.getHttpMethod()));
        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains(HttpMethod.HEAD.getHttpMethod()));
        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains(HttpMethod.OPTIONS.getHttpMethod()));
        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains(HttpMethod.POST.getHttpMethod()));
        assertTrue(optionsHandlerTwo.allowedHttpMethods().contains(HttpMethod.PUT.getHttpMethod()));
        assertEquals(5, optionsHandlerTwo.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineHeadersAndEmptyBody_whenMethodOptionsTwo() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put(Constant.HTTP_VERSION, Constant.HTTP_VERSION_NUMBER);
            put(Constant.HTTP_METHOD, HttpMethod.OPTIONS.getHttpMethod());
            put(Constant.REQUEST_TARGET, "/method_options2");
        }};
        OptionsHandlerTwo optionsHandlerTwo = new OptionsHandlerTwo();

        Response actualResponse = optionsHandlerTwo.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), Constant.EMPTY_STRING));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertEquals("Allow: GET, HEAD, OPTIONS, PUT, POST\r\n\r\n", actualResponse.getResponseHeaders());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }
}
