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

    public ClientHandler(Socket clientSocket, ServerLogger serverLogger){
        this.clientSocket = clientSocket;
        this.serverLogger = serverLogger;
    }

    public String processRequest() throws IOException {
        BufferedReader clientRequestReader = createClientSocketInputStream();
        return getClientRequest(clientRequestReader);
    }

    public BufferedReader createClientSocketInputStream() {
        BufferedReader clientRequestReader = null;
        try {
            clientRequestReader = createClientRequestReader();
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
        String clientRequestLine = clientRequestReader.readLine();
        System.out.println("Client Request: " + clientRequestLine);
        return clientRequestLine;
    }

    public void processSendResponse(String response) throws IOException {
        PrintWriter clientResponseWriter = createClientResponseWriter();
        sendResponse(response, clientResponseWriter);
    }

    private PrintWriter createClientResponseWriter() throws IOException{
        return new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void sendResponse(String response, PrintWriter clientResponseWriter) {
        clientResponseWriter.write(response);
        clientResponseWriter.close();
    }
}
