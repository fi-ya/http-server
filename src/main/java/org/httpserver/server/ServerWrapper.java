package org.httpserver.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ServerWrapper implements ServerWrapperInterface, Runnable {
    private static boolean clientSocketStatus = false;
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

            if(clientSocketStatus){
                clientSocketStatus = false;
//                ExecutorService executorService = Executors.newSingleThreadExecutor();
//                executorService.shutdownNow();
            }
        } catch(IOException ioException){
            ioException.printStackTrace();
            serverLogger.failedConnection();
        }
        return clientSocket;
    }

    public boolean handleClientSocketStatus(boolean status){
        return clientSocketStatus = true;
    }

    public BufferedReader createClientReader(Socket clientSocket) throws IOException{
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public PrintWriter createClientWriter(Socket clientSocket) throws IOException{
        return new PrintWriter(clientSocket.getOutputStream(), true);
    }

    private void createClientSocketInputOutputStream(Socket clientSocket, ServerLogger serverLogger) throws IOException{
        try (var clientInput = createClientReader(clientSocket);
             var clientOutput = createClientWriter(clientSocket);
        ) {
            serverLogger.listeningForClientInput();
            clientOutput.println("Server is awaiting your request \n--------------------------------" );


        } catch(IOException ioException){
            ioException.printStackTrace();
            System.out.println("Input & Output stream not created");
        }
        handleClientSocketStatus(true);
    }

    @Override
    public void run() {
        try {
            createClientSocketInputOutputStream(clientSocket, serverLogger);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
