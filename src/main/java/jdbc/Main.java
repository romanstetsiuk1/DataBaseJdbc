package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //        connect with DB MySQL
        String userName = "root";
        String password = "pass";
//        String connectionUrl = "jdbc:mysql://localhost:3306/trading";
        String connectionUrl = "jdbc:mysql://localhost/javaJDBC?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(user_id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(30), information VARCHAR(30), PRIMARY KEY (user_id))");

            statement.executeUpdate("INSERT INTO users (name, information) VALUES ('user 1', 'new user')");
            statement.executeUpdate("INSERT INTO users SET name='user 2', information='lorem ipsum'");


        }

    }

}
