package org.httpserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWrapper {
    private final ServerLogger serverLogger;

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
            serverLogger.printFailedToCreateServerSocket(portNumber);
            System.exit(1);
        }
        return serverSocket;
    }

    public Socket createClientSocket(ServerSocket serverSocket)throws IOException {
        Socket clientSocket = null;
        try{
            clientSocket = serverSocket.accept();
            serverLogger.printConnectedClientSocket(clientSocket.getPort());
        } catch(IOException ioException){
            ioException.printStackTrace();
            serverLogger.printFailedClientSocketConnection();
        }
        return clientSocket;
    }
}
