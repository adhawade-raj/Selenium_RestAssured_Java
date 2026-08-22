package int_allSeleniumCodes_2026;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Part04_DatabaseConnection {

    public static void main(String[] args) throws Exception {

        // Oracle database details
        String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1";
        String username = "system";
        String password = "oracle";

        // Establish connection
        Connection con = DriverManager.getConnection(url, username, password);

        // Create statement
        Statement stmt = con.createStatement();

        // Execute query
        String query = "SELECT customer_name, account_number FROM customer";

        ResultSet rs = stmt.executeQuery(query);

        // Read database result
        while (rs.next()) {

            String customerName = rs.getString("customer_name");
            String accountNumber = rs.getString("account_number");

            System.out.println("Customer Name: " + customerName);
            System.out.println("Account Number: " + accountNumber);
        }

        // Close connection
        rs.close();
        stmt.close();
        con.close();
    }
}
