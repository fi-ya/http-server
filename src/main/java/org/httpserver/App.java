package org.httpserver;

import org.httpserver.server.Server;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {
        var server = new Server(5000);
        server.start();
        // test branch
    }
}
