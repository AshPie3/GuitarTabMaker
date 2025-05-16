package GuitarTabMaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSettings {
    private static final String URL = "jdbc:mariadb://127.0.0.1:3306/guitartab?serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    public static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER, PASSWORD);
    }

}
