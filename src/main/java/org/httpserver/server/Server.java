package org.httpserver.server;

import org.httpserver.client.ClientHandler;
import org.httpserver.request.Request;
import org.httpserver.request.RequestParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private final int portNumber;

    public Server(int portNumber) {
        this.portNumber = portNumber;
    }

    public void start() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();

        ServerWrapper serverWrapper = new ServerWrapper(serverLogger);
        ServerSocket serverSocket = serverWrapper.createServerSocket(portNumber);

        while (!serverSocket.isClosed()) {

            Socket clientSocket = serverWrapper.createClientSocket(serverSocket);

            ClientHandler clientHandler = new ClientHandler(clientSocket, serverLogger);
            BufferedReader clientRequestReader = clientHandler.createClientInputStreamReader();

            RequestParser requestParser = new RequestParser();
            Request request = requestParser.parseRequest(clientRequestReader);
            String response = requestParser.responseBuilder(request);
//            String response = requestParser.processClientRequest(clientRequestReader);
            clientHandler.processSendResponse(response);
            clientHandler.closeClientConnection();
        }
    }


}
