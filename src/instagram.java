import com.sun.javaws.IconUtil;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;
import entity.Article.Article;
import entity.Article.Article_hashtag;
import entity.Article.Article_image;
import entity.Article.Article_likes;
import exception.ArticleNotFoundException;
import exception.EntityInvalidException;
import jdbc.ConnectionManager;
import repository.article.ArticleRepository;
import service.ArticleService;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class instagram
{
    public static void main(String[] args) throws SQLException
    {
        Connection connection = ConnectionManager.getConnection();
        ArticleService articleService = new ArticleService();
        ArticleRepository articleRepository = new ArticleRepository();
//        List<Article> byId = articleService.searchByHashtag("#1");
//        for (Article col : byId)
//        {
//            System.out.println("col = " + col.toString());
//        }


//        List<Integer> arr = new ArrayList<>();
//        arr.add(1);
//        arr.add(2);
//        arr.add(3);
//        List<Article> articles = articleService.searchByFollower(arr);
//        for (Article col : articles)
//        {
//            System.out.println(col.toString());
//        }
//        articleRepository.delete(articleRepository.findById(1).get(0));

        List<Article_hashtag> hashs = new ArrayList<>();
        hashs.add(new Article_hashtag(7, "#1"));
        hashs.add(new Article_hashtag(7, "#2"));
        hashs.add(new Article_hashtag(7, "#4"));
        Article newa = new Article(7,1,"te12st121231", LocalDateTime.now(), null,new ArrayList<>(), hashs);

        articleService.deleteArticle(newa);
        connection.close();
    }
}
