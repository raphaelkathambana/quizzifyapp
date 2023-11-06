package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetDbConnection {
    private static Connection connection = null;

    private GetDbConnection() {
    }

    public static Connection getConnection() {
        if (connection == null) {
            connection = connection();
        }
        return connection;
    }

    private static Connection connection() {
        Connection conn = null;
        Properties mySql = new Properties();
        try (FileReader in = new FileReader("db.properties")) {
            mySql.load(in);
        } catch (IOException e) {
            System.out.println("Error loading db.properties from classpath." + e);
        }
        String username = mySql.getProperty("username");
        String password = mySql.getProperty("password");
        String url = mySql.getProperty("url");
        try {
            conn = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            System.out.println("Error connecting to the database." + e);
        }
        return conn;
    }
}
