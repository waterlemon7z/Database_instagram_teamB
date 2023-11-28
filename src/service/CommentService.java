package service;

import entity.Comment.Comment;
import exception.EntityInvalidException;
import repository.article_comment.ArticleCommentRepository;
import repository.comment.CommentRepository;

import java.sql.SQLException;

public class CommentService
{
    private final CommentRepository commentRepository = new CommentRepository();
    private final ArticleCommentRepository articleCommentRepository = new ArticleCommentRepository();
    void createComment(Comment entity)
    {
        try
        {
            Comment comment = commentRepository.insertComment(entity);
            articleCommentRepository.connectArticleComment(comment.getArticle_id(), comment.getComment_id());
        } catch (SQLException e)
        {
            System.out.println("SQL 쿼리 오류 : createComment");
        }
    }
    void removeComment(Comment entity)
    {
        try
        {
            articleCommentRepository.disconnectArticleComment(entity.getArticle_id(), entity.getComment_id());
            commentRepository.deleteComment(entity);
        } catch (SQLException e)
        {
            System.out.println("SQL 쿼리 오류 : removeComment");
        } catch (EntityInvalidException e)
        {
            System.out.println("Entity 오류 : removeComment => " + super.toString());
        }
    }
    Comment searchByCommentId(int idx)
    {
        try
        {
            return commentRepository.findByCommentId(idx);
        } catch (SQLException e)
        {
            System.out.println("SQL 쿼리 오류 : searchByCommentId");
        }
        return null;
    }
}
