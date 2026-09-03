import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class SeedIphoneCategories {

    public static void main(String[] args) throws Exception {
        String url =
                "jdbc:sqlserver://localhost:1433"
                + ";databaseName=ServletCRUDMVC"
                + ";encrypt=true"
                + ";trustServerCertificate=true"
                + ";loginTimeout=5";

        String[][] categories = {
                {"iPhone XS", "category/iphone-xs.jfif"},
                {"iPhone 13", "category/iphone-13.jfif"},
                {"iPhone 14", "category/iphone-14.jfif"},
                {"iPhone 15", "category/iphone-15.jfif"},
                {"iPhone 16", "category/iphone-16.jfif"},
                {"iPhone 17", "category/iphone-17.jfif"}
        };

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

        try (Connection connection =
                     DriverManager.getConnection(
                             url,
                             "YOUR_SQLSERVER_USERNAME",
                             "YOUR_SQLSERVER_PASSWORD")) {

            try (Statement statement =
                         connection.createStatement()) {

                statement.executeUpdate("DELETE FROM dbo.Category");
                statement.executeUpdate(
                        "DBCC CHECKIDENT ('dbo.Category', RESEED, 0)");
            }

            try (PreparedStatement statement =
                         connection.prepareStatement(
                                 "INSERT INTO dbo.Category "
                                 + "(cate_name, icons) VALUES (?, ?)")) {

                for (String[] category : categories) {
                    statement.setString(1, category[0]);
                    statement.setString(2, category[1]);
                    statement.executeUpdate();
                }
            }
        }

        System.out.println("Seeded iPhone categories successfully.");
    }
}
