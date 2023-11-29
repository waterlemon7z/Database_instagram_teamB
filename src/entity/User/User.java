package entity;

public class User {
    private final int id;
    private final String user_id;
    private final String pw;

    public User(int id, String user_id, String pw) {
        this.id = id;
        this.user_id = user_id;
        this.pw = pw;
    }

    public int getId() {
        return id;
    }

    public String getUser_id() {
        return user_id;
    }
// user 정보 문자열로
    public String getUserDetails() {
        return "User{" +
                "ID=" + id +
                ", UserID='" + user_id + '\'' +
                '}';
    }
}
