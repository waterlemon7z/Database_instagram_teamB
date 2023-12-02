package repository.article_comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionManager;

public class ArticleCommentRepository
{
    public void connectArticleComment(int article_id, int comment_id) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = null;
        if(article_id == comment_id) return;
        try{
            String connect = "insert into article_comment value (?, ?)";
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
    public List<Integer> findByArticleId(int article_id) throws SQLException
    {
        List<Integer> rst = new ArrayList<>();
        Connection con = ConnectionManager.getCon();
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from article_comment where article_id=" + article_id);
        while(resultSet.next())
        {
            rst.add(resultSet.getInt(2));
        }
        return rst;
    }
}
