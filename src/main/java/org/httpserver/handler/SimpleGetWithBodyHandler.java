package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.response.BodyConstants.simple_get_body;
import static org.httpserver.response.ResponseHeaderMaker.contentLengthHeader;
import static org.httpserver.response.ResponseHeaderMaker.plainTextHeader;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.server.HttpMethod.GET;
import static org.httpserver.server.HttpMethod.HEAD;

public class SimpleGetWithBodyHandler implements Handler {

    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET, HEAD);
    }

    public Response handleResponse(Request request) {

        ResponseBuilder responseBuilder = new ResponseBuilder()
                .withStatusCode(OK)
                .withHeader(plainTextHeader())
                .withHeader(contentLengthHeader(simple_get_body));

        return (request.getHttpMethod() == HEAD) ? responseBuilder.build() : responseBuilder.withBody(simple_get_body).build();
    }
}
