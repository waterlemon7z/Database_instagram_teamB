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
        String url = "jdbc:mysql://localhost/instagram";
        String id = "root";
        String pwd = "0000";

        try {
            Class.forName(driver);
            try {
                con = DriverManager.getConnection(url, id, pwd);
                System.out.println("Connected...");
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                System.out.println("Connection Failed!");
                e.printStackTrace(); 	// 예외 발생시 내용 출력
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            System.out.println("Connection Failed. Check Driver or URL");
            e.printStackTrace(); 		// 예외 발생시 내용 출력
        }

        return con;
    }//getConnection()
}