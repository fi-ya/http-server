package org.httpserver.client;

import org.httpserver.handler.Handler;
import org.httpserver.request.Request;
import org.httpserver.request.RequestParser;
import org.httpserver.response.Response;
import org.httpserver.server.Router;
import org.httpserver.server.ServerLogger;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

import static org.httpserver.server.ServerWrapper.handleClientSocketStatus;

public class ClientHandler implements Runnable{

    private final Socket clientSocket;
    private final ServerLogger serverLogger;


    public int clientConnectionCounter;
    //    BufferedReader clientRequestReader;
    PrintWriter clientResponseWriter;

    public ClientHandler(Socket clientSocket, ServerLogger serverLogger) {
        this.clientSocket = clientSocket;
        this.serverLogger = serverLogger;
    }
    @Override
    public void run() {

        try {
            InputStream clientRequestInputStream = clientRequestInputStream();
            updateClientConnectionLogger();

            RequestParser requestParser = new RequestParser();
            Request request = requestParser.parseRequest(clientRequestInputStream);

            Router router = new Router();
            Handler handler = router.getHandler(request);
            serverLogger.printHandlerBuildingResponse(handler);

            Response response = handler.handleResponse(request);
            processSendResponse(response);
//            closeClientConnection();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public InputStream clientRequestInputStream() throws IOException {
        handleClientSocketStatus(true);
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
//        clientSocket.close();
//        serverLogger.printClosedClientConnection(clientSocket.getPort());
//        clientConnectionCounter--;
        serverLogger.printNumberOfClientsConnected(clientConnectionCounter);
    }


}
