package org.httpserver.handler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TextHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        TextHandler textHandler = new TextHandler();

        assertTrue(textHandler.allowedHttpMethods().contains("GET"));
        assertEquals(1, textHandler.allowedHttpMethods().size());
    }


}
