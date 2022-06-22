package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SimpleGetWithBodyHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        SimpleGetWithBodyHandler simpleGetWithBodyHandler = new SimpleGetWithBodyHandler();

        assertTrue(simpleGetWithBodyHandler.allowedHttpMethods().contains(HttpMethod.GET.getHttpMethod()));
        assertEquals(1, simpleGetWithBodyHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponseWithResponseStatusLineAndBody() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put(Constant.HTTP_VERSION, Constant.HTTP_VERSION_NUMBER);
            put(Constant.HTTP_METHOD, HttpMethod.GET.getHttpMethod());
            put(Constant.REQUEST_TARGET, "/simple_get_with_body");
        }};
        SimpleGetWithBodyHandler simpleGetWithBodyHandler = new SimpleGetWithBodyHandler();

        Response actualResponse = simpleGetWithBodyHandler.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), Constant.EMPTY_STRING));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().isBlank());
        assertEquals("Hello world", actualResponse.getResponseBody());
    }

}
