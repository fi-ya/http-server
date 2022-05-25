package org.httpserver.client;

import org.httpserver.server.StdOutServerLogger;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ClientHandlerTest {

    @Test
    void incomingClientRequestReadCorrectly() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        Socket mockClientSocket = mock(Socket.class);
        ClientHandler clientHandler = new ClientHandler(mockClientSocket, serverLogger);

        BufferedReader mockClientRequestReader = mock(BufferedReader.class);
        when(mockClientRequestReader.readLine()).thenReturn("GET /simple_get HTTP/1.1");
        clientHandler.getClientRequest(mockClientRequestReader);

        assertEquals("GET /simple_get HTTP/1.1", mockClientRequestReader.readLine());
//        assertEquals("GET /simple_get HTTP/1.1", clientHandler.getClientRequest());
    }

}
