package org.httpserver;

import org.httpserver.server.Server;
import org.httpserver.server.ServerWrapper;
import org.httpserver.server.StdOutServerLogger;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        var server = new Server(8080);
        server.start();
    }


}
