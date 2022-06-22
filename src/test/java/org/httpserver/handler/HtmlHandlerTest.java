package org.httpserver.handler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HtmlHandlerTest {

    @Test
    void returnsGetMethodOnly(){
        HtmlHandler htmlHandler = new HtmlHandler();

        assertTrue(htmlHandler.allowedHttpMethods().contains("GET"));
        assertEquals(1, htmlHandler.allowedHttpMethods().size());
    }

}
