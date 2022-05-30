package org.httpserver.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServerWrapperTest {

    @Test
    void serverSocketOpensConnectionOnPort5000() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        ServerWrapper serverWrapper = new ServerWrapper(serverLogger);
        ServerSocket newServerSocket = serverWrapper.createServerSocket(5000);
        assertNotNull(newServerSocket);
    }

    @Test
    void serverSocketFailsToConnectToInvalidPort() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        ServerWrapper serverWrapper = new ServerWrapper(serverLogger);

        assertThrows(IllegalArgumentException.class, () -> serverWrapper.createServerSocket(-1));
    }

    @Test
    void clientSocketConnectsToServerSocket() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        ServerWrapper serverWrapper = new ServerWrapper(serverLogger);

        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);

        when(mockServerSocket.accept()).thenReturn(mockClientSocket);
        serverWrapper.createClientSocket(mockServerSocket);

        verify(mockServerSocket, times(1)).accept();
    }

    @Test
    void clientSocketFailedToConnectToClientSocket() throws IOException {
        StdOutServerLogger mockServerLogger = mock(StdOutServerLogger.class);
        ServerWrapper serverWrapper = new ServerWrapper(mockServerLogger);
        ServerSocket mockServerSocket = mock(ServerSocket.class);

        when(mockServerSocket.accept()).thenThrow(IOException.class);
        serverWrapper.createClientSocket(mockServerSocket);

        verify(mockServerLogger, times(1)).printFailedClientSocketConnection();
    }
}
