/*
 * Name        : User
 * Author      : [kang min jae]
 * Date        : 2023-12-05
 * Arguments   : int id (user's unique identifier), String user_id (user's username), String pw (user's password)
 * Return      : User object
 * Description : This class represents a user entity in the system. It encapsulates the user's ID, username, and password.
 *               Provides methods to access these properties. This class is fundamental in representing users within the application.
 */

package entity;

public class User
{
    private final int id;
    private final String user_id;
    private final String pw;

    public User(int id, String user_id, String pw)
    {
        this.id = id;
        this.user_id = user_id;
        this.pw = pw;
    }

    public int getId()
    {
        return id;
    }

    public String getUser_id()
    {
        return user_id;
    }

    public String getPw()
    {
        return pw;
    }
}
