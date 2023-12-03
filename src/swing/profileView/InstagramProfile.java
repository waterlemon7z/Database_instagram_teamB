package swing.profileView;

public class InstagramProfile {
    private final int id;
    private final String username;
    private String imgPath;
    private String intro;
    private int postsCount;
    private int followersCount;
    private int followingCount;

    // Constructor including the index parameter
    public InstagramProfile(int index, String imgPath, String username, String bioLabel,
                            int postsCount, int followersCount, int followingCount) {
        this.id = index;
        this.imgPath = imgPath;
        this.username = username;
        this.intro = bioLabel;
        this.postsCount = postsCount;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
    }

    // Getter for index
    public int getId() {
        return id;
    }

    // Your existing getters...
    public String getImgPath() {
        return imgPath;
    }

    public String getUsername() {
        return username;
    }

    public String getIntro() {
        return intro;
    }

    public int getPostsCount() {
        return postsCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public int getFollowingCount() {
        return followingCount;
    }
}

