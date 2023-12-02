package service;

import entity.User;
import jdbc.ConnectionManager;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest
{
    private final UserService userService= new UserService();
    @Test
    void createUser()
    {
        ConnectionManager.getConnection();
        User userId = new User(0, "hello", "hwela");
        User user = userService.createUser(userId);
        System.out.println("userId.getId() = " + user.getId());
    }

    @Test
    void deleteUser()
    {
        ConnectionManager.getConnection();
        User userId = new User(4, "hello", "hwela");
        userService.deleteUser(userId.getId());
    }

    @Test
    void updateUser()
    {
    }

    @Test
    void getUserById()
    {
        ConnectionManager.getConnection();
        User userId = userService.getUserByUserId("db_man");
        System.out.println("userId.getId() = " + userId.getId());
    }
}