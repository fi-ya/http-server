package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.response.ResponseHeaderMaker.contentLengthHeader;
import static org.httpserver.response.ResponseHeaderMaker.htmlTextHeader;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.response.TextConstants.htmlResponse;
import static org.httpserver.server.HttpMethod.GET;

public class HtmlHandler implements Handler {

    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET);
    }

    @Override
    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeader(htmlTextHeader())
                .withHeader(contentLengthHeader(htmlResponse))
                .withBody(htmlResponse)
                .build();
    }
}
