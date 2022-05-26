package org.httpserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWrapper {
    private final ServerLogger serverLogger;
    private int clientConnectionCounter;

    public ServerWrapper(ServerLogger serverLogger){
        this.serverLogger = serverLogger;
    }

    public ServerSocket createServerSocket(int portNumber) throws IOException {
        ServerSocket serverSocket = null;
        try{
            serverSocket = new ServerSocket(portNumber);
            serverLogger.listeningForClientRequest(portNumber);
        } catch (IOException ioException){
            ioException.printStackTrace();
            serverLogger.failedToCreateServerSocket(portNumber);
            System.exit(1);
        }
        return serverSocket;
    }

    public Socket createClientSocket(ServerSocket serverSocket)throws IOException {
        Socket clientSocket = null;
        try{
            clientSocket = serverSocket.accept();
            serverLogger.successfulConnection(clientSocket.getPort());
            clientConnectionCounter++;
            serverLogger.numberOfClientsConnected(clientConnectionCounter);
        } catch(IOException ioException){
            ioException.printStackTrace();
            serverLogger.failedConnection();
        }
        return clientSocket;
    }
}
