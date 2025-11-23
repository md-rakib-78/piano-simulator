import javax.swing.*;
import java.awt.*;

public class Splash extends JFrame {

    Splash() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenW = screenSize.width;
        int screenH = screenSize.height;

         JLabel imageLabel = new JLabel(new ImageIcon("image/pngwing.com.png"));
        imageLabel.setBounds(100, 0, screenW - screenH-100, screenH - (screenH / 2));
        add(imageLabel);

        JLabel l1 = new JLabel("RTP PIANO");
        l1.setFont(new Font("Jokerman", Font.PLAIN, 47));
        l1.setForeground(Color.decode("#eeeee4"));
        l1.setBounds(25, (screenH - (screenH / 2)) / 2 - 100, screenW - screenH, 200);

        add(l1);

        JLabel l2 = new JLabel("<html>Version rtp(1.0) / 64-bit(win64)<br>April 2025</html>");
        l2.setFont(new Font("Arial", Font.PLAIN, 13));
        l2.setForeground(Color.decode("#eeeee4"));
        l2.setBounds(25, -50, 700, 200);
        add(l2);

        JLabel l3 = new JLabel("<html><b>Creator:</b><br>Md Rakibul Islam<br> BSc in CSE</html>");
        l3.setFont(new Font("Arial", Font.PLAIN, 13));
        l3.setForeground(Color.decode("#eeeee4"));
        l3.setBounds(25, screenH - (screenH / 2) - 150, 700, 200);
        add(l3);

        setUndecorated(true);
        getContentPane().setBackground(Color.decode("#900C3F"));
        setLayout(null);
        setBounds(0, 0, screenW - screenH, screenH - (screenH / 2));
        setLocationRelativeTo(null);
        setVisible(true);

        try {
            Thread.sleep(5000);
            setVisible(false);
            new home();
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 

    public static void main(String[] args) {

        new Splash();
    }
}
