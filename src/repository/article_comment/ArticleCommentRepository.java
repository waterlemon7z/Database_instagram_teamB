package repository.article_comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import jdbc.ConnectionManager;

public class ArticleCommentRepository
{
     /*
     * Name        : connectArticleComment
     * Author      : 이정대
     * Date        : 23/12/05
     * argument    : article's id, comment's id
     * return      : 없습니다
     * description : 데이터베이스의 article_comment 테이블에 게시글의 id와 댓글의 id를 추가합니다
     */
    public void connectArticleComment(int article_id, int comment_id) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = null;
        if(article_id == comment_id) return;
        try{
            String connect = "insert into article_comment value (?, ?)";
            pstmt = con.prepareStatement(connect);

            pstmt.setInt(1, article_id);
            pstmt.setInt(2, comment_id);

            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     /*
     * Name        : disconnectArticleComment
     * Author      : 이정대
     * Date        : 23/12/05
     * argument    : article's id, comment's id
     * return      : 없습니다
     * description : 데이터베이스의 article_comment 테이블에서 해당하는 게시글의 id와 댓글의 id를 제거합니다
     */
    public void disconnectArticleComment(int article_id, int comment_id) throws SQLException
    {
        Connection con = ConnectionManager.getCon();
        PreparedStatement pstmt = null;
        if(article_id == comment_id) return;
        try{
            String connect = "delete from article_comment where article_id = ? and comment_id = ?";
            pstmt = con.prepareStatement(connect);

            pstmt.setInt(1, article_id);
            pstmt.setInt(2, comment_id);

            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     /*
     * Name        : findByArticleId
     * Author      : 이정대
     * Date        : 23/12/05
     * argument    : article's id
     * return      : 해당 게시글에 달린 댓글들의 id로 List를 만들어 반환합니다
     * description : 특정 게시글에 달린 댓글들을 데이터베이스의 article_comment 테이블에서 게시글 id를 통해 검색합니다
     */
    public List<Integer> findByArticleId(int article_id) throws SQLException
    {
        List<Integer> rst = new ArrayList<>();
        Connection con = ConnectionManager.getCon();
        Statement stmt = con.createStatement();
        ResultSet resultSet = stmt.executeQuery("select * from article_comment where article_id=" + article_id);
        while(resultSet.next())
        {
            rst.add(resultSet.getInt(2));
        }
        return rst;
    }
}
