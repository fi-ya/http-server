package org.httpserver.handler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class RedirectHandlerTest {

    @Test
    void returnsGetOnly() {
        RedirectHandler redirectHandler = new RedirectHandler();

        assertTrue(redirectHandler.allowedHttpMethods().contains("GET"));
        assertEquals(1, redirectHandler.allowedHttpMethods().size());
    }
}
