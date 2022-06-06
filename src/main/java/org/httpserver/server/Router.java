package org.httpserver.server;

import org.httpserver.handler.Handler;
import org.httpserver.handler.HeadRequestHandler;
import org.httpserver.handler.MethodNotAllowedHandler;
import org.httpserver.handler.SimpleGetHandler;
import org.httpserver.request.Request;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Router {

    private final Map<String, Handler> resourceAndHandler;

    public Router() {
        this.resourceAndHandler = createResourceAndHandler();
    }

    public Map<String, Handler> createResourceAndHandler() {
        return new HashMap<>() {{
            put("/simple_get", new SimpleGetHandler());
            put("/head_request", new HeadRequestHandler());
//                put("/simple_get_with_body", "new SimpleGetHandler()");
//                put("/redirect", "new RedirectHandler()");
//                put("/echo_body", "new PostHandler()");
//                put("/method_options", "new OptionsHandler1()");
//                put("/method_options2", "new OptionsHandler2()");
        }};
    }

    public Handler getHandler(Request request) {
        if (resourceTargetExists(request) && isMethodAllowed(request)) {
            return resourceAndHandler.get(request.getRequestTarget());
        }
        return new MethodNotAllowedHandler();
    }

    private boolean resourceTargetExists(Request request) {
        return resourceAndHandler.containsKey(request.getRequestTarget());
    }

    private boolean isMethodAllowed(Request request) {
        Handler handler = resourceAndHandler.get(request.getRequestTarget());
        List<String> getAllowedMethods = handler.allowedHttpMethods();

        return getAllowedMethods.contains(request.getHttpMethod());
    }


}
