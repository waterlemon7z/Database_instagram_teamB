package repository.article_comment;

import entity.Comment.Comment;
import entity.Comment.Comment_tags;
import jdbc.ConnectionManager;
import org.junit.jupiter.api.Test;
import repository.comment.CommentRepository;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleCommentRepositoryTest
{
    private final  ArticleCommentRepository articleCommentRepository = new ArticleCommentRepository();
    private final CommentRepository commentRepository = new CommentRepository();
    @Test
    void connectArticleComment() throws SQLException
    {
        ConnectionManager.getConnection();
        List<Comment_tags> tags = new ArrayList<>();
        tags.add(new Comment_tags(0, "@lemon7z", 3));
        tags.add(new Comment_tags(0, "@saewonmin", 2));
        Comment cmt = new Comment(0, 1, "yeah you", 6, LocalDateTime.now(), null, tags);
        commentRepository.insertComment(cmt);

    }

    @Test
    void disconnectArticleComment()
    {
    }
}