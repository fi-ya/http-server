package org.httpserver;

import org.httpserver.server.Server;

import java.io.IOException;


public class App {

//    public static int portNumber = Integer.parseInt(System.getenv("PORT"));
    public static void main(String[] args) throws IOException {
        var server = new Server();
        server.start(Integer.parseInt(System.getenv("PORT")));
    }


}
