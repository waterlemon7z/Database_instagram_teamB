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
     /*
     * Name        : findByFollow
     * Author      : 이정대
     * Date        : 23/12/05
     * argument    : my id
     * return      : 내 id와 내가 팔로우한 사람의 id를 한 쌍으로 묶어서 만든 List를 반환합니다
     * description : 내가 팔로우한 사람을 데이터베이스의 follow 테이블에서 내 id를 통해 검색합니다
     */
    public List<Follow> findByFollow(int keyId) throws SQLException
    {
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
    }
     /*
     * Name        : findByFollowee
     * Author      : 이정대
     * Date        : 23/12/05
     * argument    : my id
     * return      : 나를 팔로우한 사람의 id와 내 id를 한 쌍으로 묶어서 만든 List를 반환합니다
     * description : 나를 팔로우한 사람을 데이터베이스의 follow 테이블에서 내 id를 통해 검색합니다
     */
    public List<Follow> findByFollowee(int keyId) throws SQLException
    {
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
    }
     /*
     * Name        : doFollow
     * Author      : 이정대
     * Date        : 23/12/05
     * argument    : my id, the other person's id
     * return      : 없습니다
     * description : 내 id와 상대방의 id를 데이터베이스의 follow 테이블에 추가함으로서 팔로우합니다
     */
    public void doFollow(int keyMyId, int keyTargetId) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = null;
        if(keyMyId == keyTargetId) return;
        try{
            String addFollow = "insert into follow (follow_id, followee_id) values (?, ?)";
            pstmt = con.prepareStatement(addFollow);

            pstmt.setInt(1, keyMyId);
            pstmt.setInt(2, keyTargetId);

            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     /*
     * Name        : cancelFollow
     * Author      : 이정대
     * Date        : 23/12/05
     * argument    : my id, the other person's id
     * return      : 없습니다
     * description : 내 id와 상대방의 id를 데이터베이스의 follow 테이블에서 제거함으로서 팔로우를 취소합니다
     */
    public void cancelFollow(int keyMyId, int keyTargetId) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = null;
        try{
            String cancelFollow = "delete from follow where follow_id = ? and followee_id = ?";
            pstmt = con.prepareStatement(cancelFollow);

            pstmt.setInt(1, keyMyId);
            pstmt.setInt(2, keyTargetId);

            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
