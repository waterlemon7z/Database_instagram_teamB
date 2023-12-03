package swing.articleView;

import entity.Comment.Comment;
import service.CommentService;
import service.UserService;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ShowComment extends JFrame
{
    CommentService commentService = new CommentService();
    private final int article_id;
    private final int user_id;
    JEditorPane commentArea;
    private final UserService userService=  new UserService();

    public ShowComment(int article_id, int user_id)
    {
        this.article_id = article_id;
        this.user_id = user_id;
        JFrame commentFrame = new JFrame("Comment : " + article_id);
        commentFrame.setResizable(false);
        commentFrame.setLocationRelativeTo(null);
        commentFrame.setSize(400, 400);
        commentFrame.setLayout(new BorderLayout());

        commentArea = new JEditorPane();
        commentArea.setEditable(false);
        commentArea.setContentType("text/html");
        commentArea.setEditorKit(JEditorPane.createEditorKitForContentType("text/html"));
        commentArea.setEnabled(true);
        refresh();
        commentArea.setCaretPosition(0);
        JScrollPane commentScrollPane = new JScrollPane(commentArea);
        commentFrame.add(commentScrollPane);
        commentArea.addHyperlinkListener(new HyperlinkListener()
        {
            public void hyperlinkUpdate(HyperlinkEvent e)
            {
                if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED)
                {
                    if (e.getDescription().contains("like"))
                    {
//                        System.out.println("OK");
                        int comment_id = Integer.parseInt(e.getDescription().substring(5));
                        System.out.println("like : " + comment_id);
                        commentService.likeSwitcher(comment_id, user_id);
                        int caretPosition = commentArea.getCaretPosition();
                        refresh();
                        commentArea.setCaretPosition(caretPosition);
                    }
                    if (e.getDescription().contains("delete"))
                    {
                        int comment_id = Integer.parseInt(e.getDescription().substring(7));
                        System.out.println("delete : " + comment_id);

                        commentService.removeComment(comment_id, user_id);
                        refresh();
                    }
                }
            }
        });
        JPanel addCommentP = new JPanel();
        JTextField commentText = new JTextField(20);
        JButton submitB = new JButton("확인");
        addCommentP.add(commentText, BorderLayout.WEST);
        addCommentP.add(submitB, BorderLayout.EAST);
        submitB.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                String text = commentText.getText();
                if (Objects.equals(text, "")) return;
                Comment comment = new Comment(0, user_id, text, article_id, LocalDateTime.now(), null, null);
                commentService.createComment(comment);
                commentText.setText("");
                refresh();
            }
        });
        addCommentP.setBackground(new Color(-1));
        commentFrame.add(addCommentP, BorderLayout.SOUTH);
        commentFrame.setVisible(true);
    }

    void refresh()
    {
        List<Comment> comments = commentService.searchByArticleId(article_id);
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
                "            color: black;\n" +
                "            " +
                "        }" +
                "        .comment\n" +
                "        {\n" +
                "            margin-left: 10px;\n" +
                "            margin-bottom: 10px;\n" +
                "        }" +
                ".comment {\n" +
                "            display: inline-block;\n" +
                "        }" +
                "    </style>\n" +
                "</head>";
        Collections.reverse(comments);
        for (Comment iter : comments)
        {
            html += "<div class=\"comment\"> " +
                    "<table>\n" +
                    "    <tr>\n" +
                    "        <td><img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/2/2c/Default_pfp.svg/680px-Default_pfp.svg.png?20220226140232\" alt=\"Your Image\" width=40></td>\n" +
                    "        <td>" + "<b>" + userService.getUserById(iter.getId()).getUser_id() + " </b>" + iter.getText() + "<br>" +
                    iter.getDate() + "일     " + "<a href=\"like/" + iter.getComment_id() + "\">좋아요</a> " + iter.getLikes().size() + "개"
                    + (iter.getId() == user_id ? "     <a href=\"delete/" + iter.getComment_id() + "\">삭제</a>" : "") + "</td>\n" +
                    "    </tr>\n" +
                    "</table>" +
                    "</div>";
        }
        commentArea.setText(html);
        commentArea.setCaretPosition(0);
    }
}
