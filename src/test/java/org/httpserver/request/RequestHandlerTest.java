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
    void requestLineParsedSuccessfullyReturnMap() throws IOException {
        BufferedReader mockClientRequestReader = mock(BufferedReader.class);
        String mockRequestLine = "GET /simple_get HTTP/1.1";

        when(mockClientRequestReader.readLine()).thenReturn(mockRequestLine);

        RequestHandler requestHandler = new RequestHandler();
        LinkedHashMap actualMap = requestHandler.parseClientRequestLine(mockClientRequestReader);

        assertEquals("GET",  actualMap.get("httpMethod"));
        assertEquals("/simple_get",  actualMap.get("requestTarget"));
        assertEquals("HTTP/1.1",  actualMap.get("httpVersion"));
    }

//    @Test
//    void processClientRequestSuccessfully() throws IOException {
//        BufferedReader mockClientRequestReader = mock(BufferedReader.class);
//        String RequestLineStub = "GET /simple_get_with_body HTTP/1.1";
//
//        LinkedHashMap <String, String> requestLineMapStub = new LinkedHashMap<>(){{
//            put("httpMethod", "GET");
//            put("requestTarget", "/simple_get_with_body");
//            put("httpVersion", "HTTP/1.1");
//        }};
//        String requestBodyStub = "";
//
//        when(mockClientRequestReader.readLine()).thenReturn(RequestLineStub);
//        RequestHandler requestHandler = new RequestHandler();
//
//
//        String expectedResponse = """
//                HTTP/1.1 200 OK\r
//
//                Hello world""";
//
//        String actualResponse = requestHandler.responseBuilder(requestLineMapStub,requestBodyStub);
//
//        assertEquals(expectedResponse, actualResponse);
//    }
}
