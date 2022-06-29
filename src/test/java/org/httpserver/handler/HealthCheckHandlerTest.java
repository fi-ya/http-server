package org.httpserver.handler;

import org.junit.jupiter.api.Test;

import static org.httpserver.server.HttpMethod.GET;
import static org.junit.jupiter.api.Assertions.*;

class HealthCheckHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        HealthCheckHandler healthCheckHandler = new HealthCheckHandler();

        assertTrue(healthCheckHandler.allowedHttpMethods().contains(GET));
        assertEquals(1, healthCheckHandler.allowedHttpMethods().size());
    }

}
