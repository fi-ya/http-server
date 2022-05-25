package org.httpserver;

import org.httpserver.server.Server;
import org.httpserver.server.ServerWrapper;
import org.httpserver.server.StdOutServerLogger;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        ServerWrapper serverWrapper = new ServerWrapper();
        var server = new Server(serverLogger, 8080, serverWrapper);
        server.start();
    }


}
