package repository.comment;

import entity.Comment.Comment;
import entity.Comment.Comment_likes;
import entity.Comment.Comment_tags;
import exception.EntityInvalidException;
import jdbc.ConnectionManager;
import repository.article_comment.article_commentRepository;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.List;

public class CommentRepository
{
    private final CommentLikesRepository commentLikesRepository = new CommentLikesRepository();
    private final CommentTagsRepository commentTagsRepository = new CommentTagsRepository();
    public Comment findByCommentId(int keyId) throws SQLException
    {
        List<Comment_likes> commentLikes = commentLikesRepository.findByCommentId(keyId);
        List<Comment_tags> commentTags = commentTagsRepository.findByCommentId(keyId);

        Connection con = ConnectionManager.getCon();
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from comment where comment_id=" + keyId);
        Comment rst = null;
        while (resultSet.next())
        {
            int comment_id = resultSet.getInt(1);
            int id = resultSet.getInt(2);
            String text = resultSet.getString(3);
            int article_id = resultSet.getInt(4);
            LocalDateTime date = resultSet.getTimestamp(5).toLocalDateTime();
            rst = new Comment(comment_id, id, text, article_id, date, commentLikes, commentTags);
        }
        return rst;
    }

    public void insertComment(Comment entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        article_commentRepository a_c = new article_commentRepository();
        PreparedStatement pstmt = con.prepareStatement("insert into comment value(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, 0);
        pstmt.setInt(2, entity.getId());
        pstmt.setString(3, entity.getText());
        pstmt.setInt(4, entity.getArticle_id());
        pstmt.setTimestamp(5, Timestamp.valueOf(entity.getDate()));
        pstmt.executeUpdate();
        ResultSet generatedKeys = pstmt.getGeneratedKeys();
        generatedKeys.next();
        commentTagsRepository.insertTag(generatedKeys.getInt(1), entity.getTags());
        a_c.connectArticleComment(entity.getArticle_id(), entity.getComment_id());
    }
    public void deleteComment(Comment entity) throws SQLException, EntityInvalidException
    {
        if (entity == null) throw new EntityInvalidException();
        commentTagsRepository.deleteTag(entity.getTags());
        commentLikesRepository.deleteLikes(entity.getLikes());

        Connection con = ConnectionManager.getCon();
        article_commentRepository a_c = new article_commentRepository();
        Statement stmt = con.createStatement();
        a_c.disconnectArticleComment(entity.getArticle_id(), entity.getComment_id());
        stmt.executeUpdate("delete from comment where comment_id="+entity.getComment_id());

    }
}
