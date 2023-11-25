package service;

import entity.Article.Article;
import entity.Article.Article_hashtag;
import jdbc.ConnectionManager;
import org.junit.jupiter.api.Test;
import repository.article.ArticleRepository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArticleServiceTest
{

    ArticleService articleService = new ArticleService();
    @Test
     void searchByHashtag()
    {
    }

    @Test
    void searchByFollower()
    {
    }

    @Test
    void createArticle() throws SQLException
    {
        Connection connection = ConnectionManager.getConnection();
        List<Article_hashtag> hashs = new ArrayList<>();
        hashs.add(new Article_hashtag(7, "#1"));
        hashs.add(new Article_hashtag(7, "#2"));
        hashs.add(new Article_hashtag(7, "#4"));
        Article newa = new Article(7,1,"te12st121231", LocalDateTime.now(), null,new ArrayList<>(), hashs);
        assert articleService.createArticle(newa);
        connection.close();
    }

    @Test
    void updateArticle() throws SQLException
    {
        Connection connection = ConnectionManager.getConnection();
        List<Article_hashtag> hashs = new ArrayList<>();
        hashs.add(new Article_hashtag(7, "#1"));
        hashs.add(new Article_hashtag(7, "#2"));
        hashs.add(new Article_hashtag(7, "#4"));
        Article newa = new Article(7,1,"te12st121231", LocalDateTime.now(), null,new ArrayList<>(), hashs);
        articleService.updateArticle(newa);
        connection.close();
    }

    @Test
    void deleteArticle() throws SQLException
    {
        Connection connection = ConnectionManager.getConnection();
        List<Article_hashtag> hashs = new ArrayList<>();
        hashs.add(new Article_hashtag(9, "#1"));
        hashs.add(new Article_hashtag(9, "#2"));
        hashs.add(new Article_hashtag(9, "#4"));
        Article newa = new Article(9,1,"te12st121231", LocalDateTime.now(), null,new ArrayList<>(), hashs);
        articleService.deleteArticle(newa);
        connection.close();
    }
}