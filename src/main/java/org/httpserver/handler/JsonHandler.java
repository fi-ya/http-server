
package org.httpserver.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.httpserver.request.Request;
import org.httpserver.response.Response;
import org.httpserver.response.ResponseBuilder;
import org.httpserver.server.HttpMethod;

import java.util.HashMap;
import java.util.List;

import static org.httpserver.response.ResponseHeaderMaker.contentLengthHeader;
import static org.httpserver.response.ResponseHeaderMaker.jsonHeader;
import static org.httpserver.response.StatusCode.OK;
import static org.httpserver.server.HttpMethod.GET;

public class JsonHandler implements Handler {
    @Override
    public List<HttpMethod> allowedHttpMethods() {
        return List.of(GET);
    }

    @Override
    public Response handleResponse(Request request) throws JsonProcessingException {
        HashMap jsonMap = new HashMap() {{
            put("key1", "value1");
            put("key2", "value2");
        }};
        System.out.println("hash" + jsonMap);
        String body = convertMapToJsonString(jsonMap);
        System.out.println("bidy" + body);
        byte[] bodyByte = convertMapToJsonString(jsonMap).getBytes();

        return new ResponseBuilder()
                .withStatusCode(OK)
                .withHeader(jsonHeader())
                .withHeader(contentLengthHeader(body))
                .withBodyByte(bodyByte)
                .build();
    }

    private String convertMapToJsonString(HashMap jsonMap) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(jsonMap);
    }
}
