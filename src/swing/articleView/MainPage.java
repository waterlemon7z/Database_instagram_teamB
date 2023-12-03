package swing.articleView;

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
import entity.Article.Article_hashtag;
import entity.Follow;
import entity.User;
import entity.UserInfo;
import jdbc.ConnectionManager;
import service.*;
import swing.profileView.InstagramProfileGUI;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class MainPage extends JFrame
{
    private final int loginId;
    private final ArticleService articleService = new ArticleService();
    private final FollowService followService = new FollowService();
    private final CommentService commentService = new CommentService();
    private final UserService userService = new UserService();
    private final UserInfoService userInfoService = new UserInfoService();
    private final JEditorPane jTextPane = new JEditorPane();

    public MainPage(int userId)
    {
        loginId = userId;
        setTitle("Instagram");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 800);

        Color backgroundColor = Color.WHITE;
        UIManager.put("Panel.background", new ColorUIResource(backgroundColor));
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(-1));
        JPanel searchBarPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchBarPanel.add(searchField);
        searchBarPanel.add(searchButton);

        searchButton.addActionListener(e ->
        {
            String searchText = searchField.getText();
            if(searchText.contains("#"))
            {
                List<Article> articles = articleService.searchByHashtag(searchText);
                new ShowArticle(loginId, articles);
            }
            else
            {
                User searchUser = userService.getUserByUserId(searchText);
                if (searchUser == null)
                {
                    JOptionPane.showMessageDialog(null, searchText + " : 존재하지 않는 유저");
                } else
                {
                    new InstagramProfileGUI(loginId, searchUser.getId());
                }
            }

        });

        JPanel topBtnPanel = new JPanel();
        topBtnPanel.setLayout(new BorderLayout());
        topBtnPanel.setBackground(new Color(-1));
        topBtnPanel.add(searchBarPanel,BorderLayout.SOUTH);
        //유저페이지 및 현제 로그인된 아이디
        JButton userIdButton = new JButton(userService.getUserById(loginId).getUser_id());
        userIdButton.setFocusPainted(false); // 버튼의 Focus Paint 제거 (스타일링 목적)
        userIdButton.setBorderPainted(false); // 버튼의 Border Paint 제거 (스타일링 목적)
        userIdButton.setContentAreaFilled(false); // 버튼의 Content 영역 채우기 제거 (스타일링 목적)
        userIdButton.setForeground(Color.BLACK); // 버튼 텍스트 색상 변경
        userIdButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 커서를 손가락 형태로 변경
        userIdButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new InstagramProfileGUI(loginId, loginId);
            }
        });
        topBtnPanel.add(userIdButton, BorderLayout.WEST);

        //새글 작성
        JButton newArticleBtn = new JButton("새글 작성");
        newArticleBtn.setFocusPainted(false); // 버튼의 Focus Paint 제거 (스타일링 목적)
        newArticleBtn.setBorderPainted(false); // 버튼의 Border Paint 제거 (스타일링 목적)
        newArticleBtn.setContentAreaFilled(false); // 버튼의 Content 영역 채우기 제거 (스타일링 목적)
        newArticleBtn.setForeground(Color.BLACK); // 버튼 텍스트 색상 변경
        newArticleBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 커서를 손가락 형태로 변경
        newArticleBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new NewArticle(loginId);
            }
        });
        topBtnPanel.add(newArticleBtn, BorderLayout.EAST);

        JButton refreshBtn = new JButton("새로고침");
        refreshBtn.setFocusPainted(false); // 버튼의 Focus Paint 제거 (스타일링 목적)
        refreshBtn.setBorderPainted(false); // 버튼의 Border Paint 제거 (스타일링 목적)
        refreshBtn.setContentAreaFilled(false); // 버튼의 Content 영역 채우기 제거 (스타일링 목적)
        refreshBtn.setForeground(Color.BLACK); // 버튼 텍스트 색상 변경
        refreshBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // 커서를 손가락 형태로 변경
        refreshBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                refresh();
            }
        });
        topBtnPanel.add(refreshBtn, BorderLayout.CENTER);

        panel.add(topBtnPanel, BorderLayout.NORTH);

        jTextPane.setEditable(false);
        jTextPane.setContentType("text/html");
        jTextPane.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
        jTextPane.setEnabled(true);
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
                    if (e.getDescription().contains("delete"))
                    {
//                        System.out.println("OK");
                        int article_id = Integer.parseInt(e.getDescription().substring(7));
                        System.out.println(article_id);
                        commentService.removeCommentByArticleId(article_id);
                        Article article = articleService.searchByArticleId(article_id);
                        articleService.removeArticle(article);
                        System.out.println("delete article : " + article_id);
                        refresh();
                    }
                    if (e.getDescription().contains("like"))
                    {
//                        System.out.println("OK");
                        int article_id = Integer.parseInt(e.getDescription().substring(5));
                        System.out.println(article_id);
                        articleService.likeSwitcher(article_id, loginId);
                        System.out.println("delete article : " + article_id);
                        refresh();
                    }
                    if (e.getDescription().contains("profile"))
                    {
//                        System.out.println("OK");
                        int userId = Integer.parseInt(e.getDescription().substring(8));
                        System.out.println(userId);
                        new InstagramProfileGUI(loginId, userId);
                    }
                }
            }
        });
        refresh();
        panel.add(new JScrollPane(jTextPane), BorderLayout.CENTER);
        add(panel);
        setVisible(true);
    }

    void refresh()
    {
        List<Follow> follows = followService.searchByFollow(loginId);
//        follows.add(new Follow(loginId,loginId));
        List<Article> articles = new ArrayList<>();
        for (Follow iter : follows)
        {
            articles.addAll(articleService.searchById(iter.getFollowee_id()));
        }
        String html = "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        body, h1, h2, h3, h4, h5, h6, p, span \n" +
                "        {\n" +
                "            font-family: '.AppleSystemUIFont',serif !important;\n" +
                "        }\n" +
//                "        p\n" +
//                "        {\n" +
//                "            margin-left: 10px;\n" +
//                "        }\n" +
                "        .comment a\n" +
                "        {\n" +
                "            text-decoration: none;\n" +
                "            margin-bottom: 10px;\n" +
                "            color: black;\n" +
                "            " +
                "        }" +
                "        a\n" +
                "        {\n" +
                "            text-decoration: none;\n" +
                "            color: black;\n" +
                "            " +
                "        }" +
                "        .comment\n" +
                "        {\n" +
                "            margin-left: 10px;\n" +
                "        }" +
                "    </style>\n" +
                "</head>";
        Collections.sort(articles, new ArticleComparator());

        for (Article iter : articles)
        {
            List<Article_hashtag> hashtag = iter.getHashtag();
            UserInfo userInfo = userInfoService.getUserInfo(iter.getId());
            String hashtagString = "";
            for (Article_hashtag hash : hashtag)
            {
                hashtagString += hash.getHashtag();
            }
            html += "<table>\n" +
                    "    <tr>\n" +
                    "        <td><img src=" + (userInfo.getProfileImage().equals("") ? "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Default_pfp.svg/680px-Default_pfp.svg.png?20220226140232" : userInfo.getProfileImage()) + " alt=\"Your Image\" width=40></td>\n" +
                    "        <td><p style=\"font-size:15px;\"><b><a href=\"profile/" + iter.getId() + "\">" + userService.getUserById(iter.getId()).getUser_id() + "</a></b></p>\n</td>\n" +
                    "        <td align=\"right\">" + (iter.getId() == loginId ? "     <a href=\"delete/" + iter.getArticle_id() + "\">삭제</a>" : "") + "\n</td>\n" +
                    "    </tr>\n" +
                    "</table>" +
                    "<hr>\n" +
                    "<img src=\"" + (iter.getImage().size() == 0 ? "http://www.durc.kr/img/sub/noimage.png" : iter.getImage().get(0).getImage()) + "\" width=\"380\">\n" +
                    "<div class=\"comment\"> " +
                    "<span><b><a href=\"like/"+iter.getArticle_id()+"\">좋아요 " + iter.getLikes().size()+ "개</a></b></span>"+
                    "<br><span><b>" + userService.getUserById(iter.getId()).getUser_id() + " </b>" + iter.getContent() +
                    (hashtagString.equals("") ? "" : "<br>" + hashtagString) +
                    "<br><a href=\"comment/" + iter.getArticle_id() + "\">Show " + commentService.searchByArticleId(iter.getArticle_id()).size() + " Comments..</a></span>" +
                    "<br><font color=\"gray\">" + iter.getDate().getYear() + "." + iter.getDate().getMonthValue() + "." + iter.getDate().getDayOfMonth() + " " +
                    iter.getDate().getHour() + "시 " + iter.getDate().getMinute() + "분</font>" +
                    "</div>";
        }
        jTextPane.setText(html);
        jTextPane.setCaretPosition(0);
    }

    static class ArticleComparator implements Comparator<Article>
    {
        @Override
        public int compare(Article o1, Article o2)
        {
            return o2.getDate()
                    .compareTo(o1.getDate());
        }
    }

    public static void main(String[] args)
    {
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 12)); // 적절한 폰트로 변경


        ConnectionManager.getConnection();
        SwingUtilities.invokeLater(() ->
        {
            new MainPage(1);
        });
    }
}

