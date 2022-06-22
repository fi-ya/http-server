package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PageNotFoundHandlerTest {

    @Test
    void returnsGetMethodOnly(){
        PageNotFoundHandler pageNotFoundHandler = new PageNotFoundHandler();

        assertTrue(pageNotFoundHandler.allowedHttpMethods().contains(HttpMethod.GET.getHttpMethod()));
        assertEquals(1, pageNotFoundHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineOnly(){
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put(Constant.HTTP_VERSION, Constant.HTTP_VERSION_NUMBER);
            put(Constant.HTTP_METHOD, HttpMethod.GET.getHttpMethod());
            put(Constant.REQUEST_TARGET, "/page_not_exist");
        }};
        PageNotFoundHandler pageNotFoundHandler = new PageNotFoundHandler();

        Response actualResponse = pageNotFoundHandler.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), Constant.EMPTY_STRING));

        assertEquals("HTTP/1.1 404 NOT FOUND\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }


}
