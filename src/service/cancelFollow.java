package service;

import jdbc.ConnectionManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import exception.EntityInvalidException;

public class cancelFollow {
    public static void main(String[] args) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            String url = "jdbc:mysql://localhost/Instagram";
            String user = "root";
            String password = "12345";
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        PreparedStatement pstmt = null;
        try{
            int followIdToDelete = 111;

            String cancelFollow = "delete from follow where follow_id = ?";
            pstmt = con.prepareStatement(cancelFollow);

            pstmt.setInt(1, followIdToDelete);
            
            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
