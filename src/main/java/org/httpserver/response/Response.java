package org.httpserver.response;

public class Response {

    String responseStatusLine;
    String responseHeaders;
    String responseBody;

    public Response(String responseStatusLine, String responseHeaders, String responseBody) {
        this.responseStatusLine = responseStatusLine;
        this.responseHeaders = responseHeaders;
        this.responseBody = responseBody;
    }

    public String getResponseStatusLine() {
        return responseStatusLine;
    }

    public String getResponseHeaders() {
        return responseHeaders;
    }

    public String getResponseBody() {
        return responseBody;
    }

    public String stringFormatResponse() {
        return getResponseStatusLine() + getResponseHeaders() + getResponseBody();
    }
}
