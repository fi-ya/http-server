package org.httpserver.handler;

import org.httpserver.request.Request;
import org.httpserver.request.RequestLine;
import org.httpserver.response.Response;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;

import static org.httpserver.server.HttpMethod.GET;
import static org.junit.jupiter.api.Assertions.*;

class XmlHandlerTest {

    @Test
    void returnsGetMethodOnly() {
        XmlHandler xmlHandler = new XmlHandler();

        assertTrue(xmlHandler.allowedHttpMethods().contains(GET));
        assertEquals(1, xmlHandler.allowedHttpMethods().size());
    }

    @Test
    void returnsResponse_withStatusLine_withHeaders_AndBody_withXml() {
        RequestLine mockRequestLine = new RequestLine(GET, "/xml_response", "HTTP/1.1");
        XmlHandler xmlHandler = new XmlHandler();

        Response actualResponse = xmlHandler.handleResponse(new Request(mockRequestLine, new LinkedHashMap<>(), ""));

        assertEquals("HTTP/1.1 200 OK\r\n", actualResponse.getResponseStatusLine());
        assertTrue(actualResponse.getResponseHeaders().contains("Content-Type"));
        assertTrue(actualResponse.getResponseHeaders().contains("application/xml;charset=utf-8"));
        assertEquals("<note><body>XML Response</body></note>", new String(actualResponse.getBodyBytes()));
    }

}
