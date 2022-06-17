package org.httpserver.handler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageNotFoundHandlerTest {

    @Test
    void returnsGetMethodOnly(){
        PageNotFoundHandler pageNotFoundHandler = new PageNotFoundHandler();

        assertTrue(pageNotFoundHandler.allowedHttpMethods().contains("GET"));
        assertEquals(1, pageNotFoundHandler.allowedHttpMethods().size());
    }


}
