package repository.comment;

import entity.Comment.Comment;
import entity.Comment.Comment_tags;
import exception.EntityInvalidException;
import jdbc.ConnectionManager;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentRepositoryTest
{
    private final CommentRepository commentRepository = new CommentRepository();
    @Test
    void commentRepository() throws SQLException
    {
        ConnectionManager.getConnection();
        Comment byCommentId = commentRepository.findByCommentId(1);
        System.out.println("byCommentId.toString() = " + byCommentId.toString());
    }

    @Test
    void insertComment() throws SQLException
    {
        ConnectionManager.getConnection();
        List<Comment_tags> tags = new ArrayList<>();
        tags.add(new Comment_tags(0, "@lemon7z", 3));
        tags.add(new Comment_tags(0, "@saewonmin", 2));
        Comment cmt = new Comment(0, 1, "yeah you", 6, LocalDateTime.now(), null, tags);
        commentRepository.insertComment(cmt);
    }

    @Test
    void deleteComment() throws SQLException, EntityInvalidException
    {
        ConnectionManager.getConnection();
        List<Comment_tags> tags = new ArrayList<>();
        tags.add(new Comment_tags(4, "@lemon7z", 3));
        tags.add(new Comment_tags(4, "@saewonmin", 2));
        Comment cmt = new Comment(4, 1, "yeah you", 6, LocalDateTime.now(), null, tags);
        commentRepository.deleteComment(cmt);
    }
}