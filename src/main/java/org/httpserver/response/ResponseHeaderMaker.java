package org.httpserver.response;

import org.httpserver.server.HttpMethod;
import org.httpserver.server.Server;

public class ResponseHeaderMaker {

    public static final String localIPAddress = "127.0.0.1";

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

    public static String[] locationHeader(String newLocationUrl) {
        return new String[]{ResponseHeaderName.LOCATION, newLocationAddress(newLocationUrl)};
    }

    public static String[] htmlTextHeader() {
        return new String[]{ResponseHeaderName.CONTENT_TYPE, ContentType.HTML.getValue()};
    }

    private static String newLocationAddress(String url) {
        return "http://" + localIPAddress + ":" + Server.portNumber + url;
    }

    public static String[] imageHeader(String type) {
        return switch (type) {
            case "jpg", "jpeg" -> imageJpgHeader();
            case "png" -> imagePngHeader();
            case "gif" -> imageGifHeader();
            default -> new String[0];
        };
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
}
