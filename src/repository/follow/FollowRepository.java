package repository.follow;

import entity.Follow;
import exception.EntityInvalidException;
import jdbc.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FollowRepository {
    int follow_id;
    int followee_id;
    //내가 팔로우한 사람 찾기
    public List<Follow> findByFollow(int keyId)
    {
        try{
            Connection con = ConnectionManager.getCon();
            List<Follow> lst = new ArrayList<>();
            String selectFollow = "select * from follow where follow_id = ?";
            PreparedStatement pstmt = con.prepareStatement(selectFollow);
            pstmt.setInt(1, keyId);
            
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                follow_id = rs.getInt(1);
                followee_id = rs.getInt(2);
                lst.add(new Follow(follow_id, followee_id));
            }
            return lst;
        } catch (SQLException e)
        {
        throw new RuntimeException(e);
        }
    }
    //나를 팔로우한 사람 찾기
    public List<Follow> findByFollowee(int keyId)
    {
        try{
            Connection con = ConnectionManager.getCon();
            List<Follow> lst = new ArrayList<>();
            String selectFollow = "select * from follow where followee_id = ?";
            PreparedStatement pstmt = con.prepareStatement(selectFollow);
            pstmt.setInt(1, keyId);
            
            ResultSet rs = pstmt.executeQuery();

            while (rs.next())
            {
                follow_id = rs.getInt(1);
                followee_id = rs.getInt(2);
                lst.add(new Follow(follow_id, followee_id));
            }
            return lst;
        } catch (SQLException e)
        {
        throw new RuntimeException(e);
        }
    }

    //팔로우 하기
    public void doFollow(int keyMyId, int keyTargetId) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = null;
        try{
            int followIdToInsert = keyMyId;
            int followeeIdToInsert = keyTargetId;

            String addFollow = "insert into follow (follow_id, followee_id) values (?, ?)";
            pstmt = con.prepareStatement(addFollow);

            pstmt.setInt(1, followIdToInsert);
            pstmt.setInt(2, followeeIdToInsert);

            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //팔로우 취소
    public void cancelFollow(int keyId) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = null;
        try{
            int followIdToDelete = keyId;

            String cancelFollow = "delete from follow where follow_id = ?";
            pstmt = con.prepareStatement(cancelFollow);

            pstmt.setInt(1, followIdToDelete);
            
            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
