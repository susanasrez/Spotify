package controller.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBManager {

    Connection conn;
    Statement statement;

    public DBManager() {
        connect();
    }

    public Connection connect() {
        String dbPath = "jdbc:sqlite:C:\\Users\\Susana\\Desktop\\Universidad\\Segundo de Ingenieria de datos\\Project\\DBSpotify.db";
        try {
            conn = DriverManager.getConnection(dbPath);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    public void setClose() throws SQLException {
        conn.close();
        System.out.println("Connection to SQLite has been closed.");
    }
}
