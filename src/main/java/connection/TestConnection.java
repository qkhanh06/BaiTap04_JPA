package connection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TestConnection {

    public static void main(String[] args) {

        String sql =
                "SELECT CategoryId, CategoryName, Images, status "
                + "FROM categories";

        try {
            DBConnection dbConnection =
                    new DBConnection();

            Connection connection =
                    dbConnection.getConnection();

            System.out.println("SQL Server connected successfully.");

            PreparedStatement ps =
                    connection.prepareStatement(sql);

            ResultSet rs =
                    ps.executeQuery();

            System.out.println("----- categories -----");

            while (rs.next()) {
                System.out.println(
                        rs.getInt("CategoryId")
                        + " | "
                        + rs.getString("CategoryName")
                        + " | "
                        + rs.getString("Images")
                        + " | "
                        + rs.getInt("status"));
            }

            rs.close();
            ps.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Connection test failed.");
            e.printStackTrace();
        }
    }
}
