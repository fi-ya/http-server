package org.httpserver;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void main() {
        String result = "Hello World!";
        assertEquals(Main.helloWorld(), result);
    }
}
