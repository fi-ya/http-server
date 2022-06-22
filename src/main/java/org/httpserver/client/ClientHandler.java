package org.httpserver.client;

import org.httpserver.response.Response;
import org.httpserver.server.ServerLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler {

    private final Socket clientSocket;
    private final ServerLogger serverLogger;

    public int clientConnectionCounter;
    PrintWriter clientResponseWriter;

    public ClientHandler(Socket clientSocket, ServerLogger serverLogger) {
        this.clientSocket = clientSocket;
        this.serverLogger = serverLogger;
    }

    public InputStream clientRequestInputStream() throws IOException {
        return clientSocket.getInputStream();
    }

    public void updateClientConnectionLogger() {
        clientConnectionCounter++;
        serverLogger.printNumberOfClientsConnected(clientConnectionCounter);
        serverLogger.printReadingClientRequest();
    }


    public void processSendResponse(Response response) throws IOException {
        clientResponseWriter = createClientResponseWriter();
        serverLogger.printSendingClientResponse();
        sendResponse(response, clientResponseWriter);
    }

    private PrintWriter createClientResponseWriter() throws IOException {
        return new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void sendResponse(Response response, PrintWriter clientResponseWriter) {
        clientResponseWriter.write(response.stringFormatResponse());
        serverLogger.printResponse(response.stringFormatResponse());
        clientResponseWriter.close();
    }

    public void closeClientConnection() throws IOException {
        clientSocket.close();
        serverLogger.printClosedClientConnection(clientSocket.getPort());
        clientConnectionCounter--;
        serverLogger.printNumberOfClientsConnected(clientConnectionCounter);
    }
}
