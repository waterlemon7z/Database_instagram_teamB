package repository.article_comment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jdbc.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class article_commentRepository {
    public void connectArticleComment(int article_id, int comment_id) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = null;
        if(article_id == comment_id) return;
        try{
            String connect = "insert into article_comment (article_id, comment_id) values (?, ?)";
            pstmt = con.prepareStatement(connect);

            pstmt.setInt(1, article_id);
            pstmt.setInt(2, comment_id);

            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void disconnectArticleComment(int article_id, int comment_id) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = null;
        if(article_id == comment_id) return;
        try{
            String connect = "delete from article_comment where article_id = ? and comment_id = ?";
            pstmt = con.prepareStatement(connect);

            pstmt.setInt(1, article_id);
            pstmt.setInt(2, comment_id);

            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
