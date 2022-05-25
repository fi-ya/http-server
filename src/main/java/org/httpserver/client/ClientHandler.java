package org.httpserver.client;

import org.httpserver.server.ServerLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler {

    private final Socket clientSocket;

    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    public void createClientSocketInputOutputStream(Socket clientSocket, ServerLogger serverLogger) throws IOException{
        try (var clientInput = createClientReader(clientSocket);
             var clientOutput = createClientWriter(clientSocket);
        ) {
            serverLogger.listeningForClientInput();
            String clientRequest = getClientRequest(clientInput);
            System.out.println("req:"+ clientRequest);

        } catch(IOException ioException){
            ioException.printStackTrace();
            System.out.println("Input & Output stream not created");
        }
//        handleClientSocketStatus(true);
    }

    private BufferedReader createClientReader(Socket clientSocket) throws IOException {
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    private PrintWriter createClientWriter(Socket clientSocket) throws IOException{
        return new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public String getClientRequest(BufferedReader clientInput) throws IOException {
        String clientRequestLine = clientInput.readLine();
        System.out.println("Client Request: "+ clientRequestLine);
        return clientRequestLine;
    }

//        public String getClientRequest(BufferedReader clientInput) throws IOException {
//        StringBuilder clientRequestLine = new StringBuilder(clientInput.readLine());
//        System.out.println("Client Request: "+ clientRequestLine);
//        return clientRequestLine.toString();
//
//    }

}
