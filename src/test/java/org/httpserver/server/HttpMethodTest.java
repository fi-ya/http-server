package org.httpserver.server;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HttpMethodTest {

    @Test
    void returnsHttpMethod_whenGivenHttpMethodString(){
        assertEquals(HttpMethod.GET, HttpMethod.findHttpMethod("GET"));
        assertEquals(HttpMethod.POST, HttpMethod.findHttpMethod("POST"));
    }
}
