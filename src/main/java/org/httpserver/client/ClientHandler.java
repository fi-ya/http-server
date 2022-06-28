package org.httpserver.client;

import org.httpserver.response.Response;
import org.httpserver.server.Server;
import org.httpserver.server.ServerLogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientHandler.class);
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
        LOGGER.info("[+] Number of clients connected: " + clientConnectionCounter);
        LOGGER.info("[+] Reading client request");
//        serverLogger.printNumberOfClientsConnected(clientConnectionCounter);
//        serverLogger.printReadingClientRequest();
    }


    public void processSendResponse(Response response) throws IOException {
        clientResponseWriter = createClientResponseWriter();
        LOGGER.info("[+] Sending client response");
//        serverLogger.printSendingClientResponse();
        sendResponse(response, clientResponseWriter);
    }

    private PrintWriter createClientResponseWriter() throws IOException {
        return new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void sendResponse(Response response, PrintWriter clientResponseWriter) {
        clientResponseWriter.write(response.stringFormatResponse());
        LOGGER.info("[+] Response start: \n" + response.stringFormatResponse() + "[+] Response end");
//        serverLogger.printResponse(response.stringFormatResponse());
        clientResponseWriter.close();
    }

    public void closeClientConnection() throws IOException {
        clientSocket.close();
        LOGGER.info("[+] Client request reader, response writer & socket closed on port number: " + clientSocket.getPort());
//        serverLogger.printClosedClientConnection(clientSocket.getPort());
        clientConnectionCounter--;
        LOGGER.info("[+] Number of clients connected: " + clientConnectionCounter);
//        serverLogger.printNumberOfClientsConnected(clientConnectionCounter);
    }
}
