package org.httpserver.handler;

import org.httpserver.Constant;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.response.ResponseHeader;
import org.httpserver.response.StatusCode;
import org.httpserver.server.HttpMethod;

import java.util.List;

public class TextHandler implements Handler{
    public List<String> allowedHttpMethods() {
        return List.of(HttpMethod.GET.getHttpMethod());
    }

    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(StatusCode.OK)
                .withHeaderName(ResponseHeader.CONTENT_TYPE_HEADER.getResponseHeader())
                .withHeaderValue(ResponseHeader.TEXT_TYPE.getResponseHeader())
                .withBody("text response")
                .build();
    }
}
