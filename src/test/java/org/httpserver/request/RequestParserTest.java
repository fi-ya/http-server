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
    void requestLineParsedSuccessfullyReturnMap() throws IOException {
        BufferedReader mockClientRequestReader = mock(BufferedReader.class);
        String mockRequestLine = "GET /simple_get HTTP/1.1";

        when(mockClientRequestReader.readLine()).thenReturn(mockRequestLine);

        RequestParser requestParser = new RequestParser();
        LinkedHashMap actualMap = requestParser.getRequestLine(mockClientRequestReader);

        assertEquals("GET", actualMap.get("httpMethod"));
        assertEquals("/simple_get", actualMap.get("requestTarget"));
        assertEquals("HTTP/1.1", actualMap.get("httpVersion"));
    }

}
