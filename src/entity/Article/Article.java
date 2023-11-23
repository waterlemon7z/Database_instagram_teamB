package entity.Article;

import java.util.Date;

public class Article
{
    private final int article_id;
    private final int id;
    private final String content;
    private final Date date;
    private final Article_likes[] likes;
    private final Article_image[] image;
    private final Article_hashtag[] hashtag;

    public Article(int article_id, int id, String content, Date date, Article_likes[] likes, Article_image[] image, Article_hashtag[] hashtag)
    {
        this.article_id = article_id;
        this.id = id;
        this.content = content;
        this.date = date;
        this.likes = likes;
        this.image = image;
        this.hashtag = hashtag;
    }

    public int getArticle_id()
    {
        return article_id;
    }

    public int getId()
    {
        return id;
    }

    public String getContent()
    {
        return content;
    }

    public Date getDate()
    {
        return date;
    }

    public Article_likes[] getLikes()
    {
        return likes;
    }

    public Article_image[] getImage()
    {
        return image;
    }

    public Article_hashtag[] getHashtag()
    {
        return hashtag;
    }
}
