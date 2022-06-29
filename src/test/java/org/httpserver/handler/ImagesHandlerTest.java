package org.httpserver.handler;

import org.junit.jupiter.api.Test;

import static org.httpserver.server.HttpMethod.GET;
import static org.junit.jupiter.api.Assertions.*;

class ImagesHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        ImagesHandler imagesHandler = new ImagesHandler();

        assertTrue(imagesHandler.allowedHttpMethods().contains(GET));
        assertEquals(1, imagesHandler.allowedHttpMethods().size());
    }

}
