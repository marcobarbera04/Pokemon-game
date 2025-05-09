package serverSide;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseQuery {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/pokemon_game";
        String user = "root";
        String password = "";

        // Load JDBC driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Create connection
        try (Connection connection = DriverManager.getConnection(url, user, password); Statement statement = connection.createStatement()) {
            // Execute query
            String query = "SELECT id FROM pokemons";
            ResultSet resultSet = statement.executeQuery(query);

            // Print results
            while (resultSet.next()) {
                int id = resultSet.getInt("id");

                System.out.println("ID: " + id);
            }
        }
    }
}
