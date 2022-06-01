package org.httpserver.response;

public class ResponseBuilder {

    String responseStatusLine;
    String responseHeaders ;
    String responseBody;


    public void setResponseStatusLine(String responseStatusLine) {
        this.responseStatusLine = responseStatusLine;
    }

    public void setResponseHeaders(String responseHeaders){
        this.responseHeaders = responseHeaders;
    }

    public void setResponseBody(String responseBody){
        this.responseBody = responseBody;
    }

    public Response buildResponse(){
        return new Response(responseStatusLine, responseHeaders, responseBody);
    }
}
