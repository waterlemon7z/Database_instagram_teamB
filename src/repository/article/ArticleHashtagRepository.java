package repository.article;

import entity.Article.Article_hashtag;
import jdbc.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleHashtagRepository
{
    public List<Article_hashtag> findByArticleId(int keyId) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        Statement stmt;
        List<Article_hashtag> rst = new ArrayList<>();
        stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from article_hashtag where article_id=" + keyId);
        while (resultSet.next())
        {
            int article_id = resultSet.getInt(1);
            String hashtag = resultSet.getString(2);
            rst.add(new Article_hashtag(article_id, hashtag));
        }
        return rst;
    }

    public List<Article_hashtag> findByHashTag(String keyHash) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        List<Article_hashtag> rst = new ArrayList<>();
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from article_hashtag where hashtag='" + keyHash + "'");
        while (resultSet.next())
        {
            int article_id = resultSet.getInt(1);
            String hashtag = resultSet.getString(2);
            rst.add(new Article_hashtag(article_id, hashtag));
        }

        return rst;
    }

    public void insertHashtag(int article_id, List<Article_hashtag> entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        if (entity == null || entity.size() == 0)
            return;
        PreparedStatement preparedStatement = con.prepareStatement("insert into article_hashtag value (?, ?)");
        for (Article_hashtag iter : entity)
        {
            preparedStatement.setInt(1,article_id);
            preparedStatement.setString(2,iter.getHashtag());
            preparedStatement.executeUpdate();
        }
    }

    public void deleteHashtag(List<Article_hashtag> entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        if (entity.size() == 0)
            return;
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from article_hashtag where article_id=" + entity.get(0).getArticle_id());
    }

}
