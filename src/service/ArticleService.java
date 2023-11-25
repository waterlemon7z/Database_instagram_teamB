package service;

import entity.Article.Article;
import entity.Article.Article_hashtag;
import exception.EntityInvalidException;
import repository.article.ArticleHashtagRepository;
import repository.article.ArticleRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleService
{
    private final ArticleRepository articleRepository = new ArticleRepository();
    private final ArticleHashtagRepository articleHashtagRepository = new ArticleHashtagRepository();

    public List<Article> searchByHashtag(String hash)
    {
        List<Article> rst = new ArrayList<>();
        List<Article_hashtag> byHashTag = null;
        try
        {
            byHashTag = articleHashtagRepository.findByHashTag(hash);
            for (Article_hashtag iter : byHashTag)
            {
                Article byId = articleRepository.findByArticleId(iter.getArticle_id());
                rst.add(byId);
            }
        } catch (SQLException e)
        {
            System.out.println("select 실행 중 오류발생");
        }
        return rst;
    }

    public List<Article> searchByFollower(List<Integer> ids)
    {
        List<Article> rst = new ArrayList<>();
        for (Integer eachid : ids)
        {
            rst.addAll(articleRepository.findById(eachid));
        }
        return rst;
    }

    public void createArticle(Article entity)
    {
        try
        {
            articleRepository.insertArticle(entity);
        } catch (SQLException e)
        {
            System.out.println("insert 중 sql 오류 발생.");
        }
    }

    public void updateArticle(Article entity)
    {
        try
        {
            articleRepository.updateArticle(entity);
        } catch (SQLException e)
        {
            System.out.println("update 중 sql 오류 발생.");
        } catch (EntityInvalidException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void deleteArticle(Article entity)
    {
        try
        {
            articleRepository.deleteArticle(entity);
        } catch (SQLException e)
        {
            System.out.println("update 중 sql 오류 발생.");
        }
    }
}

