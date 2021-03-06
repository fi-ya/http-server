package org.httpserver.server;

public enum HttpMethod {
    GET("GET"),
    POST("POST"),
    HEAD("HEAD"),
    OPTIONS("OPTIONS"),
    PUT("PUT");

    private final String httpMethod;

    HttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }
    public static HttpMethod findHttpMethod(String httpMethod) {
        return HttpMethod.valueOf(httpMethod);
    }
}
