package org.httpserver.response;

import org.httpserver.server.HttpMethod;

import java.sql.Array;
import java.util.StringJoiner;

public class ResponseHeaderMaker {

    public static String[] plainTextHeader(){
        return new String[]{ResponseHeaderName.CONTENT_TYPE, ContentType.TEXT.getValue()};
    }
    public static String[] contentLengthHeader(String body) {
        return new String[]{ResponseHeaderName.CONTENT_LENGTH, String.valueOf(body.length())};
    }

    public static String[] allowHeader(HttpMethod ... args){
        StringBuilder allowedHttpMethods = new StringBuilder();

        for (HttpMethod arg : args){
            allowedHttpMethods.append(arg).append(", ");
        }
       String finalAllowedHeaders = allowedHttpMethods.substring(0, allowedHttpMethods.length()-2);

        return new String[] {ResponseHeaderName.ALLOW, String.valueOf(finalAllowedHeaders)};
    }

    public static String[] locationHeader(String newLocation) {
        return new String[] {ResponseHeaderName.LOCATION, newLocation};
    }
}
