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