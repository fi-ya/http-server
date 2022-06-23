package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
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

        assertTrue(pageNotFoundHandler.allowedHttpMethods().contains("GET"));
        assertEquals(1, pageNotFoundHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithStatusLineOnly(){
//        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
//            put("httpVersion", "HTTP/1.1");
//            put("httpMethod", "GET");
//            put("requestTarget", "/page_not_exist");
//        }};
        RequestLine mockRequestLine = new RequestLine("GET", "/page_not_exist", "HTTP/1.1");
        PageNotFoundHandler pageNotFoundHandler = new PageNotFoundHandler();

        Response actualResponse = pageNotFoundHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 404 Not Found\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertTrue(actualResponse.getResponseBody().isEmpty());
    }


}
