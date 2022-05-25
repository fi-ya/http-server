package org.httpserver.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWrapper {
    private int clientConnectionCounter;
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private ServerLogger serverLogger;

    public void handleClientSocket(Socket clientSocket, ServerLogger serverLogger) {
        this.clientSocket = clientSocket;
        this.serverLogger = serverLogger;
    }

    public ServerSocket createServerSocket(int portNumber, ServerLogger serverLogger) throws IOException {
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

    public Socket createClientSocket(ServerSocket serverSocket, ServerLogger serverLogger)throws IOException {
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
        // ExecutorService executorService = Executors.newSingleThreadExecutor();
//                executorService.shutdownNow();
        return clientSocket;
    }



}
