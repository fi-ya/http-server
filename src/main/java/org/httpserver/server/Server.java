package org.httpserver.server;

import org.httpserver.client.ClientHandler;
import org.httpserver.handler.Handler;
import org.httpserver.request.Request;
import org.httpserver.request.RequestParser;
import org.httpserver.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {
    private final Logger logger = LoggerFactory.getLogger("org.httpserver.server.Server");

    private final Logger loggerReqRes = LoggerFactory.getLogger("loggerReqRes");

    public void start(int portNumber) throws IOException {
        ServerWrapper serverWrapper = new ServerWrapper();
        ServerSocket serverSocket = serverWrapper.createServerSocket(portNumber);

        while (!serverSocket.isClosed()) {

            Socket clientSocket = serverWrapper.createClientSocket(serverSocket);

            ClientHandler clientHandler = new ClientHandler(clientSocket);
            InputStream clientRequestInputStream = clientHandler.clientRequestInputStream();
            clientHandler.updateClientConnectionLogger();

            RequestParser requestParser = new RequestParser();

            Request request = requestParser.parseRequest(clientRequestInputStream);
            loggerReqRes.info("Request: " + request.stringFormatRequest());

            Router router = new Router();
            Handler handler = router.getHandler(request);

            logger.info(handler.getClass().getSimpleName() + ": building a response");

            Response response = handler.handleResponse(request);
            loggerReqRes.info("Response: " + response.stringFormatResponse());

            clientHandler.processSendResponse(response);

            clientHandler.closeClientConnection();
        }
    }
}
