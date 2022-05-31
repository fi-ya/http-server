package org.httpserver.server;

import java.util.Date;

public class StdOutServerLogger implements ServerLogger {

    public void listeningForClientRequest(int portNumber) {
        System.out.println("[+] Server socket connection created. (" + new Date() + "). Listening on port " + portNumber);
    }

    ;

    public void printFailedToCreateServerSocket(int portNumber) {
        System.out.println("[-] Server connection failed, cannot listen for client request on port " + portNumber);
    }

    ;

    public void printConnectedClientSocket(int clientPortNumber) {
        System.out.println("[+] Client connection successful: new client socket port number is " + clientPortNumber);
    }

    public void printNumberOfClientsConnected(int clientConnectionCounter) {
        System.out.println("[+] Number of clients connected: " + clientConnectionCounter);
    }

    public void printFailedClientSocketConnection() {
        System.out.println("[-] Connection unsuccessful: Server failed to accept client connection");
    }

    public void printReadingClientRequest() {
        System.out.println("[+] Reading client request");
    }

    public void printSendingClientResponse() {
        System.out.println("[+] Sending client response");
    }

    ;

    public void printClosedClientConnection(int clientPortNumber) {
        System.out.println("[+] Client request reader, response writer & socket closed on port number: " + clientPortNumber);
    }

}
