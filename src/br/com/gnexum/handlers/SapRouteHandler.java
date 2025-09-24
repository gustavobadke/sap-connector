package br.com.gnexum.handlers;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import br.com.gnexum.utils.HttpResponse;

public class SapRouteHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "SAP Route is under construction.";
        HttpResponse.sendResponse(exchange, 200, response);
    }
}
