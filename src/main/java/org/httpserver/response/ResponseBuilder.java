package org.httpserver.response;

public class ResponseBuilder {
    public Response buildResponse(String responseStatusLine , String responseHeaders, String responseBody){
        return new Response(responseStatusLine, responseHeaders, responseBody);
    }

}
