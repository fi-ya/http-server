package org.httpserver.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseHeaderMakerTest {

    @Test
    void returnsContentLengthValue(){
        assertEquals("11", ResponseHeaderMaker.contentLengthHeaderValue("Hello world"));
    }
}
