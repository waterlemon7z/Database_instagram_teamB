package repository.article;

import entity.Article.Article_hashtag;
import entity.Article.Article_image;
import jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleImageRepository
{
    List<Article_image> findByArticleId(int keyId)
    {
        Connection con = ConnectionManager.getCon();
        Statement stmt;
        List<Article_image> rst = new ArrayList<>();
        try
        {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from article_image where article_id=" + keyId);
            while  (resultSet.next())
            {
                int article_id = resultSet.getInt(1);
                String  image = resultSet.getString(2);
                rst.add(new Article_image(article_id, image));
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return rst;
    }
}
