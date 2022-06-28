package org.httpserver.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class ServerWrapperTest {

    @Test
    void serverSocketOpensConnectionOnPort5000() throws IOException {
        ServerWrapper serverWrapper = new ServerWrapper();

        ServerSocket newServerSocket = serverWrapper.createServerSocket(5000);

        assertNotNull(newServerSocket);
    }

    @Test
    void serverSocketFailsToConnectToInvalidPort() throws IOException {
        ServerWrapper serverWrapper = new ServerWrapper();

        assertThrows(IllegalArgumentException.class, () -> serverWrapper.createServerSocket(-1));
    }

    @Test
    void clientSocketConnectsToServerSocket() throws IOException {
        ServerWrapper serverWrapper = new ServerWrapper();
        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);
        when(mockServerSocket.accept()).thenReturn(mockClientSocket);

        serverWrapper.createClientSocket(mockServerSocket);

        verify(mockServerSocket, times(1)).accept();
    }

}
