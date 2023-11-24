package entity.Article;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Article
{
    private final int article_id;
    private final int id;
    private final String content;
    private final Date date;
    private final List<Article_likes> likes;
    private final List<Article_image> image;
    private final List<Article_hashtag> hashtag;

    public Article(int article_id, int id, String content, Date date, List<Article_likes> likes, List<Article_image> image, List<Article_hashtag> hashtag)
    {
        this.article_id = article_id;
        this.id = id;
        this.content = content;
        this.date = date;
        this.likes = likes;
        this.image = image;
        this.hashtag = hashtag;
    }

    @Override
    public String toString()
    {
        return "Article{" +
                "article_id=" + article_id +
                ", id=" + id +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", likes=" + likes.size() +
                ", image=" + image +
                ", hashtag=" + hashtag.toString() +
                '}';
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

    public List<Article_likes> getLikes()
    {
        return likes;
    }

    public List<Article_image> getImage()
    {
        return image;
    }

    public List<Article_hashtag> getHashtag()
    {
        return hashtag;
    }
}
