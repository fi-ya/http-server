package org.httpserver.client;

import org.httpserver.server.ServerLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler {

    private final Socket clientSocket;
    private final ServerLogger serverLogger;
//    BufferedReader clientRequestReader;
    PrintWriter clientResponseWriter;


    public ClientHandler(Socket clientSocket, ServerLogger serverLogger){
        this.clientSocket = clientSocket;
        this.serverLogger = serverLogger;
    }

    public BufferedReader createClientSocketInputStream() {
        BufferedReader clientRequestReader = null;
        try {
            clientRequestReader = createClientRequestReader();
            this.clientResponseWriter = createClientResponseWriter();
            System.out.println("input stream log; " + clientRequestReader);
            serverLogger.listeningForClientInput();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println("[-] Error client input stream not created");
        }
        return clientRequestReader;
    }

    private BufferedReader createClientRequestReader() throws IOException {
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public String getClientRequest(BufferedReader clientRequestReader) throws IOException {
        System.out.println("client req log; " + clientRequestReader);
        System.out.println("client req readline log; " + clientRequestReader.readLine());
        String clientRequestLine = clientRequestReader.readLine();
        System.out.println("Client Request: " + clientRequestLine);
        return clientRequestLine;
    }

    public void sendResponse(String response){
        clientResponseWriter.println(response);
    }

//    public void createClientOutputStream() throws IOException{
//        try {
//            var clientOutput = createClientResponseWriter(clientSocket);
//        } catch(IOException ioException){
//            ioException.printStackTrace();
//            System.out.println("[-] Error client output stream not created");
//        }
//    }
    private PrintWriter createClientResponseWriter() throws IOException{
        return new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public String processRequest() throws IOException {
        BufferedReader clientRequestReader = createClientSocketInputStream();
        String clientRequest = getClientRequest(clientRequestReader);
        return clientRequest;
    }
}
