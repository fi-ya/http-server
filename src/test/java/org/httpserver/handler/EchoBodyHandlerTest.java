package org.httpserver.handler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EchoBodyHandlerTest {

    @Test
    void returnsPostOnly() {
        EchoBodyHandler echoBodyHandler = new EchoBodyHandler();

        assertTrue(echoBodyHandler.allowedHttpMethods().contains("POST"));
        assertEquals(1, echoBodyHandler.allowedHttpMethods().size());
    }

    @Test
    void handleResponse() {
    }
}
