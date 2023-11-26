package entity.Comment;

public class Comment_likes
{
    private final int comment_id;
    private final int id;

    public Comment_likes(int comment_id, int id)
    {
        this.comment_id = comment_id;
        this.id = id;
    }

    public int getComment_id()
    {
        return comment_id;
    }

    public int getId()
    {
        return id;
    }
}
