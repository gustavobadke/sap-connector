import com.sap.conn.jco.*;

public class SapConnection {
    public static void main(String[] args) {
        try {
            JCoDestination destination = JCoDestinationManager.getDestination("SAP_CONFIG");
            System.out.println("Connected to SAP system: " + destination.getAttributes().getSystemID());
        } catch (JCoException e) {
            e.printStackTrace();
        }
    }
}