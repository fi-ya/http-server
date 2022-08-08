package org.httpserver.client;

import org.httpserver.response.Response;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientHandler {
//    private final Logger logger = LoggerFactory.getLogger("org.httpserver.client.ClientHandler");
    private final Socket clientSocket;
    public int clientConnectionCounter;

    public ClientHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    public InputStream clientRequestInputStream() throws IOException {
        return clientSocket.getInputStream();
    }

    public void updateClientConnectionLogger() {
        clientConnectionCounter++;
//        logger.info("Number of clients connected: " + clientConnectionCounter);
//        logger.info("Reading client request");
    }

    public void processSendResponse(Response response) throws IOException {
//        logger.info("Sending client response");
        sendResponse(response.byteFormatResponse());
    }

    private void sendResponse(byte[] responseByte){
        try (OutputStream clientOutputStream = clientSocket.getOutputStream();) {
            clientOutputStream.write(responseByte);
            clientOutputStream.flush();
        } catch (IOException ioException) {
            ioException.printStackTrace();
//            logger.error("Failed to send response to client");
        }
    }

    public void closeClientConnection() throws IOException {
        clientSocket.close();
//        logger.info("Client request reader, response writer & socket closed on port number: " + clientSocket.getPort());
        clientConnectionCounter--;
//        logger.info("Number of clients connected: " + clientConnectionCounter);
    }
}
