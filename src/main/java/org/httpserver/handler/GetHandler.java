package org.httpserver.handler;

public class GetHandler implements Handler {

    @Override
    public String[] allowedMethods() {
        return new String[]{"GET"};
    }
}
