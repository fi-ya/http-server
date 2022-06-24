package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.response.ContentType;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.List;

import static org.httpserver.response.ResponseHeaderName.CONTENT_TYPE;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.server.HttpMethod.GET;

public class XmlHandler implements Handler {

    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET);
    }

    public Response handleResponse(Request request) {
        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeader(CONTENT_TYPE, ContentType.XML.getValue())
                .withBody("<note><body>XML Response</body></note>")
                .build();
    }
}
