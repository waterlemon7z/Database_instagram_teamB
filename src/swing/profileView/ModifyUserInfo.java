package swing.profileView;

import entity.UserInfo;
import jdbc.ConnectionManager;
import service.UserInfoService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ModifyUserInfo extends JFrame {
    private final UserInfoService userinfoservice = new UserInfoService();
    private final JTextField profileImageField;
    private final JTextField introField;
    private final JTextField jobField;
    private final JTextField emailField;
    private final JTextField phoneField;
    private final JButton updateButton;

    public ModifyUserInfo(int loginId) {
        UserInfo curUserInfo = userinfoservice.getUserInfo(loginId);
        setTitle("Instagram User Info Editor");
        setLayout(new GridLayout(6, 2));

        JLabel profileImageLabel = new JLabel("Profile Image URL:");
        profileImageField = new JTextField();
        add(profileImageLabel);
        add(profileImageField);
        profileImageField.setText(curUserInfo.getProfileImage());

        JLabel introLabel = new JLabel("Intro:");
        introField = new JTextField();
        add(introLabel);
        add(introField);
        introField.setText(curUserInfo.getIntro());

        JLabel jobLabel = new JLabel("Job:");
        jobField = new JTextField();
        add(jobLabel);
        add(jobField);
        jobField.setText(curUserInfo.getJob());

        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        add(emailLabel);
        add(emailField);
        emailField.setText(curUserInfo.getEmail());

        JLabel phoneLabel = new JLabel("Phone:");
        phoneField = new JTextField();
        add(phoneLabel);
        add(phoneField);
        phoneField.setText(curUserInfo.getMobile());

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String profile_Image = profileImageField.getText();
                String intro = introField.getText();
                String job = jobField.getText();
                String email = emailField.getText();
                String mobile = phoneField.getText();
                UserInfo userInfo = new UserInfo(loginId, profile_Image,intro, job, email, mobile);
                userinfoservice.updateUserInfo(userInfo);
                dispose();
            }
        });
        add(updateButton);

        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        // 인스타그램 데이터베이스 연결 객체 받아오기
        ConnectionManager.getConnection();
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ModifyUserInfo(1);
            }
        });
    }
}

