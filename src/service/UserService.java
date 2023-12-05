/*
 * Name        : UserService
 * Author      : [kang min jae]
 * Date        : 2023-12-05
 * Arguments   : User object or user ID
 * Return      : User object (for createUser, updateUser, getUserById), void (for deleteUser)
 * Description : This class provides services related to user management. It includes methods for creating, deleting,
 *               updating, and retrieving user information. It interacts with the UserRepository to perform these operations,
 *               thereby abstracting the database operation details from the client.
 */
package service;

import entity.User;
import repository.userRepository.UserRepository;
import java.sql.SQLException;
import java.util.List;

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