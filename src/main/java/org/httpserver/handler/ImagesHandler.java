package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import static org.httpserver.response.ResponseHeaderMaker.contentLengthHeader;
import static org.httpserver.response.ResponseHeaderMaker.imageHeader;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.server.HttpMethod.GET;

public class ImagesHandler implements Handler {
    @Override
    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET);
    }

    @Override
    public Response handleResponse(Request request) throws IOException {
        String type = request.getRequestTarget().split("\\.")[1];
        String imageResource = request.getRequestTarget();

        byte[] bodyByte = getResponseBody(imageResource);
        String body = Arrays.toString(bodyByte);

        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeader(imageHeader(type))
                .withHeader(contentLengthHeader(body))
                .withBodyByte(bodyByte)
                .build();
    }

    private byte[] getResponseBody(String imageResource) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(imageResource);
        return inputStream.readAllBytes();
    }
}
