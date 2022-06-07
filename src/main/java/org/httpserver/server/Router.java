package org.httpserver.server;

import org.httpserver.handler.Handler;
import org.httpserver.handler.HeadRequestHandler;
import org.httpserver.handler.MethodNotAllowedHandler;
import org.httpserver.handler.SimpleGetHandler;
import org.httpserver.request.Request;

import java.util.HashMap;
import java.util.Map;

public class Router {

    private final Map<String, Handler> resourceAndHandlerMap;

    public Router() {
        this.resourceAndHandlerMap = createResourceAndHandlerMap();
    }

    public Map<String, Handler> createResourceAndHandlerMap() {
        return new HashMap<>() {{
            put("/simple_get", new SimpleGetHandler());
            put("/simple_get_with_body", new SimpleGetHandler());
            put("/head_request", new HeadRequestHandler());
        }};
    }

    public Handler getHandler(Request request) {
        if (resourceTargetExists(request) && isHttpMethodAllowed(request)) {
            return resourceAndHandlerMap.get(request.getRequestTarget());
        }
        return new MethodNotAllowedHandler();
    }

    private boolean resourceTargetExists(Request request) {
        return resourceAndHandlerMap.containsKey(request.getRequestTarget());
    }

    private boolean isHttpMethodAllowed(Request request) {
        Handler handler = resourceAndHandlerMap.get(request.getRequestTarget());
        return handler.allowedHttpMethods().contains(request.getHttpMethod());
    }


}
