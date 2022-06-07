package org.httpserver.server;

import org.httpserver.handler.Handler;

public interface ServerLogger {
    void listeningForClientRequest(int portNumber);

    void printFailedToCreateServerSocket(int portNumber);

    void printConnectedClientSocket(int clientPortNumber);

    void printNumberOfClientsConnected(int clientConnectionCounter);

    void printFailedClientSocketConnection();

    void printReadingClientRequest();

    void printHandlerBuildingResponse(Handler handler);

    void printSendingClientResponse();

    void printResponse(String response);

    void printClosedClientConnection(int clientPortNumber);

}
