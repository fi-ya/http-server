package org.httpserver.client;

import org.httpserver.server.ServerLogger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler {

    private Socket clientSocket;

    public ClientHandler(Socket clientSocket){
        this.clientSocket = clientSocket;
    }
    public BufferedReader createClientReader() throws IOException {
        return new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public PrintWriter createClientWriter() throws IOException{
        return new PrintWriter(clientSocket.getOutputStream(), true);
    }

    public void createClientSocketInputOutputStream(ServerLogger serverLogger) throws IOException{
        try (var clientInput = createClientReader();
             var clientOutput = createClientWriter();
        ) {
            serverLogger.listeningForClientInput();
            clientOutput.println("Server is awaiting your request \n--------------------------------" );


        } catch(IOException ioException){
            ioException.printStackTrace();
            System.out.println("Input & Output stream not created");
        }
//        handleClientSocketStatus(true);
    }


}
