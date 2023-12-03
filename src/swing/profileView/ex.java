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

        // 이미지 URL 배열 (원하는 이미지 URL로 변경하세요)
        String[] imageUrls = {
                "https://mblogthumb-phinf.pstatic.net/20140208_206/hw83_1391836583082d6MBL_JPEG/Penguin1%286854%29.jpg?type=w420",
                "https://mblogthumb-phinf.pstatic.net/20140208_206/hw83_1391836583082d6MBL_JPEG/Penguin1%286854%29.jpg?type=w420",
                "https://mblogthumb-phinf.pstatic.net/20140208_206/hw83_1391836583082d6MBL_JPEG/Penguin1%286854%29.jpg?type=w420",
                "https://mblogthumb-phinf.pstatic.net/20140208_206/hw83_1391836583082d6MBL_JPEG/Penguin1%286854%29.jpg?type=w420",
                "https://mblogthumb-phinf.pstatic.net/20140208_206/hw83_1391836583082d6MBL_JPEG/Penguin1%286854%29.jpg?type=w420",
                "https://mblogthumb-phinf.pstatic.net/20140208_206/hw83_1391836583082d6MBL_JPEG/Penguin1%286854%29.jpg?type=w420",
                // 추가 이미지 URL들...
        };
        int numOfImages = imageUrls.length;
        int rows = (int) Math.ceil((double) numOfImages / 3); // 이미지 수에 따른 행의 계산
        JPanel imagePanel = new JPanel(new GridLayout(rows, 3, 0, 0)); // 그리드 레이아웃, 간격 조절

        // 이미지를 불러와서 JLabel에 넣고, 그 JLabel을 JPanel에 추가
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
