package swing;

public class InstagramProfile {
    private int index; // Add this field

    // Your existing fields...
    private String imgPath;
    private String username;
    private String bioLabel;
    private int postsCount;
    private int followersCount;
    private int followingCount;

    // Constructor including the index parameter
    public InstagramProfile(int index, String imgPath, String username, String bioLabel,
                            int postsCount, int followersCount, int followingCount) {
        this.index = index;
        this.imgPath = imgPath;
        this.username = username;
        this.bioLabel = bioLabel;
        this.postsCount = postsCount;
        this.followersCount = followersCount;
        this.followingCount = followingCount;
    }

    // Getter for index
    public int getIndex() {
        return index;
    }

    // Your existing getters...
    public String getImgPath() {
        return imgPath;
    }

    public String getUsername() {
        return username;
    }

    public String getBioLabel() {
        return bioLabel;
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

