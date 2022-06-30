package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.response.ResponseHeaderMaker;
import org.httpserver.server.HttpMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    public Response handleResponse(Request request) throws IOException {
        String htmlResource = request.getRequestTarget();
        String body = getResponseBody(htmlResource);

        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeader(htmlTextHeader())
                .withHeader(contentLengthHeader(body))
                .withBody(body)
                .build();
    }

    private String getResponseBody(String htmlResource) throws IOException {
        InputStream inputStream = getClass().getResourceAsStream(htmlResource);
        return convertHtmlFileToString(inputStream);
    }

    private String convertHtmlFileToString(InputStream inputStream) throws IOException {
        StringBuilder htmlStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                htmlStringBuilder.append(line).append("\n");
            }
        }
        return htmlStringBuilder.toString().trim();
    }


}
