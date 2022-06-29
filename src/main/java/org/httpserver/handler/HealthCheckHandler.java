package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.response.ResponseHeaderMaker;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.response.ResponseHeaderMaker.*;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.response.TextConstants.textResponse;
import static org.httpserver.server.HttpMethod.GET;

public class HealthCheckHandler implements Handler{
    @Override
    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET);
    }

    @Override
    public Response handleResponse(Request request) {
        // get .html file = read public dir
        //String get html body = read html file
        // pass html body into ResponseBuilder

        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeader(htmlTextHeader())
//                .withHeader(contentLengthHeader(htmlResponse))
//                .withBody(htmlResponse)
                .build();
    }
}
