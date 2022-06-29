package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.GET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ImagesHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        ImagesHandler imagesHandler = new ImagesHandler();

        assertTrue(imagesHandler.allowedHttpMethods().contains(GET));
        assertEquals(1, imagesHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponse_withStatusLineAndBody_JpgFile() throws IOException {
        RequestLine mockRequestLine = new RequestLine(GET, "/kitteh.jpg", "HTTP/1.1");
        ImagesHandler imagesHandler = new ImagesHandler();

        Response actualResponse = imagesHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().contains("Content-Type"));
        assertTrue(actualResponse.getResponseHeaders().contains("image/jpeg"));
    }

    @Test
    void returnsResponse_withStatusLineAndBody_PngFile() throws IOException {
        RequestLine mockRequestLine = new RequestLine(GET, "/doggo.png", "HTTP/1.1");
        ImagesHandler imagesHandler = new ImagesHandler();

        Response actualResponse = imagesHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().contains("Content-Type"));
        assertTrue(actualResponse.getResponseHeaders().contains("image/png"));
    }

    @Test
    void returnsResponse_withStatusLineAndBody_GifFile() throws IOException {
        RequestLine mockRequestLine = new RequestLine(GET, "/kisses.gif", "HTTP/1.1");
        ImagesHandler imagesHandler = new ImagesHandler();

        Response actualResponse = imagesHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().contains("Content-Type"));
        assertTrue(actualResponse.getResponseHeaders().contains("image/gif"));
    }
}
