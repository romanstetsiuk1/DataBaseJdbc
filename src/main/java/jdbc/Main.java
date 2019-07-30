package jdbc;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.*;

public class Main {

    public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

        //        connect with DB MySQL
        String userName = "root";
        String password = "pass";
//        String connectionUrl = "jdbc:mysql://localhost:3306/trading";
        String connectionUrl = "jdbc:mysql://localhost/javaJDBC?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager.getConnection(connectionUrl, userName, password);
             Statement statement = connection.createStatement()) {

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(user_id INT NOT NULL AUTO_INCREMENT," +
                    "name VARCHAR(30), information VARCHAR(30), image BLOB, PRIMARY KEY (user_id))");

            statement.executeUpdate("INSERT INTO users (name, information) VALUES ('user 1', 'new user')");
            statement.executeUpdate("INSERT INTO users SET name='user 2', information='lorem ipsum'");

            String userId = "1";

//            java injection
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE user_id=?");
            preparedStatement.setString(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println("user name: " + resultSet.getString("name"));
                System.out.println("user information: " + resultSet.getString("information"));
            }

//            Add image to DataBase
            BufferedImage image = ImageIO.read(new File("icon.jpg"));
            Blob blob = connection.createBlob();
            try (OutputStream outputStream = blob.setBinaryStream(1)) {
                ImageIO.write(image, "jpg", outputStream);
            }
            PreparedStatement statement1 = connection.prepareStatement
                    ("INSERT INTO users (name, image) VALUES (?,?)");
            statement1.setString(1, "user 1");
            statement1.setBlob(2, blob);
            statement1.execute();

        }

    }

}
