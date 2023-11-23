package entity.Article;

public class Article_likes
{
    private final int article_id;
    private final int id;

    public Article_likes(int article_id, int id)
    {
        this.article_id = article_id;
        this.id = id;
    }

    public int getArticle_id()
    {
        return article_id;
    }

    public int getId()
    {
        return id;
    }
}
