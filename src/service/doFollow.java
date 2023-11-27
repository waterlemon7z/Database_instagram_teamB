package service;

import jdbc.ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import exception.EntityInvalidException;


public class doFollow
{
    public static void main(String[] args) throws SQLException
    {
        // 데이터베이스 연결 정보
        /*url, user, password: 데이터베이스에 연결하기 위한 정보를 설정
        합니다. 이 정보는 각자의 데이터베이스 연결 
        정보로 수정되어야 합니다. */

        Connection con = ConnectionManager.getConnection();

        PreparedStatement pstmt = null;
        try
        {
            int followIdToInsert = 111;
            int followeeIdToInsert = 222;

            String addFollow = "insert into follow values (?, ?)";
            pstmt = con.prepareStatement(addFollow);

            pstmt.setInt(1, followIdToInsert);
            pstmt.setInt(2, followeeIdToInsert);

            int rowsAffected = pstmt.executeUpdate();

            System.out.println(rowsAffected);
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        // // 데이터베이스 연결

        // try (Connection connection = DriverManager.getConnection(url, user, password)) {
        //     System.out.println("Connected to the database.");

        //     // 데이터베이스에 삽입할 데이터
        //     int follow_id = 111;
        //     int followee_id = 222;

        //     // SQL 쿼리 작성
        //     /*sql: 데이터를 삽입할 테이블의 이름과 필드를 지정한 SQL 쿼리를 작성합니다. */
        //     

        //     // PreparedStatement를 사용하여 SQL 쿼리 실행
        //     /*PreparedStatement: SQL 쿼리의 placeholder에 값을 채우기 위해 사용됩니다.
        //      이렇게 사용하면 SQL 인젝션 공격을 방지할 수 있습니다. */
        //     try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        //         // SQL 쿼리의 placeholder(?)에 값 채우기
        //         preparedStatement.setInt(1, follow_id);
        //         preparedStatement.setInt(2, followee_id);

        //         // 쿼리 실행 및 데이터베이스에 데이터 삽입
        //         /*executeUpdate(): INSERT, UPDATE, DELETE와
        //          같은 데이터 변경 쿼리를 실행하고 영향을 받은 행의 수를 반환합니다. */
        //         int rowsAffected = preparedStatement.executeUpdate();

        //         System.out.println(rowsAffected + " row(s) affected.");
        //     }
        // } catch (SQLException e) {
        //     e.printStackTrace();
        // }

    }
}
