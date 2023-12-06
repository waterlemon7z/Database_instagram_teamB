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
import java.util.List;

public class ArticleRepository
{
    private final ArticleHashtagRepository articleHashtagRepository = new ArticleHashtagRepository();
    private final ArticleImageRepository articleImageRepository = new ArticleImageRepository();
    private final ArticleLikesRepository articleLikesRepository = new ArticleLikesRepository();

    /*
     * Name        : findById
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : int
     * return      : List<Article>
     * description : find article by Id
     */
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
            rst.add(new Article(article_id, id, content, date, null, null, null));
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

    /*
     * Name        : findByArticleId
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : int
     * return      : Article
     * description : find article by article id
     */
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

    /*
     * Name        : insertArticle
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : Article
     * return      : void
     * description : insert article
     */
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

    /*
     * Name        : deleteArticle
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : Article
     * return      : void
     * description : delete article from article table
     */
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

    /*
     * Name        : updateArticle
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : Article
     * return      : void
     * description : update article from article table
     */
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

