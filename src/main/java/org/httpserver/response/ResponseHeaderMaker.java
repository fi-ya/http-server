package org.httpserver.response;

import org.httpserver.server.HttpMethod;

import java.util.Objects;

public class ResponseHeaderMaker {
    public static String[] plainTextHeader() {
        return new String[]{ResponseHeaderName.CONTENT_TYPE, ContentType.TEXT.getValue()};
    }

    public static String[] contentLengthHeader(String body) {
        return new String[]{ResponseHeaderName.CONTENT_LENGTH, String.valueOf(body.length())};
    }

    public static String[] allowHeader(HttpMethod... args) {
        StringBuilder allowedHttpMethods = new StringBuilder();

        for (HttpMethod arg : args) {
            allowedHttpMethods.append(arg).append(", ");
        }
        String finalAllowedHeaders = allowedHttpMethods.substring(0, allowedHttpMethods.length() - 2);

        return new String[]{ResponseHeaderName.ALLOW, String.valueOf(finalAllowedHeaders)};
    }

    public static String[] locationHeader(String locationURL) {
        return new String[]{ResponseHeaderName.LOCATION, locationURL};
    }

    public static String[] htmlTextHeader() {
        return new String[]{ResponseHeaderName.CONTENT_TYPE, ContentType.HTML.getValue()};
    }

    public static String[] imageHeader(String type) {
        if (Objects.equals(type, "jpg") || Objects.equals(type, "jpeg")) {
            return imageJpgHeader();
        }
        if (Objects.equals(type, "png")) {
            return imagePngHeader();
        }
        if (Objects.equals(type, "gif")) {
            return imageGifHeader();
        }
        return new String[0];
    }

    public static String[] imageJpgHeader() {
        return new String[]{ResponseHeaderName.CONTENT_TYPE, ContentType.JPEG.getValue()};
    }

    public static String[] imagePngHeader() {
        return new String[]{ResponseHeaderName.CONTENT_TYPE, ContentType.PNG.getValue()};
    }

    public static String[] imageGifHeader() {
        return new String[]{ResponseHeaderName.CONTENT_TYPE, ContentType.GIF.getValue()};
    }

    public static String[] jsonHeader() {
        return new String[]{ResponseHeaderName.CONTENT_TYPE, ContentType.JSON.getValue()};
    }

    public static String[] xmlHeader(){
        return new String[] {ResponseHeaderName.CONTENT_TYPE, ContentType.XML.getValue()};
    }
}
