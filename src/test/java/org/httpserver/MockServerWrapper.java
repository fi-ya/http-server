package org.httpserver;

import org.httpserver.server.ServerLogger;
import org.httpserver.server.ServerWrapperInterface;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MockServerWrapper implements ServerWrapperInterface {
    boolean isCreateServerSocketCalled = false;
    boolean isClientSocketStatusCalled = false;
    boolean isHandleClientSocketStatusCalled = false;
    boolean isCreateClientReaderCalled = false;
    boolean isCreateClientWriterCalled = false;
    int portNumber = 8080;

    @Override
    public ServerSocket createServerSocket(int portNumber, ServerLogger serverLogger) throws IOException{
        isCreateServerSocketCalled = true;
    };
    @Override

    public Socket createClientSocket(ServerSocket serverSocket, ServerLogger serverLogger)throws IOException{
        isClientSocketStatusCalled = true;
    };
    @Override

    public boolean handleClientSocketStatus(boolean status){
        isHandleClientSocketStatusCalled = true;
    }
    @Override
    public BufferedReader createClientReader(Socket clientSocket) throws IOException{
        isCreateClientReaderCalled = false;
    }
    @Override
    public PrintWriter createClientWriter(Socket clientSocket) throws IOException{
        isCreateClientWriterCalled = false;
    }
}
