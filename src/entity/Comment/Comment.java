package entity.Comment;

import java.time.LocalDateTime;
import java.util.List;

public class Comment
{
    private  int comment_id;
    private final int id;
    private final String text;
    private final int article_id;
    private final LocalDateTime date;
    private final List<Comment_likes> likes;
    private final List<Comment_tags> tags;


    public void setComment_id(int comment_id)
    {
        this.comment_id = comment_id;
    }

    public Comment(int comment_id, int id, String text, int article_id, LocalDateTime date, List<Comment_likes> likes, List<Comment_tags> tags)
    {
        this.comment_id = comment_id;
        this.id = id;
        this.text = text;
        this.article_id = article_id;
        this.date = date;
        this.likes = likes;
        this.tags = tags;
    }

    public List<Comment_likes> getLikes()
    {
        return likes;
    }

    public List<Comment_tags> getTags()
    {
        return tags;
    }

    public int getComment_id()
    {
        return comment_id;
    }

    public int getId()
    {
        return id;
    }

    public String getText()
    {
        return text;
    }

    public int getArticle_id()
    {
        return article_id;
    }

    public LocalDateTime getDate()
    {
        return date;
    }

    @Override
    public String toString()
    {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", id=" + id +
                ", text='" + text + '\'' +
                ", article_id=" + article_id +
                ", date=" + date +
                ", likes=" + likes +
                ", tags=" + tags +
                '}';
    }
}
