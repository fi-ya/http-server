package org.httpserver.server;

import org.httpserver.handler.*;
import org.httpserver.request.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {

    private final Map<String, Handler> resourceAndHandlerMap;

    public Router() {
        this.resourceAndHandlerMap = createResourceAndHandlerMap();
    }

    public Map<String, Handler> createResourceAndHandlerMap() {
        return new HashMap<>() {{
            put("/simple_get", new SimpleGetHandler());
            put("/simple_get_with_body", new SimpleGetWithBodyHandler());
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
        List<String> getAllowedMethods = handler.allowedHttpMethods();

        return getAllowedMethods.contains(request.getHttpMethod());
    }


}
