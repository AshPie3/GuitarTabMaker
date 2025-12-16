package GuitarTabMaker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionSettings {
    private static final String URL ="jdbc:mariadb://127.0.0.1:3306/guitartab?serverTimezone=UTC"; // "jdbc:mysql://uymht1l8wfretrel:Rmg4qIx0YJpN1qhwYRW7@bv3szmnzzx3y6m4bbe3w-mysql.services.clever-cloud.com:3306/bv3szmnzzx3y6m4bbe3w"; //
    private static final String USER = "root";//""uymht1l8wfretrel";
    private static final String PASSWORD = "root";//""Rmg4qIx0YJpN1qhwYRW7";

    public static Connection getDatabaseConnection() throws SQLException {
        return DriverManager.getConnection(URL,USER, PASSWORD);
    }

}
