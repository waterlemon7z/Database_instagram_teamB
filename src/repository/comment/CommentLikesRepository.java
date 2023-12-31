package repository.comment;

import entity.Article.Article_likes;
import entity.Comment.Comment_likes;
import jdbc.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CommentLikesRepository
{
    /*
     * Name        : findByCommentId
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : int
     * return      : List<Comment_likes>
     * description : find comment_likes by commentId
     */
    public List<Comment_likes> findByCommentId(int keyId) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from comment_likes where comment_id=" + keyId);
        List<Comment_likes> rst = new ArrayList<>();
        while (resultSet.next())
        {
            int comment_id = resultSet.getInt(1);
            int id = resultSet.getInt(2);
            rst.add(new Comment_likes(comment_id, id));
        }
        return rst;
    }
    /*
     * Name        : increaseLike
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : Comment_likes
     * return      : void
     * description : increase comment_likes by commentId
     */
    public void increaseLike(Comment_likes entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = con.prepareStatement("insert into comment_likes value (?,?)");
        pstmt.setInt(1, entity.getComment_id());
        pstmt.setInt(2, entity.getId());
        pstmt.executeUpdate();
    }
    /*
     * Name        : decreaseLike
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : Comment_likes
     * return      : void
     * description : decrease comment_likes by commentId
     */
    public void decreaseLike(Comment_likes entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from comment_likes where comment_id="+ entity.getComment_id()+" and id="+entity.getId());
    }
    /*
     * Name        : deleteLikes
     * Author      : MinSeok Choi
     * Date        : 2023-11-25
     * argument    : List<Comment_likes>
     * return      : void
     * description : delete all comment_likes by commentId
     */
    public void deleteLikes(List<Comment_likes> entity) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        if(entity == null)  return;
        if(entity.size() == 0)
            return;
        Statement stmt = con.createStatement();
        stmt.executeUpdate("delete from comment_likes where comment_id="+entity.get(0).getComment_id());
    }

}
