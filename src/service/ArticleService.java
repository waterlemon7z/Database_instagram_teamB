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
            try
            {
                rst.addAll(articleRepository.findById(eachid));
            } catch (SQLException e)
            {
                System.out.println("SQL 쿼리 오류 : searchByFollower");
            }
        }
        return rst;
    }

    public List<Article> searchById(int id)
    {
        List<Article> rst = new ArrayList<>();
        try
        {
            rst.addAll(articleRepository.findById(id));
        } catch (SQLException e)
        {
            System.out.println("SQL 쿼리 오류 : searchByFollower");
        }
        return rst;
    }

    public Article searchByArticleId(int idx)
    {
        try
        {
            return articleRepository.findByArticleId(idx);
        } catch (SQLException e)
        {
            System.out.println("SQL 쿼리 실행 오류 : searchByArticleId =>" + super.toString());
        }
        return null;
    }

    public boolean createArticle(Article entity)
    {
        try
        {
            articleRepository.insertArticle(entity);
            return true;
        } catch (SQLException e)
        {
            System.out.println("insert 중 sql 오류 발생.");
            return false;
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

    public void removeArticle(Article entity)
    {
        try
        {

            articleRepository.deleteArticle(entity);
        } catch (SQLException e)
        {
            System.out.println("delete 중 sql 오류 발생.");
        }
    }
}

