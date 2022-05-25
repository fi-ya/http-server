package org.httpserver.server;

import java.util.Date;

public class StdOutServerLogger implements ServerLogger {
    public void successfulConnection(int clientPortNumber) {
        System.out.println("[+] Connection successful: New client socket port number is " + clientPortNumber);
    }
    public void failedConnection() {
        System.out.println("[-] Connection unsuccessful: Server failed to accept client connection");
    }
    public void listeningForClientRequest(int portNumber){
        System.out.println("[+] Connection opened. (" + new Date() + "). Listening on port " + portNumber);
    };
    public void failedToCreateServerSocket(int portNumber){
        System.out.println( "[-] Server connection failed, cannot listen for client request on port "  + portNumber);
    };
    public void listeningForClientInput() {
        System.out.println("[+] Listening for client server input");
    }
    public void closedClientConnection(int clientPortNumber){ System.out.println("[+] Client socket closed on port number: " + clientPortNumber);}
    public void numberOfClientsConnected(int clientConnectionCounter){System.out.println("Number of clients connected: "+ clientConnectionCounter);}
}
