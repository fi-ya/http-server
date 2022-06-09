package org.httpserver.handler;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HeadRequestHandlerTest {

    @Test
    void allowedHttpMethods_ReturnsHeadOnly() {

        HeadRequestHandler headRequestHandler = new HeadRequestHandler();

        List<String> actualAllowedMethods = headRequestHandler.allowedHttpMethods();

        assertEquals("[HEAD]", actualAllowedMethods.toString());
    }

    @Test
    void handleResponse() {

    }
}
