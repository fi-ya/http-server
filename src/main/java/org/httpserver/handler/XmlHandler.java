package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.Arrays;
import java.util.List;

import static org.httpserver.response.ResponseHeaderMaker.*;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.server.HttpMethod.GET;

public class XmlHandler implements Handler{
    @Override
    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET);
    }

    @Override
    public Response handleResponse(Request request) {
        String body = "<note><body>XML Response</body></note>";
        byte[] bodyByte = body.getBytes();

        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeader(xmlHeader())
                .withHeader(contentLengthHeader(body))
                .withBodyByte(bodyByte)
                .build();
    }
}
