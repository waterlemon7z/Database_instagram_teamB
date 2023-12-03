package entity.Article;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Article
{
    private final int article_id;
    private final int id;
    private final String content;
    private final LocalDateTime date;
    private  List<Article_likes> likes;
    private  List<Article_image> image;
    private  List<Article_hashtag> hashtag;

    public void setLikes(List<Article_likes> likes)
    {
        this.likes = likes;
    }

    public void setImage(List<Article_image> image)
    {
        this.image = image;
    }

    public void setHashtag(List<Article_hashtag> hashtag)
    {
        this.hashtag = hashtag;
    }

    public Article(int article_id, int id, String content, LocalDateTime date, List<Article_likes> likes, List<Article_image> image, List<Article_hashtag> hashtag)
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

    public LocalDateTime getDate()
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

    @Override
    public String toString()
    {
        return "Article{" +
                "article_id=" + article_id +
                ", id=" + id +
                ", content='" + content + '\'' +
                ", date=" + date +
                ", likes=" + likes +
                ", image=" + image +
                ", hashtag=" + hashtag +
                '}';
    }
}
