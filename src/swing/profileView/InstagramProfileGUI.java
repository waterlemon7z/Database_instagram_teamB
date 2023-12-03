package swing.profileView;

import entity.Article.Article;
import entity.Follow;
import entity.User;
import entity.UserInfo;
import jdbc.ConnectionManager;
import service.ArticleService;
import service.FollowService;
import service.UserInfoService;
import service.UserService;
import swing.articleView.ShowArticle;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InstagramProfileGUI extends JFrame
{
    UserService userService = new UserService();
    UserInfoService userInfoService = new UserInfoService();
    ArticleService articleService = new ArticleService();
    FollowService followService = new FollowService();
    JPanel profilePanel = new JPanel(new BorderLayout());
    private final int curId;
    private final int loginId;

    public InstagramProfileGUI(int loginId, int idx)
    {
        try
        {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException |
                 InstantiationException e)
        {
            throw new RuntimeException(e);
        }
        Color backgroundColor = Color.WHITE;
        UIManager.put("Panel.background", new ColorUIResource(backgroundColor));
//        UIManager.put("Button.background", new ColorUIResource(backgroundColor));
//        UIManager.put("TextField.background", new ColorUIResource(backgroundColor));
        this.loginId = loginId;
        curId = idx;
        User curUser = userService.getUserById(idx);
        String userId = curUser.getUser_id();
        UserInfo curUserInfo = userInfoService.getUserInfo(idx);
        List<Article> curUserArticle = articleService.searchById(idx);
        List<Follow> follows = followService.searchByFollow(idx);
        List<Follow> followees = followService.searchByFollowee(idx);
        InstagramProfile instagramProfile = new InstagramProfile(idx, curUserInfo.getProfileImage(),
                userId, curUserInfo.getIntro(), curUserArticle.size(), followees.size(), follows.size());

        setTitle(curUser.getUser_id() + "'s Profile");
        setResizable(false);
        setLocationRelativeTo(null);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 700);

        JPanel searchBarPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchBarPanel.add(searchField);
        searchBarPanel.add(searchButton);

        searchButton.addActionListener(e ->
        {
            String searchText = searchField.getText();
            User searchUser = userService.getUserByUserId(searchText);
            if (searchUser == null)
            {
                JOptionPane.showMessageDialog(null, searchText + " : 존재하지 않는 유저");
            } else
            {
                new InstagramProfileGUI(loginId, searchUser.getId());
            }
        });


        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(searchBarPanel, BorderLayout.NORTH);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());


        JLabel profilePicture = new JLabel();
        loadImageFromInternet(profilePicture, curUserInfo.getProfileImage());
        profilePicture.setPreferredSize(new Dimension(110, 110));
        headerPanel.add(profilePicture);

        JPanel profileInfoPanel = new JPanel();
        profileInfoPanel.setLayout(new BoxLayout(profileInfoPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel(instagramProfile.getUsername());
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        profileInfoPanel.add(usernameLabel);

        JLabel bioLabel = new JLabel(instagramProfile.getIntro());
        profileInfoPanel.add(bioLabel);

        headerPanel.add(profileInfoPanel);

        topPanel.add(headerPanel, BorderLayout.CENTER);

        profilePanel.add(topPanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(1, 3));

        JPanel statItem1 = new JPanel();
        statItem1.setLayout(new BoxLayout(statItem1, BoxLayout.Y_AXIS));
        JLabel postsLabel = new JLabel("          Posts");
        JLabel postsCount = new JLabel("           " + String.valueOf(instagramProfile.getPostsCount()));
        statItem1.add(postsLabel);
        statItem1.add(postsCount);

        JPanel statItem2 = new JPanel();
        statItem2.setLayout(new BoxLayout(statItem2, BoxLayout.Y_AXIS));
        JLabel followersLabel = new JLabel("     Followers");
        JLabel followersCount = new JLabel("           " + String.valueOf(instagramProfile.getFollowersCount()));
        statItem2.add(followersLabel);
        statItem2.add(followersCount);

        JPanel statItem3 = new JPanel();
        statItem3.setLayout(new BoxLayout(statItem3, BoxLayout.Y_AXIS));
        JLabel followingLabel = new JLabel("     Following");
        JLabel followingCount = new JLabel("           " + String.valueOf(instagramProfile.getFollowingCount()));
        statItem3.add(followingLabel);
        statItem3.add(followingCount);

        statsPanel.add(statItem1);
        statsPanel.add(statItem2);
        statsPanel.add(statItem3);
        profilePanel.add(statsPanel, BorderLayout.CENTER);
        addImagePanel();

        add(profilePanel);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        ConnectionManager.getConnection();
        SwingUtilities.invokeLater(() ->
        {
            new InstagramProfileGUI(2,1);
        });
    }

    void addImagePanel()
    {
        List<Article> articles = articleService.searchById(curId);
//        List<String> imageUrls = new ArrayList<>();
        if (articles.size() == 0)
        {
            JLabel label = new JLabel("게시글이 없습니다.");
            profilePanel.add(label, BorderLayout.SOUTH);
            return;
        }
        Collections.reverse(articles);
        int numOfImages = articles.size();
        int rows = (int) Math.ceil((double) numOfImages / 3); // 이미지 수에 따른 행의 계산
        JPanel imagePanel = new JPanel(new GridLayout(0, 3, 0, 0)); // 그리드 레이아웃, 간격 조절
        for (Article iter : articles)
        {
            String imageUrl;
            if (iter.getImage().size() == 0)
            {
                imageUrl = "http://www.durc.kr/img/sub/noimage.png";
            } else
            {
                imageUrl = iter.getImage().get(0).getImage();
            }
            if (imageUrl.equals("")) imageUrl = "http://www.durc.kr/img/sub/noimage.png";
            try
            {
                URL url = new URL(imageUrl);
                ImageIcon icon = new ImageIcon(url);
                Image resizedImage
                        = icon.getImage().getScaledInstance(133, 133, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                JLabel label = new JLabel(resizedIcon);
                label.addMouseListener(new MouseAdapter()
                {
                    @Override
                    public void mouseClicked(MouseEvent e)
                    {
                        // 여기에 클릭 이벤트가 발생했을 때 수행할 동작을 작성
                        System.out.println(iter.getArticle_id() + "번 게시글 열기");
                        Article article = articleService.searchByArticleId(iter.getArticle_id());
                        List<Article> input = new ArrayList<>();
                        input.add(article);

                        new ShowArticle(loginId,input);
                    }
                });
                imagePanel.add(label);
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        JScrollPane scrollPane = new JScrollPane(imagePanel);
        scrollPane.setPreferredSize(new Dimension(400, 400));
        profilePanel.add(scrollPane, BorderLayout.SOUTH);
    }

    private void loadImageFromInternet(JLabel imageLabel, String url)
    {
        try
        {
            // 이미지를 가져올 URL 지정
            if (url.equals(""))
            {
                url = "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Default_pfp.svg/680px-Default_pfp.svg.png?20220226140232";
            }
            URL imageUrl = new URL(url);

            // URL에서 이미지 읽어오기
            Image image = ImageIO.read(imageUrl);
            image = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);

            // 이미지를 JLabel에 설정
            imageLabel.setIcon(new ImageIcon(image));


        } catch (IOException e)
        {
            System.out.println("이미지 오류");
        }
    }
}
