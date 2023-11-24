package repository.article;

import entity.Article.Article;
import entity.Article.Article_hashtag;
import entity.Article.Article_image;
import entity.Article.Article_likes;
import jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArticleRepository
{
    private final ArticleHashtagRepository articleHashtagRepository = new ArticleHashtagRepository();
    private final ArticleImageRepository articleImageRepository = new ArticleImageRepository();
    private final ArticleLikesRepository articleLikesRepository = new ArticleLikesRepository();
    public List<Article> findById(int keyId)
    {
        List<Article_hashtag> hashtags = articleHashtagRepository.findByArticleId(keyId);
        List<Article_image> images = articleImageRepository.findByArticleId(keyId);
        List<Article_likes> likes = articleLikesRepository.findByArticleId(keyId);
        Connection con = ConnectionManager.getCon();
        try
        {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from article where id = " + keyId);
            List<Article> rst = new ArrayList<>();
            while(resultSet.next())
            {
                int article_id = resultSet.getInt(1);
                int id = resultSet.getInt(2);
                String content = resultSet.getString(3);
                Date date = resultSet.getDate(4);
                rst.add(new Article(article_id, id, content, date, likes, images ,hashtags));
            }
            return rst;
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
