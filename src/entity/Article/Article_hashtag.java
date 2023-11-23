package entity.Article;

public class Article_hashtag
{
    private final int article_id;
    private final String hashtag;

    public Article_hashtag(int article_id, String hashtag)
    {
        this.article_id = article_id;
        this.hashtag = hashtag;
    }

    public int getArticle_id()
    {
        return article_id;
    }

    public String getHashtag()
    {
        return hashtag;
    }
}
