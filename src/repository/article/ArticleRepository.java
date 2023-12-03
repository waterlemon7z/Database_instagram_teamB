package repository.article;

import entity.Article.Article;
import entity.Article.Article_hashtag;
import entity.Article.Article_image;
import entity.Article.Article_likes;
import exception.EntityInvalidException;
import jdbc.ConnectionManager;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ArticleRepository
{
    private final ArticleHashtagRepository articleHashtagRepository = new ArticleHashtagRepository();
    private final ArticleImageRepository articleImageRepository = new ArticleImageRepository();
    private final ArticleLikesRepository articleLikesRepository = new ArticleLikesRepository();

    //아이디로 게시글 찾기
    public List<Article> findById(int keyId) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from article where id = " + keyId);
        List<Article> rst = new ArrayList<>();
        while (resultSet.next())
        {
            int article_id = resultSet.getInt(1);
            int id = resultSet.getInt(2);
            String content = resultSet.getString(3);
            LocalDateTime date = resultSet.getTimestamp(4).toLocalDateTime();
            rst.add(new Article(article_id, id, content, date,null,null,null));
        }
        for (Article iter : rst)
        {
            int articleId = iter.getArticle_id();
            List<Article_hashtag> hashtags = articleHashtagRepository.findByArticleId(articleId);
            List<Article_image> images = articleImageRepository.findByArticleId(articleId);
            List<Article_likes> likes = articleLikesRepository.findByArticleId(articleId);
             iter.setImage(images);
             iter.setHashtag(hashtags);
             iter.setLikes(likes);
        }
        return rst;
    }

    //게시글 아이디로 찾기
    public Article findByArticleId(int keyId) throws SQLException
    {
        List<Article_hashtag> hashtags = articleHashtagRepository.findByArticleId(keyId);
        List<Article_image> images = articleImageRepository.findByArticleId(keyId);
        List<Article_likes> likes = articleLikesRepository.findByArticleId(keyId);
        Connection con = ConnectionManager.getCon();
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from article where article_id = " + keyId);
        Article rst;
        resultSet.next();
        int article_id = resultSet.getInt(1);
        int id = resultSet.getInt(2);
        String content = resultSet.getString(3);
        LocalDateTime date = resultSet.getTimestamp(4).toLocalDateTime();
        rst = new Article(article_id, id, content, date, likes, images, hashtags);

        return rst;
    }

    public void insertArticle(Article entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();

        PreparedStatement pstmt = con.prepareStatement("insert into article value (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
        pstmt.setInt(1, 0);
        pstmt.setInt(2, entity.getId());
        pstmt.setString(3, entity.getContent());
        pstmt.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
        pstmt.executeUpdate();
        ResultSet generatedKeys = pstmt.getGeneratedKeys();

        generatedKeys.next();
        articleHashtagRepository.insertHashtag(generatedKeys.getInt(1), entity.getHashtag());
        articleImageRepository.insertImage(generatedKeys.getInt(1), entity.getImage());
    }

    public void deleteArticle(Article entity) throws SQLException
    {
        if (entity == null) return;
        articleImageRepository.deleteImage(entity.getImage());
        articleHashtagRepository.deleteHashtag(entity.getHashtag());
        articleLikesRepository.deleteLikes(entity.getLikes());
        Connection con = ConnectionManager.getCon();

        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from article where article_id=" + entity.getArticle_id());
    }

    public void updateArticle(Article entity) throws EntityInvalidException, SQLException //글 변경 및, 해시태그 변경. 좋아요는 부동
    {
        if (findByArticleId(entity.getArticle_id()).getId() != entity.getId())
        {
            throw new EntityInvalidException("수정하는 게시물과 유저 불일치");
        }
        articleHashtagRepository.deleteHashtag(entity.getHashtag());
        articleImageRepository.deleteImage(entity.getImage());
        articleHashtagRepository.insertHashtag(entity.getArticle_id(), entity.getHashtag());
        articleImageRepository.insertImage(entity.getArticle_id(), entity.getImage());
        Connection con = ConnectionManager.getCon();

        Statement stmt = con.createStatement();
        stmt.executeUpdate("update article set content='" + entity.getContent() + "' where article_id =" + entity.getArticle_id());

    }
}

