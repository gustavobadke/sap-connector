package br.com.gnexum;

import com.sun.net.httpserver.HttpServer;

import br.com.gnexum.handlers.HealthcheckHandler;
import br.com.gnexum.handlers.SapRouteHandler;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.logging.Logger;

public class MainServer {
    private static final Logger logger = Logger.getLogger(MainServer.class.getName());
    public static void main(String[] args) throws IOException {
        // Cria um servidor HTTP na porta 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/", new SapRouteHandler());
        server.createContext("/healthcheck", new HealthcheckHandler());

        // Inicia o servidor
        server.start();

        logger.info("Servidor rodando em http://localhost:8080/healthcheck");
    }
}
