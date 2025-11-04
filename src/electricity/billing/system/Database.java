package electricity.billing.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    Connection connection;
    Statement statement;

    public Database() {
        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to DB (change username and password if needed)
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Bill_System", "root", "Sgsit"
            );

            // Create Statement
            statement = connection.createStatement();

        } catch (ClassNotFoundException e) {
            System.err.println("Error: JDBC Driver not found: " + e.getMessage());
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: Database connection failed: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Database();
    }
}