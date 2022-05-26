package org.httpserver.server;

import org.httpserver.client.ClientHandler;
import org.httpserver.request.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int portNumber;

    public Server(int portNumber){
        this.portNumber = portNumber;
    }
    public void start() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        ServerWrapper serverWrapper = new ServerWrapper(serverLogger);
        ServerSocket serverSocket = serverWrapper.createServerSocket(portNumber);

        while(!serverSocket.isClosed()) {

            Socket clientSocket = serverWrapper.createClientSocket(serverSocket);
            ClientHandler clientHandler = new ClientHandler(clientSocket, serverLogger);

            String clientRequest = clientHandler.processRequest();

            RequestHandler requestHandler = new RequestHandler(clientRequest);
            requestHandler.parseClientRequest();

            String response = requestHandler.responseBuilder();

            clientHandler.processSendResponse(response);

        }
    }


}
