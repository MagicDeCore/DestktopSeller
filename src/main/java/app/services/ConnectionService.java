package app.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionService {

    private static ConnectionService connectionService = null;

    private ConnectionService() {}

    public static ConnectionService getConnectionService() {
        if (connectionService == null) {
            connectionService = new ConnectionService();
        }
        return connectionService;
    }

    public String testConnectionToDatabase() {
        String dataBase = "UNAVAILABLE";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/desktopappexternal?" +
                    "user=root&password=root");
            System.out.println("Connection Established Successfull and the DATABASE NAME IS:" +
                    conn.getMetaData().getDatabaseProductName());
            dataBase = conn.getMetaData().getDatabaseProductName();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
        return dataBase;
    }

    public boolean checkInternetConnection() throws IOException, InterruptedException {
        boolean success = false;
        Process process = java.lang.Runtime.getRuntime().exec("ping www.geeksforgeeks.org");
        int x = process.waitFor();
        if (x == 0) {
            success = true;
            System.out.println("Connection Successful, "
                    + "Output was " + x);
        }
        else {
            System.out.println("Internet Not Connected, "
                    + "Output was " + x);
        }
        return success;
    }
}
