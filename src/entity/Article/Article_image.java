package entity.Article;

public class Article_image
{
    private final int article_id;
    private final String image;

    public Article_image(int article_id, String image)
    {
        this.article_id = article_id;
        this.image = image;
    }

    public int getArticle_id()
    {
        return article_id;
    }

    public String getImage()
    {
        return image;
    }
}
