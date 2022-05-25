package org.httpserver.client;

import org.httpserver.server.ServerWrapper;
import org.httpserver.server.StdOutServerLogger;
import org.junit.jupiter.api.Test;
import org.mockito.internal.stubbing.answers.ReturnsElementsOf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientHandlerTest {

    @Test
    void incomingClientRequestReadCorrectly() throws IOException {
        StdOutServerLogger mockServerLogger = mock(StdOutServerLogger.class);
        Socket mockClientSocket = mock(Socket.class);
        ClientHandler clientHandler = new ClientHandler(mockClientSocket);

        BufferedReader mockClientInput = mock(BufferedReader.class);
        when(mockClientInput.readLine()).thenReturn("GET /simple_get HTTP/1.1");
        clientHandler.getClientRequest(mockClientInput);

        assertEquals("GET /simple_get HTTP/1.1", mockClientInput.readLine());
    }

}
