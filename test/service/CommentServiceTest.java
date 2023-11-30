package service;

import entity.Comment.Comment;
import jdbc.ConnectionManager;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest
{
CommentService commentService = new CommentService();
    @Test
    void createComment()
    {
    }

    @Test
    void removeComment()
    {
        ConnectionManager.getConnection();
        Comment comment = commentService.searchByCommentId(7);
        if(comment != null)
        {
            commentService.removeComment(comment);
        }
    }
}