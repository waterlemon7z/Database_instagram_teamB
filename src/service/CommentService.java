package service;


import entity.Comment.Comment;
import entity.Comment.Comment_likes;
import exception.EntityInvalidException;
import repository.article_comment.ArticleCommentRepository;
import repository.comment.CommentLikesRepository;
import repository.comment.CommentRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentService
{
    private final CommentRepository commentRepository = new CommentRepository();
    private final ArticleCommentRepository articleCommentRepository = new ArticleCommentRepository();
    private final CommentLikesRepository commentLikesRepository = new CommentLikesRepository();
   public void createComment(Comment entity)
    {
        try
        {
            Comment comment = commentRepository.insertComment(entity);
            articleCommentRepository.connectArticleComment(comment.getArticle_id(), comment.getComment_id());
        } catch (SQLException e)
        {
            System.out.println("SQL ���� ���� : createComment");
        }
    }
    public void removeComment(int comment_id, int id)
    {
        try
        {
            Comment entity = commentRepository.findByCommentId(comment_id);
            if(entity.getId() != id)
                return;
            articleCommentRepository.disconnectArticleComment(entity.getArticle_id(), entity.getComment_id());
            commentRepository.deleteComment(entity);
        } catch (SQLException e)
        {
            System.out.println("SQL ���� ���� : removeComment");
        } catch (EntityInvalidException e)
        {
            System.out.println("Entity ���� : removeComment => " + super.toString());
        }
    }

    public void removeCommentByArticleId(int article_id)
    {
        try
        {
            List<Integer> byArticleId = articleCommentRepository.findByArticleId(article_id);
            for (Integer  iter : byArticleId)
            {
                articleCommentRepository.disconnectArticleComment(article_id, iter);
                commentRepository.deleteComment(commentRepository.findByCommentId(iter));
            }
        } catch (SQLException e)
        {
            System.out.println("SQL ���� ���� : removeComment");
        } catch (EntityInvalidException e)
        {
            System.out.println("Entity ���� : removeComment => " + super.toString());
        }
    }
    public Comment searchByCommentId(int idx)
    {
        try
        {
            return commentRepository.findByCommentId(idx);
        } catch (SQLException e)
        {
            System.out.println("SQL ���� ���� : searchByCommentId");
        }
        return null;
    }
    public List<Comment> searchByArticleId(int article_id)
    {
        List<Comment> rst = new ArrayList<>();
        try
        {
            List<Integer> byArticleId = articleCommentRepository.findByArticleId(article_id);
            for (Integer commentId : byArticleId)
            {
                Comment byCommentId = commentRepository.findByCommentId(commentId);
                rst.add(byCommentId);
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return rst;
    }
    public void likeSwitcher(int comment_id, int user_id)
    {
        try
        {
            List<Comment_likes> byCommentId = commentLikesRepository.findByCommentId(comment_id);
            boolean flag = false;
            for (Comment_likes iter : byCommentId)
            {
                if(iter.getId() == user_id)
                {
                    flag = true;
                    break;
                }
            }
            if(flag)
                commentLikesRepository.decreaseLike(new Comment_likes(comment_id, user_id));
            else
                commentLikesRepository.increaseLike(new Comment_likes(comment_id, user_id));
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
