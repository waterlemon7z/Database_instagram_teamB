/*
 * Name        : UserInfoService
 * Author      : [kang min jae]
 * Date        : 2023-12-05
 * Arguments   : User_info object or user ID
 * Return      : User_info object (for createUser_info, updateUser_info, getUser_info), void (for deleteUser_info)
 * Description : This class provides services for managing additional user information. It includes methods for creating, updating, retrieving, and deleting user information.
 *               It interacts with User_inforepository to perform database operations, abstracting the complexity of direct database interactions from the client.
 */

package service;

import entity.UserInfo;
import repository.userInfoRepository.UserInfoRepository;

import java.sql.SQLException;

public class UserInfoService {

    private final UserInfoRepository userInfoRepository = new UserInfoRepository();
    public UserInfo createUserInfo(UserInfo user_info) {
        try
        {
            return userInfoRepository.insertUserInfo(user_info);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public UserInfo updateUserInfo(UserInfo user_info) {
        try
        {
            return userInfoRepository.updateUserInfo(user_info);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public UserInfo getUserInfo(int userId) {
        try
        {
            return userInfoRepository.findUserInfoById(userId);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void deleteUserInfo(int userId)  {
        try
        {
            userInfoRepository.deleteUserInfoById(userId);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
    }
}