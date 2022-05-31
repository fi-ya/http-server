package org.httpserver.request;

import org.httpserver.client.ClientHandler;
import org.httpserver.server.StdOutServerLogger;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedHashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RequestHandlerTest {

    @Test
    void requestLineParsedSuccessfully() throws IOException {
        BufferedReader mockClientRequestReader = mock(BufferedReader.class);
        String mockRequestLine = "GET /simple_get HTTP/1.1";

        when(mockClientRequestReader.readLine()).thenReturn(mockRequestLine);

        RequestHandler requestHandler = new RequestHandler();
        LinkedHashMap actualMap = requestHandler.parseClientRequestLine(mockRequestLine);

        assertEquals("GET",  actualMap.get("httpMethod"));
        assertEquals("/simple_get",  actualMap.get("requestTarget"));
        assertEquals("HTTP/1.1",  actualMap.get("httpVersion"));
    }

    @Test
    void processClientRequestSuccessfully() throws IOException {
        BufferedReader mockClientRequestReader = mock(BufferedReader.class);
        String mockRequestLine = "GET /simple_get_with_body HTTP/1.1";

        when(mockClientRequestReader.readLine()).thenReturn(mockRequestLine);

        RequestHandler requestHandler = new RequestHandler();
        String actualResponse = requestHandler.processClientRequest(mockClientRequestReader);
        String expectedResponse = "HTTP/1.1 200 OK\n" + "\n" + "Hello world";

        assertEquals(expectedResponse,  actualResponse);
    }
}
