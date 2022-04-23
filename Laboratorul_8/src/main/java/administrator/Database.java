package administrator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL =
            "jdbc:oracle:thin:student/STUDENT@localhost:1521:xe";
    private static final String USER = "student";
    private static final String PASSWORD = "STUDENT";
    private static Connection connection = null;
    private Database() {}

    public static Connection getConnection() {
        if (connection == null)
            createConnection();
        return connection;
    }

    private static void createConnection() {
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            System.err.println(e);
        }
    }

    public static void closeConnection() throws SQLException {
        if (connection != null)
            connection.close();
    }
}

