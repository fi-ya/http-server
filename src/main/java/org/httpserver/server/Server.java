package org.httpserver.server;

import org.httpserver.client.ClientHandler;
import org.httpserver.request.RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedHashMap;

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

            RequestHandler requestHandler = new RequestHandler(clientRequestReader);
            String response = requestHandler.processClientRequest();
            clientHandler.processSendResponse(response);
            clientHandler.closeClientConnection();
        }
    }


}
