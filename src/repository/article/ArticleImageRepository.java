package repository.article;

import entity.Article.Article_hashtag;
import entity.Article.Article_image;
import jdbc.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleImageRepository
{
    List<Article_image> findByArticleId(int keyId) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        Statement stmt;
        List<Article_image> rst = new ArrayList<>();
        stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from article_image where article_id=" + keyId);
        while  (resultSet.next())
        {
            int article_id = resultSet.getInt(1);
            String  image = resultSet.getString(2);
            rst.add(new Article_image(article_id, image));
        }
        return rst;
    }
    public void deleteImage(List<Article_image> entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        if(entity.size()== 0)
            return;
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from article_image where article_id="+entity.get(0).getArticle_id());
    }

    public void insertImage(int article_id, List<Article_image> image) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        if (image.size() == 0)
            return;
        PreparedStatement preparedStatement = con.prepareStatement("insert into article_image value (?, ?)");
        for (Article_image iter : image)
        {
            preparedStatement.setInt(1,article_id);
            preparedStatement.setString(2,iter.getImage());
            preparedStatement.executeUpdate();
        }
    }
}
