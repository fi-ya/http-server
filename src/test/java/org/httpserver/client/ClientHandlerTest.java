package org.httpserver.client;

import org.httpserver.response.Response;
import org.httpserver.server.StdOutServerLogger;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import static org.mockito.Mockito.*;

class ClientHandlerTest {

    @Test
    void sentClientResponseReadCorrectlyAndClosedClientSuccessfully() throws IOException {
        StdOutServerLogger serverLogger = new StdOutServerLogger();
        Socket mockClientSocket = mock(Socket.class);
        ClientHandler clientHandler = new ClientHandler(mockClientSocket, serverLogger);
        PrintWriter mockClientResponseWriter = mock(PrintWriter.class);
        String mockResponseString = "HTTP/1.1 200 OK";
        Response mockResponse = mock(Response.class);

        when(mockResponse.stringFormatResponse()).thenReturn(mockResponseString);

        clientHandler.sendResponse(mockResponse, mockClientResponseWriter);

        verify(mockClientResponseWriter).write("HTTP/1.1 200 OK");
        verify(mockClientResponseWriter, times(1)).close();
    }
}
