package org.httpserver.server;

import org.httpserver.client.ClientHandler;
import org.httpserver.handler.Handler;
import org.httpserver.request.Request;
import org.httpserver.request.RequestParser;
import org.httpserver.response.Response;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
    private final int portNumber;
    private static final Logger LOGGER = LoggerFactory.getLogger(Server.class);

    public Server(int portNumber) {
        this.portNumber = portNumber;
    }

    public void start() throws IOException {
        LOGGER.info("This is an INFO level log message!");
        LOGGER.error("This is an ERROR level log message!");
        StdOutServerLogger serverLogger = new StdOutServerLogger();

        ServerWrapper serverWrapper = new ServerWrapper(serverLogger);
        ServerSocket serverSocket = serverWrapper.createServerSocket(portNumber);

        while (!serverSocket.isClosed()) {

            Socket clientSocket = serverWrapper.createClientSocket(serverSocket);

            ClientHandler clientHandler = new ClientHandler(clientSocket, serverLogger);
            InputStream clientRequestInputStream = clientHandler.clientRequestInputStream();
            clientHandler.updateClientConnectionLogger();

            RequestParser requestParser = new RequestParser();

            Request request = requestParser.parseRequest(clientRequestInputStream);

            Router router = new Router();
            Handler handler = router.getHandler(request);
            serverLogger.printHandlerBuildingResponse(handler);

            Response response = handler.handleResponse(request);

            clientHandler.processSendResponse(response);
            clientHandler.closeClientConnection();
        }
    }


}
