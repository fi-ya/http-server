package org.httpserver.request;

import org.httpserver.server.HttpMethod;

public class RequestLine {

    private final HttpMethod httpMethod;
    private final String requestTarget;
    private final String httpVersion;

   public RequestLine(HttpMethod httpMethod, String requestTarget, String httpVersion){
        this.httpMethod = httpMethod;
        this.requestTarget = requestTarget;
        this.httpVersion = httpVersion;
    }

    public HttpMethod getHttpMethod(){
        return httpMethod;
    }

    public String getRequestTarget(){
        return requestTarget;
    }

     public String getHttpVersion(){
        return httpVersion;
     }

}
