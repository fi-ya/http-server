package org.httpserver.server;

import org.httpserver.client.ClientHandler;
import org.httpserver.request.RequestHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.Buffer;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int portNumber;

    public Server(int portNumber){
        this.portNumber = portNumber;
    }
    public void start() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        ServerWrapper serverWrapper = new ServerWrapper(serverLogger);
        ServerSocket serverSocket = serverWrapper.createServerSocket(portNumber);

        Socket clientSocket = serverWrapper.createClientSocket(serverSocket);
        ClientHandler clientHandler = new ClientHandler(clientSocket, serverLogger);

        BufferedReader clientRequestReader = clientHandler.createClientSocketInputStream();
        String clientRequest = clientHandler.getClientRequest(clientRequestReader);
        // parse getClientInput
        RequestHandler requestHandler = new RequestHandler(clientRequest);
        // -> newResquestParser - in req class
        requestHandler.parseClientRequest();
        // -> newResponseBuilder - in res class
        String response = requestHandler.responseBuilder();
        // -> send to PrintWriter
        clientHandler.sendResponse(response);
        // -> clientSocket.close
    }


}
