package service;

import entity.Follow;
import repository.follow.FollowRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FollowService
{
    private final FollowRepository followRepository = new FollowRepository();
    /*
     * Name        : searchByFollow
     * Author      : ������
     * Date        : 2023-11-25
     * argument    : int
     * return      : List<Follow>
     * description : search follower by id
     */
    public List<Follow> searchByFollow(int key)
    {
        List<Follow> rst= new ArrayList<>();
        try
        {
            rst = followRepository.findByFollow(key);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return rst;
    }
    /*
     * Name        : searchByFollowee
     * Author      : ������
     * Date        : 2023-11-25
     * argument    : int
     * return      : List<Follow>
     * description : search followee by id
     */
    public List<Follow> searchByFollowee(int key)
    {
        List<Follow> rst= new ArrayList<>();
        try
        {
            rst = followRepository.findByFollowee(key);
        } catch (SQLException e)
        {
            throw new RuntimeException(e);
        }
        return rst;
    }
}
