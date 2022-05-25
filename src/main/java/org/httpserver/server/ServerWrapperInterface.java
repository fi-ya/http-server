package org.httpserver.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public interface ServerWrapperInterface {

    ServerSocket createServerSocket(int portNumber, ServerLogger serverLogger) throws IOException;

    Socket createClientSocket(ServerSocket serverSocket, ServerLogger serverLogger)throws IOException;

    boolean handleClientSocketStatus(boolean status);

    BufferedReader createClientReader(Socket clientSocket) throws IOException;

    PrintWriter createClientWriter(Socket clientSocket) throws IOException;
}
