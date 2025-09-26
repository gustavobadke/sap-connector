package br.com.gnexum;

import com.sap.conn.jco.*;

public class SapConnection {
    public static void main(String[] args) {
        try {
            System.out.println("Attempting to connect to SAP...");

            JCoDestination destination = JCoDestinationManager.getDestination("SAP_CONFIG");

            System.out.println("configurações carregadas");
            System.out.println(destination.getApplicationServerHost());
            System.out.println(destination.getSystemNumber());
            System.out.println(destination.getClient());
            System.out.println(destination.getUser());
            System.out.println(destination.getLanguage());

            //destination.ping();

            //System.out.println("Ping bem-sucedido!");

            //System.out.println("Connected to SAP system: " + destination.getAttributes().getSystemID());

            JCoFunction function = destination.getRepository().getFunction("J_1BNFDOC");
            if (function == null) {
                throw new RuntimeException("Função J_1BNFDOC não encontrada no SAP.");
            }

            // 1. Define os parâmetros de entrada (exemplo fictício)
            JCoParameterList importParams = function.getImportParameterList();
            importParams.setValue("DOCUMENT_NUMBER", "123456789");

            // 2. Executa a função no SAP
            function.execute(destination);

            // 3. Lê parâmetros de saída (export)
            JCoParameterList exportParams = function.getExportParameterList();
            String status = exportParams.getString("STATUS");
            System.out.println("Status da NF: " + status);

            // 4. Se houver tabelas de retorno
            JCoTable table = function.getTableParameterList().getTable("NFE_DATA");
            for (int i = 0; i < table.getNumRows(); i++) {
                table.setRow(i);
                System.out.println("NF-e: " + table.getString("NFE_NUMBER"));
                // Pegue outros campos conforme necessário
            }


            JCoFunction testFunc = destination.getRepository().getFunction("STFC_CONNECTION");
            testFunc.execute(destination);
            System.out.println(testFunc.getExportParameterList().getString("ECHOTEXT"));
            
        } catch (JCoException e) {
            e.printStackTrace();
        }
    }
}




//J_1BNFDOC