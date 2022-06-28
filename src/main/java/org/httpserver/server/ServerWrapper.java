package org.httpserver.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerWrapper {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ServerSocket createServerSocket(int portNumber) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
            logger.info("Server socket connection created. Listening on port " + portNumber);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            logger.error("Server connection failed, cannot listen for client request on port " + portNumber);
            System.exit(1);
        }
        return serverSocket;
    }

    public Socket createClientSocket(ServerSocket serverSocket) throws IOException {
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
            logger.info("Client connection successful: new client socket port number is " + clientSocket.getPort());
        } catch (IOException ioException) {
            ioException.printStackTrace();
            logger.error("Connection unsuccessful: Server failed to accept client connection");
        }
        return clientSocket;
    }
}
