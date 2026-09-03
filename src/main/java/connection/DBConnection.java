package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private final String serverName = "localhost";
    private final String portNumber = "1433";
    private final String dbName = "ServletCRUDMVC";

    private final String userID = "YOUR_SQLSERVER_USERNAME";
    private final String password = "YOUR_SQLSERVER_PASSWORD";

    public Connection getConnection() throws Exception {

        String url =
                "jdbc:sqlserver://"
                + serverName
                + ":"
                + portNumber
                + ";databaseName="
                + dbName
                + ";encrypt=true"
                + ";trustServerCertificate=true"
                + ";loginTimeout=5";

        Class.forName(
                "com.microsoft.sqlserver.jdbc.SQLServerDriver");

        return DriverManager.getConnection(
                url,
                userID,
                password);
    }
}
