package swing.articleView;

import entity.Article.Article;
import entity.Article.Article_hashtag;
import entity.Article.Article_image;
import jdbc.ConnectionManager;
import service.ArticleService;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EditArticle extends JFrame
{
    private JTextArea imageUrlField;
    private JTextArea contentField;
    private JTextField hashtagField;
    private final ArticleService articleService = new ArticleService();

    public EditArticle(Article editA, int loginId)
    {
        setTitle("새글 작성");
        setSize(400, 300);
        setBackground(new Color(-1));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE); // 전체 배경색을 흰색으로 설정

        // 이미지 주소 입력 필드
        imageUrlField = createTextArea(); // 이미지 주소도 JTextArea로 변경
        mainPanel.add(createLabeledPanel("이미지 주소:", imageUrlField));
        if(editA.getImage().size() != 0)
        {
            String tar = "";
            for (Article_image iter: editA.getImage())
                tar += iter.getImage() + ",";
            imageUrlField.setText(tar);
        }
        // 내용 입력 필드 (JTextArea)
        contentField = createTextArea();
        mainPanel.add(createLabeledPanel("내용:         ", contentField));
        contentField.setText(editA.getContent());
        // 해시태그 입력 필드
        hashtagField = createTextField();
        mainPanel.add(createLabeledPanel("해시태그:   ", hashtagField));
        if(editA.getHashtag().size() != 0)
        {
            String tar = "";
            for (Article_hashtag iter: editA.getHashtag())
                tar += iter.getHashtag() + ",";
            hashtagField.setText(tar);
        }
        // 여러 이미지, 여러 해시태그 입력 안내 문구 (JTextPane 사용)
        JTextPane noteTextPane = createNoteTextPane();
        mainPanel.add(noteTextPane);

        // 전송 버튼
        JButton sendButton = new JButton("전송");
        sendButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String imageUrl = imageUrlField.getText();
                String content = contentField.getText();
                String hashtags = hashtagField.getText();

                String message = "이미지 주소: " + imageUrl + "\n내용: " + content + "\n해시태그: " + hashtags;
                List<Article_image> imageList;
                if(imageUrl == "")
                {
                    imageList = null;
                }
                else
                {
                    String[] imageUrlSplit = imageUrl.split(",");
                    imageList = new ArrayList<>();
                    for (String iter : imageUrlSplit)
                    {
                        imageList.add(new Article_image(0, iter));
                    }
                }
                List<Article_hashtag> hashtagList;
                if(hashtags.equals(""))
                {
                    hashtagList = null;
                }
                else
                {
                    String[] hashtagSplit = hashtags.split(",");
                    hashtagList = new ArrayList<>();
                    for (String iter : hashtagSplit)
                    {
                        hashtagList.add(new Article_hashtag(0, iter));
                    }
                }
                Article article = new Article(editA.getArticle_id(), loginId, content, editA.getDate(), editA.getLikes(), imageList, hashtagList);
                articleService.updateArticle(article);
                JOptionPane.showMessageDialog(EditArticle.this, "수정되었습니다.");
                dispose();
            }
        });
        mainPanel.add(sendButton);

        add(mainPanel);
        setVisible(true);
    }

    private JTextArea createTextArea()
    {
        JTextArea textArea = new JTextArea();
        textArea.setColumns(20);
        textArea.setRows(7);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        return textArea;
    }

    private JTextField createTextField()
    {
        JTextField textField = new JTextField();
        textField.setColumns(20);
        textField.setHorizontalAlignment(JTextField.LEFT);
        return textField;
    }

    private JPanel createLabeledPanel(String label, JComponent component)
    {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEADING));
        JLabel labelComponent = new JLabel(label);
        panel.add(labelComponent);
        panel.add(component);
        return panel;
    }

    private JTextPane createNoteTextPane()
    {
        JTextPane textPane = new JTextPane();
        textPane.setEditable(false);

        SimpleAttributeSet attributeSet = new SimpleAttributeSet();
        textPane.setCharacterAttributes(attributeSet, true);
        textPane.setText("여러 이미지, 여러 해시태그를 입력시에는 콤마(,)으로 입력을 구분해 주세요");

        return textPane;
    }

    public static void main(String[] args)
    {
        ConnectionManager.getConnection();
        SwingUtilities.invokeLater(() ->
        {
            new NewArticle(1);
        });
    }
}
