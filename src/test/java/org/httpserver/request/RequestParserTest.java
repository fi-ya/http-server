package org.httpserver.request;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestParserTest {
    @Test
    void processSimpleGetWithBodyRequestSuccessfully() throws IOException {
        BufferedReader mockClientRequestReader = mock(BufferedReader.class);
        String requestLineStub = "GET /simple_get_with_body HTTP/1.1";
        LinkedHashMap<String, String> requestLineMapStub = new LinkedHashMap<>() {{
            put("httpMethod", "GET");
            put("requestTarget", "/simple_get_with_body");
            put("httpVersion", "HTTP/1.1");
        }};

        LinkedHashMap<String, String> requestHeadersMapStub = new LinkedHashMap<>();
        String requestBodyStub = "";

        when(mockClientRequestReader.readLine()).thenReturn(requestLineStub);
        RequestParser requestParser = new RequestParser();
        Request mockRequest = new Request(requestLineMapStub, requestHeadersMapStub, requestBodyStub);

        String expectedResponse = "HTTP/1.1 200 OK\r\n\r\nHello world";
        String actualResponse = requestParser.responseBuilder(mockRequest);

        assertEquals(expectedResponse, actualResponse);
    }
}
