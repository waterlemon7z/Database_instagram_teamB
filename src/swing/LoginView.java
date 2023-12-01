package swing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame {

    private MainLogin main;
    private MainPage mainPage;

    private JButton btnLogin;
    private JButton btnInit;
    private JPasswordField passText;
    private JTextField userText;
    private boolean bLoginCheck;

    public static void main(String[] args) {
        // new LoginView();
    }

    public LoginView() {
        // setting
        setTitle("LOGIN");
        setSize(500, 700);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        // panel
        JPanel panel = new JPanel();
        placeLoginPanel(panel);

        // add
        add(panel);

        // visible
        setVisible(true);
    }

    public void placeLoginPanel(JPanel panel){
        panel.setLayout(new GridBagLayout());
        panel.setBackground(new Color(-1));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        // 이미지 크기 조정
        ImageIcon imageIcon = new ImageIcon("images/instagram.png");
        Image image = imageIcon.getImage();
        Image scaledImage = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH); // 이미지 크기 조정
        ImageIcon scaledImageIcon = new ImageIcon(scaledImage);

        // 이미지를 라벨에 추가
        JLabel imageLabel = new JLabel(scaledImageIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(imageLabel, gbc);

        // 나머지 컴포넌트들 추가
        JLabel userLabel = new JLabel("User");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(userLabel, gbc);

        JLabel passLabel = new JLabel("Pass");
        gbc.gridy = 2;
        panel.add(passLabel, gbc);

        userText = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(userText, gbc);

        passText = new JPasswordField(20);
        gbc.gridy = 2;
        panel.add(passText, gbc);
        passText.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });

        btnInit = new JButton("Reset");
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(btnInit, gbc);
        btnInit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userText.setText("");
                passText.setText("");
            }
        });

        btnLogin = new JButton("Login");
        gbc.gridx = 1;
        panel.add(btnLogin, gbc);
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                isLoginCheck();
            }
        });
    }

    public void isLoginCheck(){
        if(userText.getText().equals("test") && new String(passText.getPassword()).equals("1234")){
            JOptionPane.showMessageDialog(null, "Success");
            bLoginCheck = true;

            // 로그인 성공이라면 매니져창 뛰우기
            if(isLogin()){
                main.showFrameTest(userText.getText()); // 메인창 메소드를 이용해 창뛰우기
            }
        }else{
            JOptionPane.showMessageDialog(null, "Failed");
        }
    }

    // mainProcess와 연동
    public void setMain(MainLogin main) {
        this.main = main;
    }

    public boolean isLogin() {
        return bLoginCheck;
    }
}
