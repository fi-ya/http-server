package org.httpserver.response;

import org.httpserver.request.Request;

public class Body {

    public static String echo_body(Request request){
        return request.getRequestBody();
    }

    public static String simple_get_body(){
        return "Hello world";
    }

    public static String text_response_body(){
        return "text response";
    }

    public static String html_response_body(){
        return "<html><body><p>HTML Response</p></body></html>";
    }
}
