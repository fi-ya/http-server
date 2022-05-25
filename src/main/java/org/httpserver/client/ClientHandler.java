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
    private BufferedReader clientRequestReader;


    public ClientHandler(Socket clientSocket, ServerLogger serverLogger){
        this.clientSocket = clientSocket;
        this.serverLogger = serverLogger;
    }

    public void createClientSocketInputOutputStream(Socket clientSocket, ServerLogger serverLogger) throws IOException{
        try ( BufferedReader clientRequestReader = createClientRequestReader(clientSocket);
//            var clientResponseWriter = createClientResponseWriter(clientSocket);
        ) {
            serverLogger.listeningForClientInput();
            getClientRequest(clientRequestReader);

        } catch(IOException ioException){
            ioException.printStackTrace();
            System.out.println("[-] Error client input & output stream not created");
        }
    }
    public String getClientRequest(BufferedReader clientRequestReader) throws IOException {

        String clientRequestLine = null;

        try {
            clientRequestLine = clientRequestReader.readLine();
            System.out.println("Client Request: " + clientRequestLine);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            System.out.println("[-] Error client input stream not created");
        }
        return clientRequestLine;
    }

//    public String getClientRequest() throws IOException {
//        serverLogger.listeningForClientInput();
//        String clientRequestLine = null;
//
//        try (var clientInput = createClientReader(clientSocket);) {
//            clientRequestLine = clientInput.readLine();
//            System.out.println("Client Request: " + clientRequestLine);
//        } catch (IOException ioException) {
//            ioException.printStackTrace();
//            System.out.println("[-] Error client input stream not created");
//        }
//        return clientRequestLine;
//    }


    private BufferedReader createClientRequestReader(Socket clientSocket) throws IOException {
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

//    public void sendServerResponse() throws IOException{
//        try (var clientOutput = createClientWriter(clientSocket);) {
//
//        } catch(IOException ioException){
//            ioException.printStackTrace();
//            System.out.println("[-] Error client output stream not created");
//        }
//    }
//    private PrintWriter createClientResponseWriter(Socket clientSocket) throws IOException{
//        return new PrintWriter(clientSocket.getOutputStream(), true);
//    }
}
