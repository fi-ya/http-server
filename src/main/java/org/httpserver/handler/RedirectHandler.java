package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.response.ResponseHeaderMaker.locationHeader;
import static org.httpserver.response.StatusCode.MOVED_PERMANENTLY;
import static org.httpserver.server.HttpMethod.GET;

public class RedirectHandler implements Handler {
    @Override
    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET);
    }

    @Override
    public Response handleResponse(Request request) {

        final String REDIRECT_URL = System.getenv("REDIRECT_URL");

        return new ResponseBuilder()
                .withStatusCode(MOVED_PERMANENTLY)
                .withHeader(locationHeader(REDIRECT_URL))
                .build();
    }


}
