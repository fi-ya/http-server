package org.httpserver.server;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ServerWrapperTest {

    @Test
    void serverSocketOpensConnectionOnPort8080() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        ServerWrapper serverWrapper = new ServerWrapper();
        ServerSocket newServerSocket =  serverWrapper.createServerSocket(8080, serverLogger);
        assertNotNull(newServerSocket);
    }

    @Test
    void serverSocketFailsToConnectToInvalidPort() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        ServerWrapper serverWrapper = new ServerWrapper();

        assertThrows(IllegalArgumentException.class, () -> serverWrapper.createServerSocket(-1, serverLogger));
    }

    @Test
    void clientSocketConnectsToServerSocket() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        ServerWrapper serverWrapper = new ServerWrapper();

        ServerSocket mockServerSocket = mock(ServerSocket.class);
        Socket mockClientSocket = mock(Socket.class);

        when(mockServerSocket.accept()).thenReturn(mockClientSocket);
        serverWrapper.createClientSocket(mockServerSocket, serverLogger);

        verify(mockServerSocket, times(1)).accept();
    }

    @Test
    void clientSocketFailedToConnectToServerSocket() throws IOException {
        StdOutServerLogger mockServerLogger = mock(StdOutServerLogger.class);
        ServerWrapper serverWrapper = new ServerWrapper();
        ServerSocket mockServerSocket = mock(ServerSocket.class);

        when(mockServerSocket.accept()).thenThrow(IOException.class);
        serverWrapper.createClientSocket(mockServerSocket, mockServerLogger);

        verify(mockServerLogger, times(1)).failedConnection();
    }
}
