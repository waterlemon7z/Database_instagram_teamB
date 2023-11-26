package entity.Comment;

public class Comment_tags
{
    private final int comment_id;
    private final String tag;
    private final int id;

    public Comment_tags(int comment_id, String tag, int id)
    {
        this.comment_id = comment_id;
        this.tag = tag;
        this.id = id;
    }

    public String getTag()
    {
        return tag;
    }

    public int getId()
    {
        return id;
    }

    public int getComment_id()
    {
        return comment_id;
    }
}
