package jdbc.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JdbcUtils {

    private static JdbcUtils instance = new JdbcUtils();
    private Connection connection;

    private JdbcUtils() {
        String connectionString = "jdbc:mysql://localhost:3306/public";
        String dbUser = "root";
        String dbPassword = "pass";
        Properties prop = new Properties();
        prop.put("password", dbPassword);
        prop.put("user", dbUser);

        try {
            connection = DriverManager.getConnection(connectionString, prop);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static JdbcUtils getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

}
