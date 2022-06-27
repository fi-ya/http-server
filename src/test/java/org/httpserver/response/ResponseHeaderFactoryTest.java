package org.httpserver.response;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ResponseHeaderFactoryTest {

    @Test
    void returnsContentLengthValue(){
        ResponseHeaderFactory responseHeaderFactory = new ResponseHeaderFactory();

        assertEquals("11", responseHeaderFactory.contentLengthHeaderValue("Hello world"));
    }
}
