package br.com.gnexum.utils;

import java.io.IOException;
import java.io.OutputStream;

import com.sun.net.httpserver.HttpExchange;

public class HttpResponse {

  // Private constructor to prevent instantiation
  private HttpResponse() {
    throw new UnsupportedOperationException("Utility class");
  }

  public static void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
    exchange.sendResponseHeaders(statusCode, response.getBytes().length);
    OutputStream os = exchange.getResponseBody();
    os.write(response.getBytes());
    os.close();
  }
}
