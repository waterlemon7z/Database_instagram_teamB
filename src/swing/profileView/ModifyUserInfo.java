package swing.profileView;

import entity.UserInfo;
import jdbc.ConnectionManager;
import service.UserInfoService;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyUserInfo extends JFrame
{
    private final UserInfoService userinfoservice = new UserInfoService();
    private final JScrollPane profileImageField;
    private final JScrollPane introField;
    private final JScrollPane jobField;
    private final JScrollPane emailField;
    private final JScrollPane phoneField;
    private final JButton updateButton;

    public ModifyUserInfo(int loginId)
    {
        UserInfo curUserInfo = userinfoservice.getUserInfo(loginId);
        setTitle("Instagram User Info Editor");
//        setLayout(new GridLayout(6, 2));
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        // 각 필드의 배경색을 흰색으로 설정
        Color whiteColor = new Color(255, 255, 255);

//        Color backgroundColor = Color.WHITE;
//        UIManager.put("Panel.background", new ColorUIResource(backgroundColor));
        profileImageField = createTextArea(curUserInfo.getProfileImage());
        mainPanel.add(createLabeledPanel("Profile Image URL: ", profileImageField));
        introField = createTextArea(curUserInfo.getIntro());
        mainPanel.add(createLabeledPanel("Intro:                     ", introField));
        jobField = createTextArea(curUserInfo.getJob());
        mainPanel.add(createLabeledPanel("Job:                        ", jobField));
        emailField = createTextArea(curUserInfo.getEmail());
        mainPanel.add(createLabeledPanel("Email:                    ", emailField));
        phoneField = createTextArea(curUserInfo.getMobile());
        mainPanel.add(createLabeledPanel("Phone:                   ", phoneField));

        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                JTextArea profileImageTextArea = (JTextArea) profileImageField.getViewport().getView();
                JTextArea introTextArea = (JTextArea) introField.getViewport().getView();
                JTextArea jobTextArea = (JTextArea) jobField.getViewport().getView();
                JTextArea emailTextArea = (JTextArea) emailField.getViewport().getView();
                JTextArea phoneTextArea = (JTextArea) phoneField.getViewport().getView();

                String profile_Image = profileImageTextArea.getText();
                String intro = introTextArea.getText();
                String job = jobTextArea.getText();
                String email = emailTextArea.getText();
                String mobile = phoneTextArea.getText();
                UserInfo userInfo = new UserInfo(loginId, profile_Image, intro, job, email, mobile);
                userinfoservice.updateUserInfo(userInfo);
                dispose();
            }
        });
        mainPanel.add(updateButton);

        // 창 크기를 400x600으로 고정
        setSize(400, 600);
        setResizable(false);

        // 창 배경색을 흰색으로 설정
        getContentPane().setBackground(whiteColor);

        add(mainPanel);
        setLocationRelativeTo(null); // 화면 중앙에 표시
        setVisible(true);
    }

    private JScrollPane createTextArea(String str)
    {
        JTextArea textArea = new JTextArea();
        textArea.setColumns(20);
        textArea.setRows(7);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText(str);
        return new JScrollPane(textArea);
    }

    private JPanel createLabeledPanel(String label, JComponent component)
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JLabel labelComponent = new JLabel(label);
        panel.add(labelComponent);
        panel.add(component);
        return panel;
    }

    public static void main(String[] args)
    {
        // 인스타그램 데이터베이스 연결 객체 받아오기
        ConnectionManager.getConnection();
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new ModifyUserInfo(1);
            }
        });
    }
}
