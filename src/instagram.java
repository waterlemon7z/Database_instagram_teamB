import entity.Article.Article;
import jdbc.ConnectionManager;
import repository.article.ArticleRepository;

import java.sql.Connection;
import java.util.List;

public class instagram
{
    public static void main(String[] args)
    {
        Connection connection = ConnectionManager.getConnection();
        ArticleRepository repository = new ArticleRepository();
        List<Article> byId = repository.findById(1);
        for (Article col : byId)
        {
            System.out.println("col = " + col.toString());
        }
    }
}
