package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.httpserver.response.ResponseHeaderMaker.*;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.server.HttpMethod.GET;

public class ImagesHandler implements Handler{
    @Override
    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET);
    }

    @Override
    public Response handleResponse(Request request) throws IOException {
        String type = request.getRequestTarget().split("\\.")[1];
        String imageResource = request.getRequestTarget();
        String body = getResponseBody(imageResource);

        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeader(imageHeader(type))
                .withHeader(contentLengthHeader(body))
                .withBody(body)
                .build();
    }

    private String getResponseBody(String imageResource) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(imageResource);
        return Arrays.toString(inputStream.readAllBytes());
    }
}
