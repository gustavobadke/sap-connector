package br.com.gnexum;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;

public class SapConnection {
    public static void main(String[] args) {
        try {
            System.out.println("Attempting to connect to SAP...");

            JCoDestination destination = JCoDestinationManager.getDestination("SAP_CONFIG");

            System.out.println("Connection successful!");

            destination.ping();

            System.out.println("Ping successful!");

            System.out.println("Connected to SAP system: " + destination.getAttributes().getSystemID());

            JCoFunction testFunc = destination.getRepository().getFunction("STFC_CONNECTION");
            testFunc.execute(destination);
            System.out.println(testFunc.getExportParameterList().getString("ECHOTEXT"));
            
        } catch (JCoException e) {
            e.printStackTrace();
        }
    }
}




//J_1BNFDOC