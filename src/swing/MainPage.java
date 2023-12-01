package loginTest;

//public class TestFrm extends JFrame {
//
//    public TestFrm(){
//        setTitle("Test Frame");
//        setSize(600, 400);
//        centerFrameOnScreen(this); // 창을 화면 중앙에 배치하는 메서드 호출
//        setVisible(true);
//    }
//
//    // 창을 화면 중앙에 배치하는 메서드
//    private void centerFrameOnScreen(JFrame frame) {
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // 화면 크기 가져오기
//        int centerX = (screenSize.width - frame.getWidth()) / 2; // 화면 가로 중앙 위치 계산
//        int centerY = (screenSize.height - frame.getHeight()) / 2; // 화면 세로 중앙 위치 계산
//        frame.setLocation(centerX, centerY); // 창을 화면 중앙으로 이동
//    }
//}

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MainPage extends JFrame {
    private JButton userIdButton; // 변경된 부분: JLabel 대신 JButton 사용
    private JTextArea articleTextArea;
    private JButton likeButton;
    private JButton commentButton;

    public MainPage() {
        setTitle("Instagram ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        userIdButton = new JButton("User ID");
        userIdButton.setFocusPainted(false); // 버튼의 Focus Paint 제거 (스타일링 목적)
        userIdButton.setBorderPainted(false); // 버튼의 Border Paint 제거 (스타일링 목적)
        userIdButton.setContentAreaFilled(false); // 버튼의 Content 영역 채우기 제거 (스타일링 목적)
        userIdButton.setForeground(Color.BLUE); // 버튼 텍스트 색상 변경
        userIdButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 커서를 손가락 형태로 변경
        userIdButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // user id click 하면 재현 오빠가 만든 유저페이지 메인화면으로 이동하는거
                JOptionPane.showMessageDialog(null, "User ID Clicked!");
            }
        });
        panel.add(userIdButton, BorderLayout.NORTH);

        articleTextArea = new JTextArea();
        articleTextArea.setEditable(false);
        articleTextArea.setLineWrap(true);

        // article 누르먄 새로운창이동 하게할거 일단 남겨둠
//        articleTextArea.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//
//                JFrame articleFrame = new JFrame("Article Details");
//                articleFrame.setSize(400,200);
//
//                JTextArea articleDetails = new JTextArea("Article content");
//                articleDetails.setEditable(false);
//                JScrollPane articleScrollPane = new JScrollPane(articleDetails);
//
//                articleFrame.add(articleScrollPane);
//                articleFrame.setVisible(true);
//            }
//        }

        JScrollPane scrollPane = new JScrollPane(articleTextArea);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        likeButton = new JButton("Like");
        commentButton = new JButton("Comment");
        commentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle comment button click event here
                JFrame commentFrame = new JFrame("Comments");
                commentFrame.setSize(300, 200);

                JTextArea commentArea = new JTextArea();
                JScrollPane commentScrollPane = new JScrollPane(commentArea);

                commentFrame.add(commentScrollPane);
                commentFrame.setVisible(true);
            }
        });
        buttonPanel.add(likeButton);
        buttonPanel.add(commentButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainPage();
        });
    }
}
