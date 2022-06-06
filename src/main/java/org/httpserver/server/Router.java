package org.httpserver.server;

import org.httpserver.handler.SimpleGetHandler;
import org.httpserver.handler.Handler;
import org.httpserver.request.Request;
import org.httpserver.response.Response;

import java.util.HashMap;
import java.util.Map;

public class Router {

    private final Map<String, Handler> resourceAndHandler;

    public Router() {
        this.resourceAndHandler = createResourceAndHandler();
    }

    public Map<String, Handler> createResourceAndHandler() {
        return new HashMap<>() {{
            put("/simple_get", new SimpleGetHandler());
//                put("/simple_get_with_body", "new SimpleGetHandler()");
//                put("/head_request", "new HeadRequestHandler()");
//                put("/redirect", "new RedirectHandler()");
//                put("/echo_body", "new PostHandler()");
//                put("/method_options", "new OptionsHandler1()");
//                put("/method_options2", "new OptionsHandler2()");
        }};
    }

    public Handler getHandler(Request request) {
        System.out.println("get" + request.getRequestTarget());
        if (resourceTargetExists(request)) {
            System.out.println("geHandler : ");
            return resourceAndHandler.get(request.getRequestTarget());
        }
        return null;
    }

    private boolean resourceTargetExists(Request request){
        return resourceAndHandler.containsKey(request.getRequestTarget());
    }
}
