package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Body;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.response.ResponseHeaderMaker.contentLengthHeader;
import static org.httpserver.response.ResponseHeaderMaker.plainTextHeader;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.server.HttpMethod.POST;

public class EchoBodyHandler implements Handler {

    public List<HttpMethod> allowedHttpMethods() {
        return List.of(POST);
    }

    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeader(plainTextHeader())
                .withHeader(contentLengthHeader(Body.echo_body(request)))
                .withBody(Body.echo_body(request))
                .build();
    }
}
