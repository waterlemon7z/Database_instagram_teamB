package repository.article;

import entity.Article.Article_hashtag;
import jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleHashtagRepository
{
    List<Article_hashtag> findByArticleId(int keyId)
    {
        Connection con = ConnectionManager.getCon();
        Statement stmt;
        List<Article_hashtag> rst = new ArrayList<>();
        try
        {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from article_hashtag where article_id=" + keyId);
            while  (resultSet.next())
            {
                int article_id = resultSet.getInt(1);
                String  hashtag = resultSet.getString(2);
                rst.add(new Article_hashtag(article_id, hashtag));
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return rst;
    }
}
