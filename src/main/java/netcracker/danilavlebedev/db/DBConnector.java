package netcracker.danilavlebedev.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnector {
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/db";
    private static final String DB_USERNAME = "netcracker";
    private static final String DB_PASSWORD = "netcracker";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
    }
}
