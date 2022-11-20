package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlserver://;serverName=localhost;databaseName=SQLados");
    }
}
