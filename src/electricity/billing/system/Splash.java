package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;     // used for Timer and TimerTask
import java.util.TimerTask;

public class Splash extends JFrame {

    Splash() {
        setTitle("Voltra");

        // Custom panel with gradient background
        JPanel content = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gp = new GradientPaint(0, 0, new Color(0, 102, 204), 0, getHeight(), new Color(102, 204, 255));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        content.setLayout(null);
        setContentPane(content);

        // Heading - Voltra
        JLabel heading = new JLabel("Voltra");
        heading.setFont(new Font("Segoe UI Black", Font.BOLD, 48));
        heading.setForeground(Color.WHITE);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBounds(0, 20, 800, 60);
        content.add(heading);

        // Tagline
        JLabel tag = new JLabel("A New Era of Electric Utility Management");
        tag.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        tag.setForeground(Color.WHITE);
        tag.setHorizontalAlignment(SwingConstants.CENTER);
        tag.setBounds(0, 80, 800, 30);
        content.add(tag);

        // High quality centered image
        ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("icon/splash/Demo1.jpg"));
        Image scaledImage = icon.getImage().getScaledInstance(500, 300, Image.SCALE_SMOOTH);  // Best quality
        ImageIcon highQualityIcon = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(highQualityIcon);
        imageLabel.setBounds(150, 130, 500, 300); // reposition image a bit lower
        content.add(imageLabel);

        // Frame setup
        setSize(800, 500); // increased for better layout
        setLocationRelativeTo(null); // center on screen
        setUndecorated(true); // remove window borders for clean look
        setVisible(true);

        // Transition to Login screen after delay
        new Timer().schedule(new TimerTask() {
            public void run() {
                setVisible(false);
                new Login();
            }
        }, 3000); // 3 seconds
    }

    public static void main(String[] args) {
        new Splash();
    }
}
