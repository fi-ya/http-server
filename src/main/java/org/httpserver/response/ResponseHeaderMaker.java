package org.httpserver.response;

import java.sql.Array;

public class ResponseHeaderMaker {

    public static String[] plainTextHeader(){
        return new String[]{ResponseHeaderName.CONTENT_TYPE, ContentType.TEXT.getValue()};
    }
    public static String[] contentLengthHeader(String body) {
        return new String[]{ResponseHeaderName.CONTENT_LENGTH, String.valueOf(body.length())};
    }

}
