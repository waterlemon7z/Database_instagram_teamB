package swing.profileView;

import entity.Article.Article;
import service.ArticleService;
import swing.profileView.InstagramProfile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class InstagramProfileGUI extends JFrame {
    private List<InstagramProfile> instagramProfiles;

    public InstagramProfileGUI(InstagramProfile profile) {
        this.instagramProfiles = new ArrayList<>(); // 리스트 초기화

        // 전달된 프로필을 리스트에 추가
        this.instagramProfiles.add(profile);

        setTitle("Instagram Profile");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);

        JPanel searchBarPanel = new JPanel();
        JTextField searchField = new JTextField(20);
        JButton searchButton = new JButton("Search");

        searchBarPanel.add(searchField);
        searchBarPanel.add(searchButton);

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            int searchIndex = Integer.parseInt(searchText);

            boolean profileFound = false;

            for (InstagramProfile profile1 : instagramProfiles) {
                if (profile1.getIndex() == searchIndex) {
                    JOptionPane.showMessageDialog(null, "프로필 찾음!\n사용자 이름: " + profile.getUsername() + "\n바이오: " + profile.getBioLabel());
                    profileFound = true;
                    new InstagramProfileGUI(profile);
                    break;
                }
            }

            if (!profileFound) {
                JOptionPane.showMessageDialog(null, "해당 인덱스의 프로필을 찾을 수 없습니다.");
            }
        });



        JPanel profilePanel = new JPanel(new BorderLayout());

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(searchBarPanel, BorderLayout.NORTH);

        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new FlowLayout());

        ImageIcon imageIcon = new ImageIcon(profile.getImgPath());
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(110, 110, Image.SCALE_SMOOTH);
        ImageIcon scaledImageIcon = new ImageIcon(newImage);

        JLabel profilePicture = new JLabel(scaledImageIcon);
        profilePicture.setPreferredSize(new Dimension(110, 110));
        headerPanel.add(profilePicture);

        JPanel profileInfoPanel = new JPanel();
        profileInfoPanel.setLayout(new BoxLayout(profileInfoPanel, BoxLayout.Y_AXIS));

        JLabel usernameLabel = new JLabel(profile.getUsername());
        usernameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        profileInfoPanel.add(usernameLabel);

        JLabel bioLabel = new JLabel(profile.getBioLabel());
        profileInfoPanel.add(bioLabel);

        headerPanel.add(profileInfoPanel);

        topPanel.add(headerPanel, BorderLayout.CENTER);

        profilePanel.add(topPanel, BorderLayout.NORTH);

        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new GridLayout(1, 3));

        JPanel statItem1 = new JPanel();
        statItem1.setLayout(new BoxLayout(statItem1, BoxLayout.Y_AXIS));
        JLabel postsLabel = new JLabel("Posts");
        JLabel postsCount = new JLabel(String.valueOf(profile.getPostsCount()));
        statItem1.add(postsLabel);
        statItem1.add(postsCount);

        JPanel statItem2 = new JPanel();
        statItem2.setLayout(new BoxLayout(statItem2, BoxLayout.Y_AXIS));
        JLabel followersLabel = new JLabel("Followers");
        JLabel followersCount = new JLabel(String.valueOf(profile.getFollowersCount()));
        statItem2.add(followersLabel);
        statItem2.add(followersCount);

        JPanel statItem3 = new JPanel();
        statItem3.setLayout(new BoxLayout(statItem3, BoxLayout.Y_AXIS));
        JLabel followingLabel = new JLabel("Following");
        JLabel followingCount = new JLabel(String.valueOf(profile.getFollowingCount()));
        statItem3.add(followingLabel);
        statItem3.add(followingCount);

        statsPanel.add(statItem1);
        statsPanel.add(statItem2);
        statsPanel.add(statItem3);

        profilePanel.add(statsPanel, BorderLayout.CENTER);

        JPanel postPanel = new JPanel(new GridLayout(3, 3));

        int post_idx = 2;
        Article[] articles = new Article[post_idx]; // Modify the size according to your requirement

// Initialize posts with sample data for demonstration
        //articles[0] = new Article("/Users/jaehyeoncho/IdeaProjects/user_page/out/production/user_page/ikon.jpg", "Sample Text");
        //articles[1] = new Article("/Users/jaehyeoncho/IdeaProjects/user_page/out/production/user_page/ikon.jpg", "Sample Text");


        // Inside the loop where you create grid cells
        // Inside the loop where you create grid cells
        for (int i = 0; i < 9; i++) {
            JPanel gridCell = new JPanel(new GridLayout(1, 2));
            gridCell.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            gridCell.setPreferredSize(new Dimension(200, 120)); // 칸의 크기 설정

            JLabel cellLabel1 = new JLabel();
            cellLabel1.setHorizontalAlignment(SwingConstants.CENTER); // 이미지를 가운데 정렬

            JLabel cellLabel2 = new JLabel();
            ArticleService as = new ArticleService();
            List<Article> articles1 = as.searchById(2);
            if (i < articles.length) {
                ImageIcon imageIcon1 = new ImageIcon(articles1.get(i).getImage().get(0).getImage());
                Image image1 = imageIcon.getImage();
                Image newImage1 = image.getScaledInstance(180, 100, Image.SCALE_SMOOTH); // 이미지 크기 조절
                ImageIcon scaledImageIcon1 = new ImageIcon(newImage);

                cellLabel1.setIcon(scaledImageIcon);
                cellLabel2.setText(articles[i].getContent());
            }

            gridCell.add(cellLabel1);
            gridCell.add(cellLabel2);
            postPanel.add(gridCell);
        }


        profilePanel.add(postPanel, BorderLayout.SOUTH);

        add(profilePanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        List<InstagramProfile> instagramProfiles = new ArrayList<>();

        // InstagramProfile 객체 생성 및 리스트에 추가
        InstagramProfile profile1 = new InstagramProfile(
                124,
                "//Users/jaehyeoncho/IdeaProjects/user_page/out/production/user_page/ikon.jpg",
                "jaehyeoncho",
                "I hate database",
                1,
                100000,
                150000
        );
        InstagramProfile profile2 = new InstagramProfile(
                12434,
                "//Users/jaehyeoncho/IdeaProjects/user_page/out/production/user_page/ikon.jpg",
                "jaehyeoncho",
                "I hate database",
                2,
                100000,
                150000
        );
        instagramProfiles.add(profile1);
        instagramProfiles.add(profile2);
        InstagramProfile profile = new InstagramProfile(
                1234,
                "//Users/jaehyeoncho/IdeaProjects/user_page/out/production/user_page/ikon.jpg",
                "jaehyeoncho",
                "I hate database",
                3,
                100000,
                150000
        );
        instagramProfiles.add(profile);

        SwingUtilities.invokeLater(() -> {
            new InstagramProfileGUI(profile);
        });
    }
}
