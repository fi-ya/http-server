package org.httpserver.server;

public interface ServerLogger {
    void successfulConnection(int clientPortNumber);
    void failedConnection();
    void listeningForClientRequest(int portNumber);
    void failedToCreateServerSocket(int portNumber);
    void listeningForClientInput();
    void closedClientConnection(int clientPortNumber);
    void numberOfClientsConnected(int clientConnectionCounter);
}
