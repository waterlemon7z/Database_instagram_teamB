package entity;

public class Follow {
    private final int follow_id;
    private final int followee_id;
    public Follow(int follow_id, int followee_id)
    {
        this.follow_id = follow_id;
        this.followee_id = followee_id;
    }
    public int getFollow_id()
    {
        return follow_id;
    }
    public int getFollowee_id()
    {
        return followee_id;
    }

    @Override
    public String toString()
    {
        return "Follow{" +
                "follow_id=" + follow_id +
                ", followee_id=" + followee_id +
                '}';
    }
}
