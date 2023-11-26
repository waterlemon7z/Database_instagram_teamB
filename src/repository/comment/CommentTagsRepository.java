package repository.comment;

import entity.Article.Article_hashtag;
import entity.Comment.Comment;
import entity.Comment.Comment_tags;
import jdbc.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentTagsRepository
{
    public List<Comment_tags> findByCommentId(int keyId) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from comment_tags where comment_id=" + keyId);
        List<Comment_tags> rst = new ArrayList<>();
        while(resultSet.next())
        {
            int comment_id = resultSet.getInt(1);
            String  tag = resultSet.getString(2);
            int id = resultSet.getInt(3);
            rst.add(new Comment_tags(comment_id, tag, id));
        }
        return rst;
    }

    public void insertTag(int comment_id, List<Comment_tags> entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        if (entity.size() == 0)
            return;
        PreparedStatement preparedStatement = con.prepareStatement("insert into comment_tags value (?, ?, ?)");
        for (Comment_tags iter : entity)
        {
            preparedStatement.setInt(1,comment_id);
            preparedStatement.setString(2,iter.getTag());
            preparedStatement.setInt(3,iter.getId());
            preparedStatement.executeUpdate();
        }
    }
    public void deleteTag( List<Comment_tags> entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        if (entity.size() == 0)
            return;
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from comment_tags where comment_id=" + entity.get(0).getComment_id());
    }
}
