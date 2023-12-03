package swing.profileView;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class ex
{
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            createAndShowGUI();
        });
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Online Image Grid Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // �̹��� URL �迭 (���ϴ� �̹��� URL�� �����ϼ���)
        String[] imageUrls = {
                "https://mblogthumb-phinf.pstatic.net/20140208_206/hw83_1391836583082d6MBL_JPEG/Penguin1%286854%29.jpg?type=w420",
                "https://mblogthumb-phinf.pstatic.net/20140208_206/hw83_1391836583082d6MBL_JPEG/Penguin1%286854%29.jpg?type=w420",
                "https://mblogthumb-phinf.pstatic.net/20140208_206/hw83_1391836583082d6MBL_JPEG/Penguin1%286854%29.jpg?type=w420",
                "https://mblogthumb-phinf.pstatic.net/20140208_206/hw83_1391836583082d6MBL_JPEG/Penguin1%286854%29.jpg?type=w420",
                "https://mblogthumb-phinf.pstatic.net/20140208_206/hw83_1391836583082d6MBL_JPEG/Penguin1%286854%29.jpg?type=w420",
                "https://mblogthumb-phinf.pstatic.net/20140208_206/hw83_1391836583082d6MBL_JPEG/Penguin1%286854%29.jpg?type=w420",
                // �߰� �̹��� URL��...
        };
        int numOfImages = imageUrls.length;
        int rows = (int) Math.ceil((double) numOfImages / 3); // �̹��� ���� ���� ���� ���
        JPanel imagePanel = new JPanel(new GridLayout(rows, 3, 0, 0)); // �׸��� ���̾ƿ�, ���� ����

        // �̹����� �ҷ��ͼ� JLabel�� �ְ�, �� JLabel�� JPanel�� �߰�
        for (String imageUrl : imageUrls) {
            try {
                URL url = new URL(imageUrl);
                ImageIcon icon = new ImageIcon(url);
                Image resizedImage
                        = icon.getImage().getScaledInstance(133, 133, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(resizedImage);
                JLabel label = new JLabel(resizedIcon);
                imagePanel.add(label);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        JScrollPane scrollPane = new JScrollPane(imagePanel);

        frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);
        frame.setVisible(true);
    }
}
