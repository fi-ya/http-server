package org.httpserver.server;

import org.httpserver.client.ClientHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final int portNumber;
//    ServerWrapper serverWrapper;
//    private final ServerLogger serverLogger;

    public Server(int portNumber){
        this.portNumber = portNumber;
//      this.serverWrapper = serverWrapper;
//      this.serverLogger = serverLogger;
    }
    public void start() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        ServerWrapper serverWrapper = new ServerWrapper();

        ServerSocket serverSocket = serverWrapper.createServerSocket(portNumber, serverLogger);

        while(!serverSocket.isClosed()){
            Socket clientSocket = serverWrapper.createClientSocket(serverSocket, serverLogger);
            serverWrapper.handleClientSocket(clientSocket, serverLogger);
            ClientHandler clientHandler = new ClientHandler(clientSocket);
//            ExecutorService executorService = Executors.newSingleThreadExecutor();
//            executorService.execute(clientHandler);
            clientHandler.createClientSocketInputOutputStream(serverLogger);
            // parse getClientInput
            // -> newResquestParser - in req class
            // -> newResponseBuilder - in res class
            // -> send to PrintWriter
            // -> clientSocket.close

        }
    }


}
