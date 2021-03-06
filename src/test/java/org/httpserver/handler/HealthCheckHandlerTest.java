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

class HealthCheckHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        HealthCheckHandler healthCheckHandler = new HealthCheckHandler();

        assertTrue(healthCheckHandler.allowedHttpMethods().contains(GET));
        assertEquals(1, healthCheckHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponse_withStatusLineAndBody_containingHtmlFile() throws IOException {
        RequestLine mockRequestLine = new RequestLine(GET, "/health-check.html", "HTTP/1.1");
        HealthCheckHandler healthCheckHandler = new HealthCheckHandler();

        Response actualResponse = healthCheckHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().contains("Content-Type"));
        assertTrue(actualResponse.getResponseHeaders().contains("text/html;charset=utf-8"));
        assertTrue(new String(actualResponse.getBodyBytes()).contains("<strong>Status:</strong> pass"));
    }
}
