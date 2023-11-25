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
    List<Article_likes> findByArticleId(int keyId)
    {
        Connection con = ConnectionManager.getCon();
        Statement stmt;
        List<Article_likes> rst = new ArrayList<>();
        try
        {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery("select * from article_likes where article_id=" + keyId);
            while  (resultSet.next())
            {
                int article_id = resultSet.getInt(1);
                int  id = resultSet.getInt(2);
                rst.add(new Article_likes(article_id, id));
            }
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return rst;
    }
    public void increaseLike(Article_likes entity)
    {
        Connection con = ConnectionManager.getCon();
        if(entity == null)
            return;
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("insert into article_likes value (" + entity.getArticle_id() + ", " + entity.getId() + ")");
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public void decreaseLike(Article_likes entity)
    {
        Connection con = ConnectionManager.getCon();
        if(entity == null)
            return;
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from article_likes where article_id = " + entity.getArticle_id() +" and id=" + entity.getId());
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void deleteLikes(List<Article_likes> entity)
    {
        Connection con = ConnectionManager.getCon();
        if(entity == null)  return;
        if(entity.size() == 0)
            return;
        try
        {
            Statement stmt = con.createStatement();
            stmt.executeUpdate("delete from article_likes where article_id="+entity.get(0).getArticle_id());
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}
