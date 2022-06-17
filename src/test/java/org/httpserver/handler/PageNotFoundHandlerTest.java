package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PageNotFoundHandlerTest {

    @Test
    void returnsGetMethodOnly(){
        PageNotFoundHandler pageNotFoundHandler = new PageNotFoundHandler();

        assertTrue(pageNotFoundHandler.allowedHttpMethods().contains("GET"));
        assertEquals(1, pageNotFoundHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineOnly(){
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put("httpVersion", "HTTP/1.1");
            put("httpMethod", "GET");
            put("requestTarget", "/page_not_exist");
        }};
        Request requestMock = new Request(requestLineStub, new LinkedHashMap<>(), "");
        PageNotFoundHandler pageNotFoundHandler = new PageNotFoundHandler();

        Response actualResponse = pageNotFoundHandler.handleResponse(requestMock);

        assertEquals("HTTP/1.1 404 NOT FOUND\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }


}
