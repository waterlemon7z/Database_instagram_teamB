package repository.article;

import entity.Article.Article_hashtag;
import jdbc.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleHashtagRepository
{
    /*
     * Name        : findByArticleId
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : int
     * return      : List<Article_hashtag>
     * description : Find article hashtag in article_hashtag table by Article id
     */
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
    /*
     * Name        : findByHashTag
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : String
     * return      : List<Article_hashtag>
     * description : Find article hashtag in article_hashtag table by hashtag
     */
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
    /*
     * Name        : insertHashtag
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : int, List<Article_hashtag>
     * return      : void
     * description : Insert hashtag into database table
     */
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
    /*
     * Name        : deleteHashtag
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : List<Article_hashtag>
     * return      : void
     * description : delete hashtag from database table
     */
    public void deleteHashtag(List<Article_hashtag> entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        if (entity.size() == 0)
            return;
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from article_hashtag where article_id=" + entity.get(0).getArticle_id());
    }
}
