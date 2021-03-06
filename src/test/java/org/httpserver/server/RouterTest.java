package org.httpserver.server;

import org.httpserver.handler.*;
import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class RouterTest {

    private RequestLine mockRequestLine;

    private LinkedHashMap<String, String> requestHeadersStub;
    private String requestBodyStub;

    @BeforeEach
    void setup() {
        requestHeadersStub = new LinkedHashMap<>();
        requestBodyStub = "";
    }

    @Test
    void returnGetHandler_whenGetRoute() {
        mockRequestLine = new RequestLine(GET, "/simple_get", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(SimpleGetHandler.class, actualHandler.getClass());
    }

    @Test
    void returnGetHandler_whenSimpleGetWithBodyRoute() {
        mockRequestLine = new RequestLine(GET, "/simple_get_with_body", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(SimpleGetWithBodyHandler.class, actualHandler.getClass());
    }

    @Test
    void returnHeadHandler_whenHeadRequestRoute() {
        mockRequestLine = new RequestLine(HEAD, "/head_request", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(HeadRequestHandler.class, actualHandler.getClass());
    }

    @Test
    void returnMethodNotAllowedHandler_whenHttpMethodIncorrect() {
        mockRequestLine = new RequestLine(GET, "/head_request", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(MethodNotAllowedHandler.class, actualHandler.getClass());
    }

    @Test
    void returnOptionsHandler_whenMethodOptionsRoute() {
        mockRequestLine = new RequestLine(OPTIONS, "/method_options", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(OptionsHandler.class, actualHandler.getClass());
    }

    @Test
    void returnOptionsHandlerTwo_whenMethodOptions2Route() {
        mockRequestLine = new RequestLine(OPTIONS, "/method_options2", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(OptionsHandlerTwo.class, actualHandler.getClass());
    }

    @Test
    void returnPostHandler_whenEchoBodyRoute() {
        mockRequestLine = new RequestLine(POST, "/echo_body", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(EchoBodyHandler.class, actualHandler.getClass());
    }

    @Test
    void returnRedirectHandler_whenRedirectRoute() {
        mockRequestLine = new RequestLine(GET, "/redirect", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(RedirectHandler.class, actualHandler.getClass());
    }

    @Test
    void returnPageNotFoundHandler_whenResourceNotAvailable() {
        mockRequestLine = new RequestLine(GET, "/page_not_exist", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(PageNotFoundHandler.class, actualHandler.getClass());
    }

    @Test
    void returnTextHandler_whenTextResponseRoute() {
        mockRequestLine = new RequestLine(GET, "/text_response", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(TextHandler.class, actualHandler.getClass());
    }

    @Test
    void returnHtmlHandler_whenHtmlResponseRoute(){
        mockRequestLine = new RequestLine(GET, "/html_response", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(HtmlHandler.class, actualHandler.getClass());
    }

    @Test
    void returnHealthCheckHandler_whenHealthCheckHtmlRoute(){
        mockRequestLine = new RequestLine(GET, "/health-check.html", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(HealthCheckHandler.class, actualHandler.getClass());
    }

    @Test
    void returnImagesHandler_whenKittehJpgRoute(){
        mockRequestLine = new RequestLine(GET, "/kitteh.jpg", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(ImagesHandler.class, actualHandler.getClass());
    }

    @Test
    void returnImagesHandler_whenDoggoPngRoute(){
        mockRequestLine = new RequestLine(GET, "/doggo.png", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(ImagesHandler.class, actualHandler.getClass());
    }

    @Test
    void returnImagesHandler_whenKissesGifRoute(){
        mockRequestLine = new RequestLine(GET, "/kisses.gif", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(ImagesHandler.class, actualHandler.getClass());
    }

    @Test
    void returnJsonHandler_whenJsonResponseRoute(){
        mockRequestLine = new RequestLine(GET, "/json_response", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(JsonHandler.class, actualHandler.getClass());
    }

    @Test
    void returnXmlHandler_whenXmlResponseRoute(){
        mockRequestLine = new RequestLine(GET, "/xml_response", "HTTP/1.1");

        Handler actualHandler = getActualHandler();

        assertEquals(XmlHandler.class, actualHandler.getClass());
    }
    private Handler getActualHandler() {
        Request requestMock = new Request(mockRequestLine, requestHeadersStub, requestBodyStub);
        Router router = new Router();

        return router.getHandler(requestMock);
    }
}
