package repository.userRepository;

import entity.User;
import jdbc.ConnectionManager;

import java.sql.*;
/*
 * Name        : UserRepository
 * Author      : [kang min jae]
 * Date        : 2023-12-05
 * Arguments   : User object or user ID
 * Return      : User object (for insertUser, updateUser, findUserById), void (for deleteUser)
 * Description : This class is responsible for direct database operations related to users. It handles inserting, deleting,
 *               updating, and finding users in the database. The class uses SQL queries to interact with the database,
 *               and it manages database connections and resource clean-up.
 */
public class UserRepository
{
    public User insertUser(User user) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO user (user_id, pw) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, user.getUser_id());
        pstmt.setString(2, user.getPw());
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        if (rs.next())
        {
            int newId = rs.getInt(1);
            user = new User(newId, user.getUser_id(), user.getPw());
        }
        return user;
    }

    public void deleteUser(int userId) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = con.prepareStatement("DELETE FROM user WHERE id = ?");
        pstmt.setInt(1, userId);
        pstmt.executeUpdate();
    }

    public User updateUser(User user) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = con.prepareStatement("UPDATE user SET user_id = ?, pw = ? WHERE id = ?");
        pstmt.setString(1, user.getUser_id());
        pstmt.setString(2, user.getPw());
        pstmt.setInt(3, user.getId());
        pstmt.executeUpdate();
        return user;
    }

    public User findUserById(int userId) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM user WHERE id = ?");
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();
        User user = null;
        if (rs.next())
        {
            user = new User(rs.getInt("id"), rs.getString("user_id"), rs.getString("pw"));
        }
        return user;
    }

    public User findUserByUserId(String userId) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM user WHERE user_id = ?");
        pstmt.setString(1, userId);
        ResultSet rs = pstmt.executeQuery();
        User user = null;
        if (rs.next())
        {
            user = new User(rs.getInt("id"), rs.getString("user_id"), rs.getString("pw"));
        }
        return user;
    }
}