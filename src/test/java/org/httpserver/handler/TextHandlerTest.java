package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;

class TextHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        TextHandler textHandler = new TextHandler();

        assertTrue(textHandler.allowedHttpMethods().contains(HttpMethod.GET.getHttpMethod()));
        assertEquals(1, textHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponse_withStatusLineAndBody_withText() {
        LinkedHashMap<String, String> requestLineStub = new LinkedHashMap<>() {{
            put(Constant.HTTP_VERSION, Constant.HTTP_VERSION_NUMBER);
            put(Constant.HTTP_METHOD, HttpMethod.GET.getHttpMethod());
            put(Constant.REQUEST_TARGET, "/text_response");
        }};
        TextHandler textHandler = new TextHandler();

        Response actualResponse = textHandler.handleResponse(new Request(requestLineStub, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().contains(Constant.CONTENT_TYPE_HEADER));
        assertTrue(actualResponse.getResponseHeaders().contains(Constant.TEXT_TYPE));
        assertEquals("text response", actualResponse.getResponseBody());
    }

}
