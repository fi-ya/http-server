package org.httpserver.server;

public interface ServerLogger {
    void listeningForClientRequest(int portNumber);

    void printFailedToCreateServerSocket(int portNumber);

    void printConnectedClientSocket(int clientPortNumber);

    void printNumberOfClientsConnected(int clientConnectionCounter);

    void printFailedClientSocketConnection();

    void printReadingClientRequest();

    void printSendingClientResponse();

    void printClosedClientConnection(int clientPortNumber);

}
