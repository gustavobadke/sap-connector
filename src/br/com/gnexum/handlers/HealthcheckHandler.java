package br.com.gnexum.handlers;

import java.io.IOException;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import br.com.gnexum.utils.HttpResponse;
import java.util.logging.Logger;

public class HealthcheckHandler implements HttpHandler {
  private static final Logger logger = Logger.getLogger(HealthcheckHandler.class.getName());

  @Override
  public void handle(HttpExchange exchange) throws IOException {
    try {
      JCoDestination destination = JCoDestinationManager.getDestination("SAP_CONFIG");

      String response = "Connected to SAP system: " + destination.getAttributes().getSystemID();

      logger.info(response);

      HttpResponse.sendResponse(exchange, 200, response);
    } catch (JCoException e) {
      e.printStackTrace();
      HttpResponse.sendResponse(exchange, 500, e.getMessage());
    }
  }
}
