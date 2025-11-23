import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class about extends JFrame {
    JButton exit, mini,home;
            JButton f1,b1;

    public about()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenW = screenSize.width;
        int screenH = screenSize.height;
        setLayout(null);


                int resizedWidth = 30, resizedHeight = 30;

        ///////////// Close Button
        ImageIcon icon40 = new ImageIcon(new ImageIcon("image/close01.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        ImageIcon pressedIcon40 = new ImageIcon(new ImageIcon("image/close0.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

        exit = new JButton(icon40);
        exit.setBounds(screenW - 35, 5, resizedWidth, resizedHeight);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverIcon(pressedIcon40);
        add(exit);

        exit.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exit.setIcon(pressedIcon40);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                exit.setIcon(icon40);
                System.exit(0);
            }
        });

        exit.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), "ctrlC");
        exit.getActionMap().put("ctrlC", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        ///////////// Minimize Button
ImageIcon icon41 = new ImageIcon(new ImageIcon("image/mini0.png")
        .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
ImageIcon pressedIcon41 = new ImageIcon(new ImageIcon("image/mini01.png")
        .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

mini = new JButton(icon41);
mini.setBounds(screenW-70, 5, resizedWidth, resizedHeight);
mini.setBorderPainted(false);
mini.setFocusPainted(false);
mini.setContentAreaFilled(false);
mini.setRolloverIcon(pressedIcon41);
add(mini);

mini.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        mini.setIcon(pressedIcon41);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mini.setIcon(icon41);
        setState(JFrame.ICONIFIED);
    }
});

mini.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
    KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.SHIFT_DOWN_MASK), "shiftM");

mini.getActionMap().put("shiftM", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        setState(JFrame.ICONIFIED);
    }
});


///////////// Home Button
ImageIcon icon42 = new ImageIcon(new ImageIcon("image/home01.png")
        .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
ImageIcon pressedIcon42 = new ImageIcon(new ImageIcon("image/home0.png")
        .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

home = new JButton(icon42);
home.setBounds(screenW-105, 5, resizedWidth, resizedHeight);
home.setBorderPainted(false);
home.setFocusPainted(false);
home.setContentAreaFilled(false);
home.setRolloverIcon(pressedIcon42);
add(home);

home.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        home.setIcon(pressedIcon42);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        home.setIcon(icon42);
        new home();
        setVisible(false);
    }
});

home.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK), "ctrlH");
home.getActionMap().put("ctrlH", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
       new home();
       setVisible(false);
    }
});


resizedHeight =resizedHeight +50;
resizedWidth = resizedWidth+50;


///////////// Back Button
ImageIcon icon43 = new ImageIcon(new ImageIcon("image/forB01.png")
        .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
ImageIcon pressedIcon43 = new ImageIcon(new ImageIcon("image/forB02.png")
        .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

f1 = new JButton(icon43);
f1.setBounds(screenW-resizedWidth-50, screenH-resizedHeight-80+30, resizedWidth, resizedHeight);
f1.setBorderPainted(false);
f1.setFocusPainted(false);
f1.setContentAreaFilled(false);
f1.setRolloverIcon(pressedIcon43);
add(f1);

f1.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        f1.setIcon(pressedIcon43);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        f1.setIcon(icon43);
        new about1();
        setVisible(false);
    }
});


        /// First page
        ImageIcon originalIcon = new ImageIcon("image/ss02.png");
        Image originalImage = originalIcon.getImage();

        int newWidth = screenW;
        int newHeight = screenH;

        Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        JLabel imageLabel = new JLabel(scaledIcon);
        imageLabel.setBounds(0, 0, newWidth, newHeight);
        add(imageLabel);


        setUndecorated(true);
        getContentPane().setBackground(Color.decode("#900C3F"));
        setBounds(0, 0, screenW, screenH);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
