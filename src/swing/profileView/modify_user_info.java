package swing.profileView;

import entity.UserInfo;
import jdbc.ConnectionManager;
import service.UserInfoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class modify_user_info extends JFrame {
    private final UserInfoService userinfoservice = new UserInfoService();
    private JTextField profileImageField, introField, jobField, emailField, phoneField;
    private JButton updateButton;
    private Connection instagram; // 인스타그램 데이터베이스 연결 객체
    public modify_user_info(Connection conn) {
        this.instagram = conn; // 인스타그램 데이터베이스 연결 객체 받아오기
        UserInfo users = new UserInfo(1,"images/instagram.png", "awf", "afewa", "awfd","afwaf");
        setTitle("Instagram User Info Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        JLabel profileImageLabel = new JLabel("Profile Image URL:");
        profileImageField = new JTextField();
        add(profileImageLabel);
        add(profileImageField);

        JLabel introLabel = new JLabel("Intro:");
        introField = new JTextField();
        add(introLabel);
        add(introField);

        JLabel jobLabel = new JLabel("Job:");
        jobField = new JTextField();
        add(jobLabel);
        add(jobField);

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        add(emailLabel);
        add(emailField);

        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField();
        add(phoneLabel);
        add(phoneField);

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateUserInfo();
            }
        });
        add(updateButton);

        pack();
        setVisible(true);
    }

    private void updateUserInfo() {
        try {
            String profile_Image = profileImageField.getText();
            String intro = introField.getText();
            String job = jobField.getText();
            String email = emailField.getText();
            String phone = phoneField.getText();

            // 인스타그램 데이터베이스 연결 객체를 통해 정보 수정
            String updateQuery = "UPDATE user_info SET profile_Image=?, intro=?, job=?, email=?, phone=? WHERE user_id=?";
            PreparedStatement preparedStatement = instagram.prepareStatement(updateQuery);

            // 각각의 필드에 해당하는 값을 설정
            preparedStatement.setString(1, profile_Image);
            preparedStatement.setString(2, intro);
            preparedStatement.setString(3, job);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, phone);
            preparedStatement.setInt(6, 1); // 유저 ID를 설정


            // 쿼리 실행
            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("User information updated successfully!");
            } else {
                System.out.println("Failed to update user information.");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // 인스타그램 데이터베이스 연결 객체 받아오기
        Connection conn = ConnectionManager.getConnection(); // 연결된 인스타그램 데이터베이스 연결 객체를 가져오는 메서드 호출
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new modify_user_info(conn);
            }
        });
    }
}

