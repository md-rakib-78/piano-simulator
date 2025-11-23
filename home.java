import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class home extends JFrame {
    JButton exit, mini,re,mu;

        JButton cl,mi,ab,pi;


    public home() {
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
        mini.setBounds(screenW - 70, 5, resizedWidth, resizedHeight);
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
                KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK), "ctrlM");
        mini.getActionMap().put("ctrlM", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                setState(JFrame.ICONIFIED);
            }
        });



resizedHeight =resizedHeight +100;
resizedWidth = resizedWidth+100;

///////////// Piano Board Button
        ImageIcon icon46 = new ImageIcon(new ImageIcon("image/power01.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        ImageIcon pressedIcon46 = new ImageIcon(new ImageIcon("image/power02.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

        pi = new JButton(icon46);
        pi.setBounds(screenW/2-2*resizedWidth-resizedWidth/2,screenH/2-resizedHeight/2, resizedWidth, resizedHeight);
        pi.setBorderPainted(false);
        pi.setFocusPainted(false);
        pi.setContentAreaFilled(false);
        pi.setRolloverIcon(pressedIcon46);
        add(pi);

        pi.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pi.setIcon(pressedIcon46);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pi.setIcon(icon46);
                new PianoBoard();
                setVisible(false);
            }
        });

        pi.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK), "ctrlP");
        pi.getActionMap().put("ctrlP", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                new PianoBoard();
                setVisible(false);
            }
        });


        ///////////// About shortcut Button
        ImageIcon icon47 = new ImageIcon(new ImageIcon("image/AB002.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        ImageIcon pressedIcon47 = new ImageIcon(new ImageIcon("image/AB001.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

        ab = new JButton(icon47);
        ab.setBounds(screenW/2-resizedWidth,screenH/2-resizedHeight/2, resizedWidth, resizedHeight);
        ab.setBorderPainted(false);
        ab.setFocusPainted(false);
        ab.setContentAreaFilled(false);
        ab.setRolloverIcon(pressedIcon47);
        add(ab);

        ab.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ab.setIcon(pressedIcon47);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ab.setIcon(icon47);
                new about();
                setVisible(false);
            }
        });

        ab.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK), "ctrlB");
        ab.getActionMap().put("ctrlB", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                new about();
                setVisible(false);
            }
        });


         ///////////// Big Close Button
        ImageIcon icon48 = new ImageIcon(new ImageIcon("image/CLS001.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        ImageIcon pressedIcon48 = new ImageIcon(new ImageIcon("image/CLS002.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

        cl = new JButton(icon48);
        cl.setBounds(screenW/2+2*resizedWidth,screenH/2-resizedHeight/2, resizedWidth, resizedHeight);
        cl.setBorderPainted(false);
        cl.setFocusPainted(false);
        cl.setContentAreaFilled(false);
        cl.setRolloverIcon(pressedIcon48);
        add(cl);

        cl.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cl.setIcon(pressedIcon48);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                cl.setIcon(icon48);
                 System.exit(0);
            }
        });

        cl.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK), "ctrlC");
        cl.getActionMap().put("ctrlC", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                  System.exit(0);
            }
        });

        ///////////// Big Min Button
        ImageIcon icon49 = new ImageIcon(new ImageIcon("image/MIN001.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        ImageIcon pressedIcon49 = new ImageIcon(new ImageIcon("image/MIN003.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

        mi = new JButton(icon49);
        mi.setBounds(screenW/2+resizedWidth/2,screenH/2-resizedHeight/2, resizedWidth, resizedHeight);
        mi.setBorderPainted(false);
        mi.setFocusPainted(false);
        mi.setContentAreaFilled(false);
        mi.setRolloverIcon(pressedIcon49);
        add(mi);

        mi.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mi.setIcon(pressedIcon49);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                mi.setIcon(icon49);
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


ImageIcon originalIcon = new ImageIcon("image/title10.png");
Image originalImage = originalIcon.getImage();


int newWidth = 620;  
int newHeight = 180;

Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
ImageIcon scaledIcon = new ImageIcon(scaledImage);

JLabel imageLabel = new JLabel(scaledIcon);
imageLabel.setBounds(screenW/2-newWidth/2, 30, newWidth, newHeight);
add(imageLabel);



ImageIcon originalIcon1 = new ImageIcon("image/pianoboard.png");
Image originalImage1 = originalIcon1.getImage();

 newWidth = 980;  
 newHeight = 120;

Image scaledImage1 = originalImage1.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
ImageIcon scaledIcon1 = new ImageIcon(scaledImage1);

JLabel imageLabel1 = new JLabel(scaledIcon1);
imageLabel1.setBounds(screenW/2+30-newWidth/2, screenH/2+resizedWidth/2, newWidth, newHeight);
add(imageLabel1);


        JLabel text = new JLabel("Copyright 2025 by RTP. All Rights Reserved. md-rakib-78.");
        text.setFont(new Font("Arial", Font.PLAIN, 16));
        text.setForeground(Color.lightGray);
        text.setBounds(screenW/2-210,screenH-55,500,70);
        add(text);

        setUndecorated(true);
        getContentPane().setBackground(Color.decode("#900C3F"));
        setBounds(0, 0, screenW, screenH);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
