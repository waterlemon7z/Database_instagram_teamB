package service;

import entity.Article.Article;
import entity.Article.Article_hashtag;
import entity.Article.Article_likes;
import entity.Comment.Comment_likes;
import exception.EntityInvalidException;
import repository.article.ArticleHashtagRepository;
import repository.article.ArticleLikesRepository;
import repository.article.ArticleRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ArticleService
{
    private final ArticleRepository articleRepository = new ArticleRepository();
    private final ArticleHashtagRepository articleHashtagRepository = new ArticleHashtagRepository();
    private final ArticleLikesRepository articleLikesRepository = new ArticleLikesRepository();
    /*
     * Name        : searchByHashtag
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : String
     * return      : List<Article>
     * description : search article by hashtag
     */
    public List<Article> searchByHashtag(String hash)
    {
        List<Article> rst = new ArrayList<>();
        List<Article_hashtag> byHashTag = null;
        try
        {
            byHashTag = articleHashtagRepository.findByHashTag(hash);
            for (Article_hashtag iter : byHashTag)
            {
                Article byId = articleRepository.findByArticleId(iter.getArticle_id());
                rst.add(byId);
            }
        } catch (SQLException e)
        {
            System.out.println("select 실행 중 오류발생");
        }
        return rst;
    }
    /* Deprecated
     * Name        : searchByFollower
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : List<Integer>
     * return      : List<Article>
     * description : search article by follower
     */
    public List<Article> searchByFollower(List<Integer> ids)
    {
        List<Article> rst = new ArrayList<>();
        for (Integer eachid : ids)
        {
            try
            {
                rst.addAll(articleRepository.findById(eachid));
            } catch (SQLException e)
            {
                System.out.println("SQL 쿼리 오류 : searchByFollower");
            }
        }
        return rst;
    }
    /*
     * Name        : searchById
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : int
     * return      : List<Article>
     * description : search article by user id
     */
    public List<Article> searchById(int id)
    {
        List<Article> rst = new ArrayList<>();
        try
        {
            rst.addAll(articleRepository.findById(id));
        } catch (SQLException e)
        {
            System.out.println("SQL 쿼리 오류 : searchByFollower");
        }
        return rst;
    }
    /*
     * Name        : searchByArticleId
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : int
     * return      : Article
     * description : search article by article id
     */
    public Article searchByArticleId(int idx)
    {
        try
        {
            return articleRepository.findByArticleId(idx);
        } catch (SQLException e)
        {
            System.out.println("SQL 쿼리 실행 오류 : searchByArticleId =>" + super.toString());
        }
        return null;
    }
    /*
     * Name        : createArticle
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : boolean
     * return      : Article
     * description : make new article
     */
    public boolean createArticle(Article entity)
    {
        try
        {
            articleRepository.insertArticle(entity);
            return true;
        } catch (SQLException e)
        {
            System.out.println("insert 중 sql 오류 발생.");
            return false;
        }
    }
    /*
     * Name        : updateArticle
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : Article
     * return      : void
     * description : update article
     */
    public void updateArticle(Article entity)
    {
        try
        {
            articleRepository.updateArticle(entity);
        } catch (SQLException e)
        {
            System.out.println("update 중 sql 오류 발생.");
        } catch (EntityInvalidException e)
        {
            System.out.println(e.getMessage());
        }
    }
    /*
     * Name        : removeArticle
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : Article
     * return      : void
     * description : remove article
     */
    public void removeArticle(Article entity)
    {
        try
        {

            articleRepository.deleteArticle(entity);
        } catch (SQLException e)
        {
            System.out.println("delete 중 sql 오류 발생.");
        }
    }
    /*
     * Name        : likeSwitcher
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : int, int
     * return      : void
     * description : on/off likes
     */
    public void likeSwitcher(int article_id, int user_id)
    {
        try
        {
            List<Article_likes> byArticleId = articleLikesRepository.findByArticleId(article_id);
            boolean flag = false;
            for (Article_likes iter : byArticleId)
            {
                if(iter.getId() == user_id)
                {
                    flag = true;
                    break;
                }
            }
            if(flag)
                articleLikesRepository.decreaseLike(new Article_likes(article_id, user_id));
            else
                articleLikesRepository.increaseLike(new Article_likes(article_id, user_id));
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}

