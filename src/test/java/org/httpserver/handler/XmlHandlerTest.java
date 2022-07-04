package org.httpserver.handler;

import org.junit.jupiter.api.Test;

import static org.httpserver.server.HttpMethod.GET;
import static org.junit.jupiter.api.Assertions.*;

class XmlHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        XmlHandler xmlHandler = new XmlHandler();

        assertTrue(xmlHandler.allowedHttpMethods().contains(GET));
        assertEquals(1, xmlHandler.allowedHttpMethods().size());
    }

}
