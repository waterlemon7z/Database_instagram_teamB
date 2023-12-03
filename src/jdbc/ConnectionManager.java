package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    private static Connection con = null;

    public static Connection getCon()
    {
        return con;
    }

    public static Connection getConnection() {
        String driver = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost/Instagram";
        String id = "root";
        String pwd = "whwogus4568";
        try {
            Class.forName(driver);
            try {
                con = DriverManager.getConnection(url, id, pwd);
                System.out.println("Connected...");
            } catch (SQLException e) {
                System.out.println("Connection Failed!");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Connection Failed. Check Driver or URL");
            e.printStackTrace();
        }
        return con;
    }
}