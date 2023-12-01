package service;

import entity.Comment.Comment;
import entity.Comment.Comment_tags;
import jdbc.ConnectionManager;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CommentServiceTest
{
CommentService commentService = new CommentService();
    @Test
    void createComment()
    {ConnectionManager.getConnection();
        List<Comment_tags> tags = new ArrayList<>();
        tags.add(new Comment_tags(0, "@lemon7z", 3));
        tags.add(new Comment_tags(0, "@saewonmin", 2));
        Comment cmt = new Comment(0, 1, "yeah you", 5, LocalDateTime.now(), null, tags);
        commentService.createComment(cmt);

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