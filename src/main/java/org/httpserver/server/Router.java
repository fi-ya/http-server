package org.httpserver.server;

import java.util.LinkedHashMap;

public class Router {

    private final LinkedHashMap<String, String> resourceAndHandler;

    public Router(LinkedHashMap<String, String> resourceAndHandler) {
        this.resourceAndHandler = resourceAndHandler;
    }


}
