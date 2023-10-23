package db.core;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBHelp {
    static{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("connecting...");
        } catch (ClassNotFoundException e) {
            System.out.println("fail to connect...");
            throw new RuntimeException(e);
        }
    }


    public static Connection getConnection() throws IOException, SQLException {
        Properties properties = new Properties();
        InputStream in = DBHelp.class.getClassLoader().getResourceAsStream("JDBC.properties");
        properties.load(in);
        Connection connection = DriverManager.getConnection(properties.getProperty("url"),properties);
        return connection;
    }


}
