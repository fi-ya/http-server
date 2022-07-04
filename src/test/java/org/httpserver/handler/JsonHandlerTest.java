package org.httpserver.handler;

import org.junit.jupiter.api.Test;

import static org.httpserver.server.HttpMethod.GET;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class JsonHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        JsonHandler jsonHandler = new JsonHandler();

        assertTrue(jsonHandler.allowedHttpMethods().contains(GET));
        assertEquals(1, jsonHandler.allowedHttpMethods().size());
    }
}
