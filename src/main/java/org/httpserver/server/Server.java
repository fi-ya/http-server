package org.httpserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private final ServerLogger serverLogger;
    private final int portNumber;
    ServerWrapper serverWrapper;

    public Server(ServerLogger serverLogger, int portNumber, ServerWrapper serverWrapper){
        this.serverLogger = serverLogger;
        this.portNumber = portNumber;
        this.serverWrapper = serverWrapper;
    }
    public void start() throws IOException {

        ServerSocket serverSocket = serverWrapper.createServerSocket(portNumber, serverLogger);

        while(!serverSocket.isClosed()){
            Socket clientSocket = serverWrapper.createClientSocket(serverSocket, serverLogger);
            serverWrapper.handleClientSocket(clientSocket, serverLogger);
//            ExecutorService executorService = Executors.newSingleThreadExecutor();
//            executorService.execute(serverWrapper);

        }
    }


}
