package repository.follow;

import entity.Follow;
import jdbc.ConnectionManager;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FollowRepositoryTest
{
    FollowRepository followRepository = new FollowRepository();
    @Test
    void findByFollow()
    {
        ConnectionManager.getConnection();
        List<Follow> byFollow = followRepository.findByFollow(1);
        byFollow.forEach(iter ->{
            System.out.println("iter.toString() = " + iter.toString());
        });
    }
}