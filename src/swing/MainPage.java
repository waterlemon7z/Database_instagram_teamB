package swing;

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

import entity.Article.Article;
import jdbc.ConnectionManager;
import service.ArticleService;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.plaf.PanelUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;


public class MainPage extends JFrame
{
    private JButton userIdButton; // 변경된 부분: JLabel 대신 JButton 사용
    private JButton likeButton;
    private JButton commentButton;
    private String loginUser;
    private int loginId;
    private ArticleService articleService = new ArticleService();

    public MainPage(String userId)
    {
        this.loginUser = userId;
        this.loginId = 2;
        setTitle("Instagram");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 800);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        userIdButton = new JButton(loginUser);
        userIdButton.setFocusPainted(false); // 버튼의 Focus Paint 제거 (스타일링 목적)
        userIdButton.setBorderPainted(false); // 버튼의 Border Paint 제거 (스타일링 목적)
        userIdButton.setContentAreaFilled(false); // 버튼의 Content 영역 채우기 제거 (스타일링 목적)
        userIdButton.setForeground(Color.BLUE); // 버튼 텍스트 색상 변경
        userIdButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 커서를 손가락 형태로 변경
        userIdButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // user id click 하면 재현 오빠가 만든 유저페이지 메인화면으로 이동하는거
                JOptionPane.showMessageDialog(null, "User ID Clicked!");
            }
        });
        panel.add(userIdButton, BorderLayout.NORTH);

        List<Article> articles = articleService.searchById(1);
        ScrollPane scrollPane = new ScrollPane();

        JEditorPane jTextPane = new JEditorPane();
        jTextPane.setEditable(false);
        jTextPane.setContentType("text/html");
        jTextPane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
        jTextPane.setEnabled(true);
        String html = "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body, h1, h2, h3, h4, h5, h6, p, span \n" +
                "        {\n" +
                "            font-family: '.AppleSystemUIFont',serif !important;\n" +
                "        }\n" +
                "        p\n" +
                "        {\n" +
                "            margin-left: 10px;\n" +
                "        }\n" +
                "        .comment a\n" +
                "        {\n" +
                "            text-decoration: none;\n" +
                "            color: black;\n" +
                "            "+
                "        }" +
                "        .comment\n" +
                "        {\n" +
                "            margin-left: 10px;\n" +
                "        }" +
                "    </style>\n" +
                "</head>";
        for (Article iter : articles)
        {
            html += "<p style=\"font-size:15px;\"><b>" + "유저네임으로 대체필요" + iter.getId() + " </b></p>\n" +
                    "<hr>\n" +
                    "<img src=\"" + iter.getImage().get(0).getImage() + "\" width=\"380\">\n" +
                    "<div class=\"comment\"> " +
                        "<span><b>" + "유저네임으로 대체필요" + iter.getId() + " </b>" + iter.getContent() +
                        "<br><a href=\"comment/" + iter.getArticle_id() + "\">Show Comments..</a></span>" +
                    "</div>";
        }
        jTextPane.setText(html);
        jTextPane.setCaretPosition(0);
        jTextPane.addHyperlinkListener(new HyperlinkListener()
        {
            public void hyperlinkUpdate(HyperlinkEvent e)
            {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                {
                    if (e.getDescription().contains("comment"))
                    {
//                        System.out.println("OK");
                        int article_id = Integer.parseInt(e.getDescription().substring(8));
                        System.out.println(article_id);
                        new ShowComment(article_id, loginId);
                    }
                }
            }
        });

        panel.add(new JScrollPane(jTextPane), BorderLayout.CENTER);

//        JPanel buttonPanel = new JPanel();
//        likeButton = new JButton("Like");
//        commentButton = new JButton("Comment");
//        commentButton.addActionListener(new ActionListener()
//        {
//            @Override
//            public void actionPerformed(ActionEvent e)
//            {
//                // Handle comment button click event here
//                JFrame commentFrame = new JFrame("Comments");
//                commentFrame.setSize(300, 200);
//
//                JTextArea commentArea = new JTextArea();
//                JScrollPane commentScrollPane = new JScrollPane(commentArea);
//
//                commentFrame.add(commentScrollPane);
//                commentFrame.setVisible(true);
//            }
//        });
//        buttonPanel.add(likeButton);
//        buttonPanel.add(commentButton);
//        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        ConnectionManager.getConnection();
        SwingUtilities.invokeLater(() ->
        {
            new MainPage("lemon7z_");
        });
    }
}
