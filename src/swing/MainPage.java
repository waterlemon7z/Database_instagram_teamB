package swing;

//public class TestFrm extends JFrame {
//
//    public TestFrm(){
//        setTitle("Test Frame");
//        setSize(600, 400);
//        centerFrameOnScreen(this); // â�� ȭ�� �߾ӿ� ��ġ�ϴ� �޼��� ȣ��
//        setVisible(true);
//    }
//
//    // â�� ȭ�� �߾ӿ� ��ġ�ϴ� �޼���
//    private void centerFrameOnScreen(JFrame frame) {
//        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); // ȭ�� ũ�� ��������
//        int centerX = (screenSize.width - frame.getWidth()) / 2; // ȭ�� ���� �߾� ��ġ ���
//        int centerY = (screenSize.height - frame.getHeight()) / 2; // ȭ�� ���� �߾� ��ġ ���
//        frame.setLocation(centerX, centerY); // â�� ȭ�� �߾����� �̵�
//    }
//}

import entity.Article.Article;
import entity.Article.Article_hashtag;
import entity.Follow;
import jdbc.ConnectionManager;
import service.ArticleService;
import service.CommentService;
import service.FollowService;
import service.UserService;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;


public class MainPage extends JFrame
{
    private final int loginId;
    private final ArticleService articleService = new ArticleService();
    private final FollowService followService = new FollowService();
    private final CommentService commentService = new CommentService();
    private final UserService userService = new UserService();
    private final JEditorPane jTextPane = new JEditorPane();

    public MainPage(int userId)
    {
        loginId = userId;
        setTitle("Instagram");
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 800);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(-1));


        JPanel topBtnPanel = new JPanel();
        topBtnPanel.setLayout(new BorderLayout());
        topBtnPanel.setBackground(new Color(-1));
        //���������� �� ���� �α��ε� ���̵�
        JButton userIdButton = new JButton(userService.getUserById(loginId).getUser_id());
        userIdButton.setFocusPainted(false); // ��ư�� Focus Paint ���� (��Ÿ�ϸ� ����)
        userIdButton.setBorderPainted(false); // ��ư�� Border Paint ���� (��Ÿ�ϸ� ����)
        userIdButton.setContentAreaFilled(false); // ��ư�� Content ���� ä��� ���� (��Ÿ�ϸ� ����)
        userIdButton.setForeground(Color.BLACK); // ��ư �ؽ�Ʈ ���� ����
        userIdButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Ŀ���� �հ��� ���·� ����
        userIdButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // user id click �ϸ� ���� ������ ���� ���������� ����ȭ������ �̵��ϴ°�
                JOptionPane.showMessageDialog(null, "User ID Clicked!");
            }
        });
        topBtnPanel.add(userIdButton, BorderLayout.WEST);

        //���� �ۼ�
        JButton newArticleBtn = new JButton("���� �ۼ�");
        newArticleBtn.setFocusPainted(false); // ��ư�� Focus Paint ���� (��Ÿ�ϸ� ����)
        newArticleBtn.setBorderPainted(false); // ��ư�� Border Paint ���� (��Ÿ�ϸ� ����)
        newArticleBtn.setContentAreaFilled(false); // ��ư�� Content ���� ä��� ���� (��Ÿ�ϸ� ����)
        newArticleBtn.setForeground(Color.BLACK); // ��ư �ؽ�Ʈ ���� ����
        newArticleBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Ŀ���� �հ��� ���·� ����
        newArticleBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                new NewArticle(loginId);
            }
        });
        topBtnPanel.add(newArticleBtn, BorderLayout.EAST);

        JButton refreshBtn = new JButton("���ΰ�ħ");
        refreshBtn.setFocusPainted(false); // ��ư�� Focus Paint ���� (��Ÿ�ϸ� ����)
        refreshBtn.setBorderPainted(false); // ��ư�� Border Paint ���� (��Ÿ�ϸ� ����)
        refreshBtn.setContentAreaFilled(false); // ��ư�� Content ���� ä��� ���� (��Ÿ�ϸ� ����)
        refreshBtn.setForeground(Color.BLACK); // ��ư �ؽ�Ʈ ���� ����
        refreshBtn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Ŀ���� �հ��� ���·� ����
        refreshBtn.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                refresh();
            }
        });
        topBtnPanel.add(refreshBtn, BorderLayout.CENTER);

        panel.add(topBtnPanel,BorderLayout.NORTH);

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
                    }if (e.getDescription().contains("delete"))
                    {
//                        System.out.println("OK");
                        int article_id = Integer.parseInt(e.getDescription().substring(7));
                        System.out.println(article_id);
                        commentService.removeCommentByArticleId(article_id);
                        Article article = articleService.searchByArticleId(article_id);
                        articleService.removeArticle(article);
                        System.out.println("delete article : "+ article_id);
                        refresh();
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
        follows.add(new Follow(loginId,loginId));
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
        Collections.sort(articles,new ArticleComparator());

        for (Article iter : articles)
        {
            List<Article_hashtag> hashtag = iter.getHashtag();
            String hashtagString= "";
            for(Article_hashtag hash : hashtag)
            {
                hashtagString+= hash.getHashtag();
            }
            html += "<table>\n" +
                    "    <tr>\n" +
                    "        <td><img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Default_pfp.svg/680px-Default_pfp.svg.png?20220226140232\" alt=\"Your Image\" width=40></td>\n" +
                    "        <td><p style=\"font-size:15px;\"><b>"+userService.getUserById(iter.getId()).getUser_id() + "</b></p>\n</td>\n" +
                    "        <td align=\"right\">"+(iter.getId() == loginId ? "     <a href=\"delete/" + iter.getArticle_id() + "\">����</a>" : "") + "\n</td>\n" +
                    "    </tr>\n" +
                    "</table>" +
                    "<hr>\n" +
                    "<img src=\"" + (iter.getImage().size() == 0 ? "http://www.durc.kr/img/sub/noimage.png" : iter.getImage().get(0).getImage()) + "\" width=\"380\">\n" +
                    "<div class=\"comment\"> " +
                    "<span><b>" + userService.getUserById(iter.getId()).getUser_id() + " </b>" + iter.getContent() +
                    (hashtagString.equals("") ? "" : "<br>"+hashtagString)+
                    "<br><a href=\"comment/" + iter.getArticle_id() + "\">Show "+commentService.searchByArticleId(iter.getArticle_id()).size()+" Comments..</a></span>" +
                    "<br><font color=\"gray\">"+iter.getDate().getYear()+"."+iter.getDate().getMonthValue()+"."+iter.getDate().getDayOfMonth()+ " "+
                    iter.getDate().getHour() +"�� "+ iter.getDate().getMinute()+"��</font>"+
                    "</div>";
        }
        jTextPane.setText(html);
        jTextPane.setCaretPosition(0);
    }
    static class ArticleComparator implements Comparator<Article>
    {
        @Override
        public  int compare(Article o1, Article o2) {
            return o2.getDate()
                    .compareTo(o1.getDate());
        }
    }
    public static void main(String[] args)
    {
        UIManager.put("Label.font", new Font("Arial", Font.PLAIN, 12)); // ������ ��Ʈ�� ����


        ConnectionManager.getConnection();
        SwingUtilities.invokeLater(() ->
        {
            new MainPage(1);
        });
    }
}

