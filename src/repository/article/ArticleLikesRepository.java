package repository.article;

import entity.Article.Article_image;
import entity.Article.Article_likes;
import jdbc.ConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArticleLikesRepository
{
   public List<Article_likes> findByArticleId(int keyId) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        Statement stmt;
        List<Article_likes> rst = new ArrayList<>();
        stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from article_likes where article_id=" + keyId);
        while  (resultSet.next())
        {
            int article_id = resultSet.getInt(1);
            int  id = resultSet.getInt(2);
            rst.add(new Article_likes(article_id, id));
        }
        return rst;
    }
    public void increaseLike(Article_likes entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        if(entity == null)
            return;
        Statement stmt = con.createStatement();
        stmt.executeUpdate("insert into article_likes value (" + entity.getArticle_id() + ", " + entity.getId() + ")");
    }
    public void decreaseLike(Article_likes entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        if(entity == null)
            return;
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from article_likes where article_id = " + entity.getArticle_id() +" and id=" + entity.getId());
    }

    public void deleteLikes(List<Article_likes> entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        if(entity == null)  return;
        if(entity.size() == 0)
            return;
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from article_likes where article_id="+entity.get(0).getArticle_id());
    }
}
