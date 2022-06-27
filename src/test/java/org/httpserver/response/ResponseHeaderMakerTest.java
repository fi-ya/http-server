package org.httpserver.response;

import org.httpserver.server.HttpMethod;
import org.junit.jupiter.api.Test;

import static org.httpserver.server.HttpMethod.*;
import static org.junit.jupiter.api.Assertions.*;

class ResponseHeaderMakerTest {

    @Test
    void returnsPlainTextHeaderStringArray(){

        String[] expectedHeader = new String[] {"Content-Type", "text/plain;charset=utf-8"};

        String [] actualHeader = ResponseHeaderMaker.plainTextHeader();

        assertEquals(expectedHeader[0], actualHeader[0]);
        assertEquals(expectedHeader[1], actualHeader[1]);
    }

    @Test
    void returnsContentLengthHeaderStringArray(){

        String[] expectedHeader = new String[] {"Content-Length", "11"};

        String [] actualHeader = ResponseHeaderMaker.contentLengthHeader("Hello world");

        assertEquals(expectedHeader[0], actualHeader[0]);
        assertEquals(expectedHeader[1], actualHeader[1]);
    }

    @Test
    void returnsAllowHeaderStringArray_withGetHeadOptionsOnly(){

        String[] expectedHeader = new String[] {"Allow", "GET, HEAD, OPTIONS"};

        String [] actualHeader = ResponseHeaderMaker.allowHeader(GET, HEAD, OPTIONS);

        assertEquals(expectedHeader[0], actualHeader[0]);
        assertEquals(expectedHeader[1], actualHeader[1]);
    }

    @Test
    void returnsAllowHeaderStringArray_withGetHeadOptionsPostPut(){

        String[] expectedHeader = new String[] {"Allow", "GET, HEAD, OPTIONS, POST, PUT"};

        String [] actualHeader = ResponseHeaderMaker.allowHeader(GET, HEAD, OPTIONS, POST, PUT);

        assertEquals(expectedHeader[0], actualHeader[0]);
        assertEquals(expectedHeader[1], actualHeader[1]);
    }
}
