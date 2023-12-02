package repository.userInfoRepository;

import entity.UserInfo;
import jdbc.ConnectionManager;

import java.sql.*;

public class UserInfoRepository
{

    public UserInfo insertUserInfo(UserInfo user_info) throws SQLException {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = con.prepareStatement("INSERT INTO user_info (id, profile_image, intro, job, email, mobile) VALUES (?, ?, ?, ?, ?, ?)");
        pstmt.setInt(1, user_info.getId());
        pstmt.setString(2, user_info.getProfileImage());
        pstmt.setString(3, user_info.getIntro());
        pstmt.setString(4, user_info.getJob());
        pstmt.setString(5, user_info.getEmail());
        pstmt.setString(6, user_info.getMobile());
        pstmt.executeUpdate();
        return user_info;
    }

    public UserInfo updateUserInfo(UserInfo user_info) throws SQLException {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = con.prepareStatement("UPDATE user_info SET profile_image = ?, intro = ?, job = ?, email = ?, mobile = ? WHERE id = ?");
        pstmt.setString(1, user_info.getProfileImage());
        pstmt.setString(2, user_info.getIntro());
        pstmt.setString(3, user_info.getJob());
        pstmt.setString(4, user_info.getEmail());
        pstmt.setString(5, user_info.getMobile());
        pstmt.setInt(6, user_info.getId());
        pstmt.executeUpdate();
        return user_info;
    }

    public UserInfo findUserInfoById(int userId) throws SQLException {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = con.prepareStatement("SELECT * FROM user_info WHERE id = ?");
        pstmt.setInt(1, userId);
        ResultSet rs = pstmt.executeQuery();
        UserInfo user_info = null;
        if (rs.next()) {
            user_info = new UserInfo(rs.getInt("id"), rs.getString("profile_image"), rs.getString("intro"), rs.getString("job"), rs.getString("email"), rs.getString("mobile"));
        }
        return user_info;
    }

    public void deleteUserInfoById(int userId) throws SQLException {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = con.prepareStatement("DELETE FROM user_info WHERE id = ?");
        pstmt.setInt(1, userId);
        pstmt.executeUpdate();
    }
}