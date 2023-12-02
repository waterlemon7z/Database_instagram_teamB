package service;

import entity.User;
import repository.userRepository.UserRepository;
import java.sql.SQLException;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public User createUser(User user) {
        try
        {
            return userRepository.insertUser(user);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void deleteUser(int userId){
        try
        {
            userRepository.deleteUser(userId);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public User updateUser(User user){
        try
        {
            return userRepository.updateUser(user);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public User getUserById(int userId){
        try
        {
            return userRepository.findUserById(userId);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
    public User getUserByUserId(String userId) {
        try
        {
            return userRepository.findUserByUserId(userId);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}