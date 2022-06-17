package org.httpserver.handler;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class OptionsHandlerTest {

    @Test
    void returnsGetHeadAndOptionsMethodsOnly() {
        OptionsHandler optionsHandler = new OptionsHandler();

        assertTrue(optionsHandler.allowedHttpMethods().contains("GET"));
        assertTrue(optionsHandler.allowedHttpMethods().contains("HEAD"));
        assertTrue(optionsHandler.allowedHttpMethods().contains("OPTIONS"));
    }

}
