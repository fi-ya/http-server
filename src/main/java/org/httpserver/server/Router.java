package org.httpserver.server;

import org.httpserver.handler.GetHandler;
import org.httpserver.handler.Handler;
import org.httpserver.request.Request;

import java.util.HashMap;
import java.util.Map;

public class Router {

    private final Map<String, Handler> resourceAndHandler = createResourceAndHandler();

    public Map<String, Handler> createResourceAndHandler() {
        return new HashMap<>() {{
            put("/simple_get", new GetHandler());
//                put("/simple_get_with_body", "new GetHandler()");
//                put("/head_request", "new HeadRequestHandler()");
//                put("/redirect", "new RedirectHandler()");
//                put("/echo_body", "new PostHandler()");
//                put("/method_options", "new OptionsHandler1()");
//                put("/method_options2", "new OptionsHandler2()");
        }};
    }

    public Handler getHandler(Request request) {
        System.out.println("get" + request.getRequestTarget());
        Handler handler = null;
        if (resourceAndHandler.containsKey(request.getRequestTarget())) {
            handler = resourceAndHandler.get(request.getRequestTarget());
            System.out.println("handler" + handler);
        }
        return handler;
    }

}
