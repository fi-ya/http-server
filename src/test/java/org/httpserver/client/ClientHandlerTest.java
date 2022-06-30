package org.httpserver.client;

import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ClientHandlerTest {

    @Test
    void sentClientResponseReadCorrectlyAndConvertedToBytesSuccessfully() throws IOException {
        Response mockResponse = new Response("HTTP/1.1 200 OK", "", "");
        Socket mockClientSocket = mock(Socket.class);
        ClientHandler clientHandler = new ClientHandler(mockClientSocket);

        byte[] actualByte = clientHandler.responseStringToBytes(mockResponse);

        byte[] expectedByte = {72, 84, 84, 80, 47, 49, 46, 49, 32, 50, 48, 48, 32, 79, 75};
        assertEquals(Arrays.toString(expectedByte), Arrays.toString(expectedByte));
        assertEquals(expectedByte.length, actualByte.length);
    }
}
