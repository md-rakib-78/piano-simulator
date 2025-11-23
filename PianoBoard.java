import javax.sound.sampled.*;
import javax.swing.*;
import javazoom.jlgui.basicplayer.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;


public class PianoBoard extends JFrame implements ActionListener {
    JButton b1, b2,b3,b4,b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19,b20,b21,b22,b23,b24,b25,b26,b27,b28,b29,b30,b31,b32
    ,b33,b34,b35,b36,b37,b38,b39,b40;

    JPanel transparentPanel,transparentPanel1,transparentPanel2,transparentPanel3;

    JLabel label1,label2;

    JButton exit,mini,home,rec,mu,volumeButton,ke,vo,in;

    ImageIcon pressedIcon43;
    ImageIcon pressedIcon44;
    ImageIcon pressedIcon45;
    ImageIcon pressedIcon46;
    ImageIcon pressedIcon47;

     static BasicPlayer basicPlayer = new BasicPlayer();
     public static int[] currentVolume = {100};

    BasicPlayer player;
    int muW;

    public PianoBoard() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenW = screenSize.width;
        int screenH = screenSize.height;

        setLayout(null); 


        ////////////////////////////////////// J Panel 0.1
        transparentPanel2 = new JPanel();
        transparentPanel2.setOpaque(false);
        transparentPanel2.setBounds(0, 0, screenW, screenH);
        transparentPanel2.setLayout(new GridBagLayout());
        add(transparentPanel2);


        //////////////////////////////////////////////////////////////////// Upper Shadow Add \\\\\\\\\\\\\\\\\\
        ImageIcon icon0 = new ImageIcon("image/button1.png");
        int imgH0 = icon0.getIconHeight();

        ImageIcon boardImg1 = new ImageIcon("image/shadow.png");
        Image img1 = boardImg1.getImage();
        int width1 = img1.getWidth(null);
        int height1 = img1.getHeight(null);

        JPanel shadowPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(0, 0, 0, 150),
                        0, getHeight(), new Color(0, 0, 0, 0));

                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        int shadowWidth = width1;
        int shadowHeight = height1;

        shadowPanel.setBounds(-2, screenH - imgH0, shadowWidth, shadowHeight);
        shadowPanel.setOpaque(false);
        add(shadowPanel);




//////////////////////////////////////////////////////////////////// Display JPanel For Play  And Media \\\\\\\\\\\\\\\\
ImageIcon boardImg = new ImageIcon("image/board.png");
Image img = boardImg.getImage();
int width = img.getWidth(null);
int height = img.getHeight(null);

JLabel boardLabel = new JLabel(boardImg);
boardLabel.setBounds(-2,screenH - imgH0-35 , width-50, height);


////////////////////////////////////// J Panel 1
transparentPanel = new JPanel();
transparentPanel.setOpaque(false);
transparentPanel.setBounds((screenW / 2) - (width / 3) / 2+25-10, (screenH / 5)-(height/2)/2+20, (width /3)/2-10, height/2-35);
transparentPanel.setLayout(new GridBagLayout());
transparentPanel.setBorder(BorderFactory.createMatteBorder(12, 12, 12, 8,Color.decode("#181818")));
//transparentPanel.setBorder(BorderFactory.createLineBorder(Color.PINK));
add(transparentPanel);

label1 = new JLabel("PLAY", SwingConstants.CENTER);
label1.setForeground(new Color(0, 200, 255));
label1.setFont(new Font("Jokerman", Font.PLAIN, 30)); 
transparentPanel.add(label1);



////////////////////////////////////// J Panel 2
muW=(width /3)/2;
transparentPanel1 = new JPanel();
transparentPanel1.setOpaque(false);
transparentPanel1.setBounds((screenW / 2) - (width / 3) / 2+ (width /3)/2-5,(screenH / 5)-(height/2)/2+20, (width /3)/2-5, height/2-35);
transparentPanel1.setLayout(new GridBagLayout()); 
//transparentPanel1.setBorder(BorderFactory.createMatteBorder(0, 3, 0, 0,Color.decode("#353535")));
transparentPanel1.setBorder(BorderFactory.createMatteBorder(12, 12, 12, 12,Color.decode("#181818")));
add(transparentPanel1);

label2 = new JLabel("MEDIA", SwingConstants.CENTER);
label2.setForeground(new Color(0, 200, 255));
label2.setFont(new Font("Jokerman", Font.PLAIN, 30)); 
transparentPanel1.add(label2);




/////////////////////////////////// J Panel BackGround
ImageIcon boardImg3 = new ImageIcon("image/board.png");
Image img3 = boardImg3.getImage();
int customWidth = width/3;
int customHeight = height/2;
Image scaledImg3 = img3.getScaledInstance(customWidth-40, customHeight-35, Image.SCALE_SMOOTH);
ImageIcon scaledIcon3 = new ImageIcon(scaledImg3);
JLabel boardLabel3 = new JLabel(scaledIcon3);
boardLabel3.setBounds((screenW/2)-customWidth/2+20, (screenH/5)-customHeight/2+20, customWidth-40, customHeight-35);
add(boardLabel3);

player = new BasicPlayer();


        //////////////////////////////////////////////////////////////////// Button 1 \\\\\\\\\\\\\\\\\\\\\\\
        ImageIcon icon1 = new ImageIcon("image/button1.png");
        ImageIcon pressedIcon1 = new ImageIcon("image/sbutton1.png");
        int imgW1 = icon1.getIconWidth();
        int imgH1 = icon1.getIconHeight();

        b1 = new JButton(icon1);
        b1.setBounds(0, screenH-imgH1, imgW1, imgH1);
        b1.setBorderPainted(false);
        b1.setFocusPainted(false);
        b1.setContentAreaFilled(false);
        add(b1);

        b1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 1"), "press1");
        b1.getActionMap().put("press1", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("F2", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b1.setIcon(pressedIcon1);
                playSound("F2.wav");
            }
        });

        b1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released 1"), "release1");
        b1.getActionMap().put("release1", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b1.setIcon(icon1);
            }
        });

        //////////////////////////////////////////////////////////////////////////Button #1 \\\\\\\\\\\\
        ImageIcon icon2 = new ImageIcon("image/button#1.png");
        ImageIcon pressedIcon2 = new ImageIcon("image/sbutton#1.png");
        int imgW2 = icon2.getIconWidth();
        int imgH2 = icon2.getIconHeight();

        b2 = new JButton(icon2);
        b2.setBounds((imgW1-imgW1/2)+6, screenH-imgH1, imgW2, imgH2);
        b2.setBorderPainted(false);
        b2.setFocusPainted(false);
        b2.setContentAreaFilled(false);
        add(b2);

        b2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.SHIFT_DOWN_MASK, false), "press!");
        b2.getActionMap().put("press!", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("F#2", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b2.setIcon(pressedIcon2);
                playSound1("F#2.wav");
            }
        });

        b2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.SHIFT_DOWN_MASK, true), "release!");
        b2.getActionMap().put("release!", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b2.setIcon(icon2);
            }
        });



        //////////////////////////////////////////////////////////////////////////Button 2 \\\\\\\\\\\\
        ImageIcon icon3 = new ImageIcon("image/button2.png");
        ImageIcon pressedIcon3 = new ImageIcon("image/sbutton2.png");
        int imgW3 = icon3.getIconWidth();
        int imgH3 = icon3.getIconHeight();

        b3 = new JButton(icon3);
        b3.setBounds(imgW1/2+imgW2-5, screenH-imgH1, imgW3, imgH3);
        b3.setBorderPainted(false);
        b3.setFocusPainted(false);
        b3.setContentAreaFilled(false);
        add(b3);

        b3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 2"), "press2");
        b3.getActionMap().put("press2", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("G2", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b3.setIcon(pressedIcon3);
                playSound("G2.wav");
            }
        });

        b3.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released 2"), "release2");
        b3.getActionMap().put("release2", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b3.setIcon(icon3);
            }
        });


 //////////////////////////////////////////////////////////////////////////Button #2 \\\\\\\\\\\\
        ImageIcon icon4 = new ImageIcon("image/button#1.png");
        ImageIcon pressedIcon4 = new ImageIcon("image/sbutton#1.png");
        int imgW4 = icon4.getIconWidth();
        int imgH4 = icon4.getIconHeight();

        b4 = new JButton(icon4);
        b4.setBounds(imgW1/2+imgW2+49, screenH-imgH1, imgW4, imgH4);
        b4.setBorderPainted(false);
        b4.setFocusPainted(false);
        b4.setContentAreaFilled(false);
        add(b4);

        b4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.SHIFT_DOWN_MASK, false), "press@");
        b4.getActionMap().put("press@", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("G#2", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b4.setIcon(pressedIcon4);
                playSound1("G#2.wav");
            }
        });

        b4.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.SHIFT_DOWN_MASK, true), "release@");
        b4.getActionMap().put("release@", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b4.setIcon(icon4);
            }
        });


         //////////////////////////////////////////////////////////////////////////Button 3 \\\\\\\\\\\\
        ImageIcon icon5 = new ImageIcon("image/button3.png");
        ImageIcon pressedIcon5 = new ImageIcon("image/sbutton3.png");
        int imgW5 = icon5.getIconWidth();
        int imgH5 = icon5.getIconHeight();

        b5 = new JButton(icon5);
        b5.setBounds(imgW1/2+imgW2+71, screenH-imgH1, imgW5, imgH5);
        b5.setBorderPainted(false);
        b5.setFocusPainted(false);
        b5.setContentAreaFilled(false);
        add(b5);

        b5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 3"), "press3");
        b5.getActionMap().put("press3", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("A2", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b5.setIcon(pressedIcon5);
                playSound("A2.wav");
            }
        });

        b5.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released 3"), "release3");
        b5.getActionMap().put("release3", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b5.setIcon(icon5);
            }
        });


                 //////////////////////////////////////////////////////////////////////////Button #3 \\\\\\\\\\\\
        ImageIcon icon6 = new ImageIcon("image/button#1.png");
        ImageIcon pressedIcon6 = new ImageIcon("image/sbutton#1.png");
        int imgW6 = icon6.getIconWidth();
        int imgH6 = icon6.getIconHeight();

        b6 = new JButton(icon6);
        b6.setBounds(imgW1/2+imgW2+71+imgW5-imgW5/5+4, screenH-imgH1, imgW6, imgH6);
        b6.setBorderPainted(false);
        b6.setFocusPainted(false);
        b6.setContentAreaFilled(false);
        add(b6);


        b6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.SHIFT_DOWN_MASK, false), "pressHash");
        b6.getActionMap().put("pressHash", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("A#2", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b6.setIcon(pressedIcon6);
                playSound1("A#2.wav");
            }
        });

        b6.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.SHIFT_DOWN_MASK, true), "releaseHash");
        b6.getActionMap().put("releaseHash", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b6.setIcon(icon6);
            }
        });

//////////////////////////////////////////////////////////////////////////Button 4 \\\\\\\\\\\\
        ImageIcon icon7 = new ImageIcon("image/button4.png");
        ImageIcon pressedIcon7 = new ImageIcon("image/sbutton4.png");
        int imgW7 = icon7.getIconWidth();
        int imgH7 = icon7.getIconHeight();

        b7 = new JButton(icon7);
        b7.setBounds(imgW1/2+imgW2+71+imgW5-imgW5/5+4+12, screenH-imgH1, imgW7, imgH7);
        b7.setBorderPainted(false);
        b7.setFocusPainted(false);
        b7.setContentAreaFilled(false);
        add(b7);

        b7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 4"), "press4");
        b7.getActionMap().put("press4", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("B2", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b7.setIcon(pressedIcon7);
                playSound("B2.wav");
            }
        });

        b7.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released 4"), "release4");
        b7.getActionMap().put("release4", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b7.setIcon(icon7);
            }
        });

        int num1=imgW1/2+imgW2+71+imgW5-imgW5/5+4+12;

                //////////////////////////////////////////////////////////////////// Button 5 \\\\\\\\\\\\\\\\\\\\\\\
        ImageIcon icon8 = new ImageIcon("image/button1.png");
        ImageIcon pressedIcon8 = new ImageIcon("image/sbutton1.png");
        int imgW8 = icon1.getIconWidth();
        int imgH8 = icon1.getIconHeight();

        b8 = new JButton(icon8);
        b8.setBounds(num1+imgW7+2, screenH-imgH1, imgW8, imgH8);
        b8.setBorderPainted(false);
        b8.setFocusPainted(false);
        b8.setContentAreaFilled(false);
        add(b8);

        b8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 5"), "press5");
        b8.getActionMap().put("press5", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("C3", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b8.setIcon(pressedIcon8);
                playSound("C3.wav");
            }
        });

        b8.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released 5"), "release5");
        b8.getActionMap().put("release5", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b8.setIcon(icon8);
            }
        });

     //////////////////////////////////////////////////////////////////// Button # 5 \\\\\\\\\\\\\\\\\\\\\\\
        ImageIcon icon9 = new ImageIcon("image/button#1.png");
        ImageIcon pressedIcon9 = new ImageIcon("image/sbutton#1.png");
        int imgW9 = icon9.getIconWidth();
        int imgH9 = icon9.getIconHeight();

        b9 = new JButton(icon9);
        b9.setBounds(num1+imgW7+imgW9+1, screenH-imgH1, imgW9, imgH9);
        b9.setBorderPainted(false);
        b9.setFocusPainted(false);
        b9.setContentAreaFilled(false);
        add(b9);


        b9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.SHIFT_DOWN_MASK, false), "pressPercent");
        b9.getActionMap().put("pressPercent", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("C#3", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);


                b9.setIcon(pressedIcon9);
                playSound1("C#3.wav");
            }
        });

        b9.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.SHIFT_DOWN_MASK, true), "releasePercent");
        b9.getActionMap().put("releasePercent", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b9.setIcon(icon9);
            }
        });

//////////////////////////////////////////////////////////////////// Button 6 \\\\\\\\\\\\\\\\\\\\\\\
        ImageIcon icon10 = new ImageIcon("image/mbutton.png");
        ImageIcon pressedIcon10 = new ImageIcon("image/smbutton.png");
        int imgW10 = icon10.getIconWidth();
        int imgH10 = icon10.getIconHeight();

        b10 = new JButton(icon10);
        b10.setBounds(num1+imgW7+imgW9+31, screenH-imgH1, imgW10, imgH10);
        b10.setBorderPainted(false);
        b10.setFocusPainted(false);
        b10.setContentAreaFilled(false);
        add(b10);

        b10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 6"), "press6");
        b10.getActionMap().put("press6", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("D3", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);


                b10.setIcon(pressedIcon10);
                playSound("D3.wav");
            }
        });

        b10.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released 6"), "release6");
        b10.getActionMap().put("release6", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b10.setIcon(icon10);
            }
        });


 //////////////////////////////////////////////////////////////////// Button #6 \\\\\\\\\\\\\\\\\\\\\\\
        ImageIcon icon11 = new ImageIcon("image/button#1.png");
        ImageIcon pressedIcon11 = new ImageIcon("image/sbutton#1.png");
        int imgW11 = icon11.getIconWidth();
        int imgH11 = icon11.getIconHeight();

        b11 = new JButton(icon11);
        b11.setBounds(num1+imgW7+imgW9+imgW10+16, screenH-imgH1, imgW11, imgH11);
        b11.setBorderPainted(false);
        b11.setFocusPainted(false);
        b11.setContentAreaFilled(false);
        add(b11);

        b11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_6, InputEvent.SHIFT_DOWN_MASK, false), "pressCaret");
        b11.getActionMap().put("pressCaret", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("D#3", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b11.setIcon(pressedIcon11);
                playSound1("D#3.wav");
            }
        });

        b11.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_6, InputEvent.SHIFT_DOWN_MASK, true), "releaseCaret");
        b11.getActionMap().put("releaseCaret", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b11.setIcon(icon11);
            }
        });


    //////////////////////////////////////////////////////////////////// Button 7 \\\\\\\\\\\\\\\\\\\\\\\
        ImageIcon icon12 = new ImageIcon("image/button4.png");
        ImageIcon pressedIcon12 = new ImageIcon("image/sbutton4.png");
        int imgW12 = icon12.getIconWidth();
        int imgH12 = icon12.getIconHeight();

        b12 = new JButton(icon12);
        b12.setBounds(num1+imgW7+imgW9+imgW10+15+15, screenH-imgH1, imgW12, imgH12);
        b12.setBorderPainted(false);
        b12.setFocusPainted(false);
        b12.setContentAreaFilled(false);
        add(b12);

        b12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 7"), "press7");
        b12.getActionMap().put("press7", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("E3", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b12.setIcon(pressedIcon12);
                playSound("E3.wav");
            }
        });

        b12.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released 7"), "release7");
        b12.getActionMap().put("release7", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b12.setIcon(icon12);
            }
        });

        //////////////////////////////////////////////////////////////////// Button 8 \\\\\\\\\\\\\\\\\\\\\\\
        ImageIcon icon13 = new ImageIcon("image/button1.png");
        ImageIcon pressedIcon13 = new ImageIcon("image/sbutton1.png");
        int imgW13 = icon13.getIconWidth();
        int imgH13 = icon13.getIconHeight();

        b13 = new JButton(icon13);
        b13.setBounds(num1+imgW7+imgW9+imgW10+15+15+imgW12, screenH-imgH1, imgW13, imgH13);
        b13.setBorderPainted(false);
        b13.setFocusPainted(false);
        b13.setContentAreaFilled(false);
        add(b13);

        b13.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("pressed 8"), "press8");
        b13.getActionMap().put("press8", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("F3", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b13.setIcon(pressedIcon13);
                playSound("F3.wav");
            }
        });

        b13.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("released 8"), "release8");
        b13.getActionMap().put("release8", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b13.setIcon(icon13);
            }
        });

    //////////////////////////////////////////////////////////////////// Button # 8 \\\\\\\\\\\\\\\\\\\\\\\
        ImageIcon icon14 = new ImageIcon("image/button#1.png");
        ImageIcon pressedIcon14 = new ImageIcon("image/sbutton#1.png");
        int imgW14 = icon14.getIconWidth();
        int imgH14 = icon14.getIconHeight();

        b14 = new JButton(icon14);
        b14.setBounds(num1+imgW7+imgW9+imgW10+15+15+imgW12+imgW13/2+7, screenH-imgH1, imgW14, imgH14);
        b14.setBorderPainted(false);
        b14.setFocusPainted(false);
        b14.setContentAreaFilled(false);
        add(b14);

        b14.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_8, InputEvent.SHIFT_DOWN_MASK, false), "pressAsterisk");
        b14.getActionMap().put("pressAsterisk", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("F#3", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b14.setIcon(pressedIcon14);
                playSound1("F#3.wav");
            }
        });

        b14.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_8, InputEvent.SHIFT_DOWN_MASK, true), "releaseAsterisk");
        b14.getActionMap().put("releaseAsterisk", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b14.setIcon(icon14);
            }
        });


        //////////////////////////////////////////////////////////////////// Button 9 \\\\\\\\\\\\\\\\\\\\\\\
        ImageIcon icon15 = new ImageIcon("image/button2.png");
        ImageIcon pressedIcon15 = new ImageIcon("image/sbutton2.png");
        int imgW15 = icon15.getIconWidth();
        int imgH15 = icon15.getIconHeight();

        b15 = new JButton(icon15);
        b15.setBounds(num1+imgW7+imgW9+imgW10+15+15+imgW12+imgW13/2+imgW14-5, screenH - imgH1,imgW15, imgH15);
        b15.setBorderPainted(false);
        b15.setFocusPainted(false);
        b15.setContentAreaFilled(false);
        add(b15);

        b15.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_9, 0, false), "pressNine");
        b15.getActionMap().put("pressNine", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("G3", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b15.setIcon(pressedIcon15);
                playSound("G3.wav");
            }
        });

        b15.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_9, 0, true), "releaseNine");
        b15.getActionMap().put("releaseNine", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b15.setIcon(icon15);
            }
        });

        //////////////////////////////////////////////////////////////////// Button # 9 \\\\\\\\\\\\\\\\\\\\\\\
        ImageIcon icon16 = new ImageIcon("image/button#1.png");
        ImageIcon pressedIcon16 = new ImageIcon("image/sbutton#1.png");
        int imgW16 = icon16.getIconWidth();
        int imgH16 = icon16.getIconHeight();

        b16 = new JButton(icon16);
        b16.setBounds(num1 + imgW7 + imgW9 + imgW10 + 15 + 15 + imgW12 + imgW13 / 2 + imgW14 - 5 + imgW15/2+17,
                screenH - imgH1, imgW16, imgH16);
        b16.setBorderPainted(false);
        b16.setFocusPainted(false);
        b16.setContentAreaFilled(false);
        add(b16);

        b16.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_9, InputEvent.SHIFT_DOWN_MASK, false), "pressShiftNine");
        b16.getActionMap().put("pressShiftNine", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("G#3", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b16.setIcon(pressedIcon16);
                playSound1("G#3.wav");
            }
        });

        b16.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_9, InputEvent.SHIFT_DOWN_MASK, true), "releaseShiftNine");
        b16.getActionMap().put("releaseShiftNine", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b16.setIcon(icon16);
            }
        });

        //////////////////////////////////////////////////////////////////// Button 10 \\\\\\\\\\\\\\\\\\\\\\\
        ImageIcon icon17 = new ImageIcon("image/button3.png");
        ImageIcon pressedIcon17 = new ImageIcon("image/sbutton3.png");
        int imgW17 = icon17.getIconWidth();
        int imgH17 = icon17.getIconHeight();

        b17 = new JButton(icon17);
        b17.setBounds(num1 + imgW7 + imgW9 + imgW10 + 15 + 15 + imgW12 + imgW13 / 2 + imgW14 - 5 + imgW15 / 2 + 40,
                screenH - imgH1, imgW17, imgH17);
        b17.setBorderPainted(false);
        b17.setFocusPainted(false);
        b17.setContentAreaFilled(false);
        add(b17);

        b17.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_0, 0, false), "pressZero");
        b17.getActionMap().put("pressZero", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("A3", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b17.setIcon(pressedIcon17);
                playSound("A3.wav");
            }
        });

        b17.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_0, 0, true), "releaseZero");
        b17.getActionMap().put("releaseZero", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b17.setIcon(icon17);
            }
        });

      //////////////////////////////////////////////////////////////////// Button # 10 \\\\\\\\\\\\\\\\\\\\\\\
        ImageIcon icon18 = new ImageIcon("image/button#1.png");
        ImageIcon pressedIcon18 = new ImageIcon("image/sbutton#1.png");
        int imgW18 = icon18.getIconWidth();
        int imgH18 = icon18.getIconHeight();

        b18 = new JButton(icon18);
        b18.setBounds(num1 + imgW7 + imgW9 + imgW10 + 15 + 15 + imgW12 + imgW13 / 2 + imgW14 - 5 + imgW15 / 2 + 40+imgW17-12,
                screenH - imgH1, imgW18, imgH18);
        b18.setBorderPainted(false);
        b18.setFocusPainted(false);
        b18.setContentAreaFilled(false);
        add(b18);

        b18.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_0, InputEvent.SHIFT_DOWN_MASK, false), "pressShiftZero");
        b18.getActionMap().put("pressShiftZero", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("A#3", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b18.setIcon(pressedIcon18);
                playSound1("A#3.wav");
            }
        });

        b18.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_0, InputEvent.SHIFT_DOWN_MASK, true), "releaseShiftZero");
        b18.getActionMap().put("releaseShiftZero", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b18.setIcon(icon18);
            }
        });

        num1=num1 + imgW7 + imgW9 + imgW10 + 15 + 15 + imgW12 + imgW13 / 2 + imgW14 - 5 + imgW15 / 2 + 40;

         //////////////////////////////////////////////////////////////////// Button 11 \\\\\\\\\\\\\\\\\\\\\\\
        ImageIcon icon19 = new ImageIcon("image/button4.png");
        ImageIcon pressedIcon19 = new ImageIcon("image/sbutton4.png");
        int imgW19 = icon19.getIconWidth();
        int imgH19 = icon19.getIconHeight();

        b19 = new JButton(icon19);
        b19.setBounds(num1 +imgW18*2-12,screenH - imgH1, imgW19, imgH19);
        b19.setBorderPainted(false);
        b19.setFocusPainted(false);
        b19.setContentAreaFilled(false);
        add(b19);

        b19.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, false), "pressQ");
        b19.getActionMap().put("pressQ", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {

                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel("B3", SwingConstants.CENTER);
                label1.setForeground(Color.RED);
                label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                transparentPanel.revalidate();
                transparentPanel.repaint();

                transparentPanel.add(label1);

                b19.setIcon(pressedIcon19);
                playSound("B3.wav");
            }
        });

        b19.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0, true), "releaseQ");
        b19.getActionMap().put("releaseQ", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                b19.setIcon(icon19);
            }
        });


//////////////////////////////////////////////////////////////////// Button 12 \\\\\\\\\\\\\\\\\\\\\\\

ImageIcon icon20 = new ImageIcon("image/button1.png");
ImageIcon pressedIcon20 = new ImageIcon("image/sbutton1.png");
int imgW20 = icon20.getIconWidth();
int imgH20 = icon20.getIconHeight();

b20 = new JButton(icon20);
b20.setBounds(num1 + imgW18 * 2 - 12+imgW19+2, screenH - imgH1, imgW20, imgH20);
b20.setBorderPainted(false);
b20.setFocusPainted(false);
b20.setContentAreaFilled(false);
add(b20);

b20.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), "pressW");
b20.getActionMap().put("pressW", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("C4", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);

        b20.setIcon(pressedIcon20);
        playSound("C4.wav");
    }
});

b20.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), "releaseW");
b20.getActionMap().put("releaseW", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b20.setIcon(icon20);
    }
});


//////////////////////////////////////////////////////////////////// Button # 12 \\\\\\\\\\\\\\\\\\\\\\\

ImageIcon icon21 = new ImageIcon("image/button#1.png");
ImageIcon pressedIcon21 = new ImageIcon("image/sbutton#1.png");
int imgW21 = icon21.getIconWidth();
int imgH21 = icon21.getIconHeight();

b21 = new JButton(icon21);
b21.setBounds(num1 + imgW18 * 2 - 12 + imgW19 + 45, screenH - imgH1, imgW21, imgH21);
b21.setBorderPainted(false);
b21.setFocusPainted(false);
b21.setContentAreaFilled(false);
add(b21);

b21.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.SHIFT_DOWN_MASK, false), "pressWShift");
b21.getActionMap().put("pressWShift", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("C#4", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);

        b21.setIcon(pressedIcon21);
        playSound1("C#4.wav");
    }
});

b21.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.SHIFT_DOWN_MASK, true), "releaseWShift");
b21.getActionMap().put("releaseWShift", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b21.setIcon(icon21);
    }
});

//////////////////////////////////////////////////////////////////// Button 13 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon22 = new ImageIcon("image/mbutton.png");
ImageIcon pressedIcon22 = new ImageIcon("image/smbutton.png");
int imgW22 = icon22.getIconWidth();
int imgH22 = icon22.getIconHeight();

b22 = new JButton(icon22);
b22.setBounds(num1 + imgW18 * 2 - 12 + imgW19 + 75, screenH - imgH1, imgW22, imgH22);
b22.setBorderPainted(false);
b22.setFocusPainted(false);
b22.setContentAreaFilled(false);
add(b22);

b22.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, false), "pressE");
b22.getActionMap().put("pressE", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("D4", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);

        b22.setIcon(pressedIcon22);
        playSound("D4.wav");
    }
});

b22.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_E, 0, true), "releaseE");
b22.getActionMap().put("releaseE", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b22.setIcon(icon22);
    }
});


num1=num1 + imgW18 * 2 - 12 + imgW19 + 75;

//////////////////////////////////////////////////////////////////// Button # 13 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon23 = new ImageIcon("image/button#1.png");
ImageIcon pressedIcon23 = new ImageIcon("image/sbutton#1.png");
int imgW23 = icon23.getIconWidth();
int imgH23 = icon23.getIconHeight();

b23 = new JButton(icon23);
b23.setBounds(num1 + imgW22-15, screenH - imgH1, imgW23, imgH23);
b23.setBorderPainted(false);
b23.setFocusPainted(false);
b23.setContentAreaFilled(false);
add(b23);

b23.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_DOWN_MASK, false), "pressEShift");
b23.getActionMap().put("pressEShift", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("D#4", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);

        b23.setIcon(pressedIcon23);
        playSound1("D#4.wav");
    }
});

b23.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.SHIFT_DOWN_MASK, true), "releaseEShift");
b23.getActionMap().put("releaseEShift", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b23.setIcon(icon23);
    }
});


//////////////////////////////////////////////////////////////////// Button 14 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon24 = new ImageIcon("image/button4.png");
ImageIcon pressedIcon24 = new ImageIcon("image/sbutton4.png");
int imgW24 = icon24.getIconWidth();
int imgH24 = icon24.getIconHeight();

b24 = new JButton(icon24);
b24.setBounds(num1 + imgW22-2, screenH - imgH1, imgW24, imgH24);
b24.setBorderPainted(false);
b24.setFocusPainted(false);
b24.setContentAreaFilled(false);
add(b24);

b24.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_R, 0, false), "pressR");
b24.getActionMap().put("pressR", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("E4", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);

        b24.setIcon(pressedIcon24);
        playSound("E4.wav");
    }
});

b24.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_R, 0, true), "releaseR");
b24.getActionMap().put("releaseR", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b24.setIcon(icon24);
    }
});


//////////////////////////////////////////////////////////////////// Button 15 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon25 = new ImageIcon("image/button1.png");
ImageIcon pressedIcon25 = new ImageIcon("image/sbutton1.png");
int imgW25 = icon25.getIconWidth();
int imgH25 = icon25.getIconHeight();

b25 = new JButton(icon25);
b25.setBounds(num1 + imgW22+imgW24-1, screenH - imgH1, imgW25, imgH25);
b25.setBorderPainted(false);
b25.setFocusPainted(false);
b25.setContentAreaFilled(false);
add(b25);

b25.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_T, 0, false), "pressT");
b25.getActionMap().put("pressT", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("F4", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);

        b25.setIcon(pressedIcon25);
        playSound("F4.wav");
    }
});

b25.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_T, 0, true), "releaseT");
b25.getActionMap().put("releaseT", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b25.setIcon(icon25);
    }
});

//////////////////////////////////////////////////////////////////// Button # 15 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon26 = new ImageIcon("image/button#1.png");
ImageIcon pressedIcon26 = new ImageIcon("image/sbutton#1.png");
int imgW26 = icon26.getIconWidth();
int imgH26 = icon26.getIconHeight();

b26 = new JButton(icon26);
b26.setBounds(num1 + imgW22 + imgW24+imgW25/2+5, screenH - imgH1, imgW26, imgH26);
b26.setBorderPainted(false);
b26.setFocusPainted(false);
b26.setContentAreaFilled(false);
add(b26);

b26.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.SHIFT_DOWN_MASK, false), "pressTShift");
b26.getActionMap().put("pressTShift", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("F#4", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);

        b26.setIcon(pressedIcon26);
        playSound1("F#4.wav");
    }
});

b26.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_T, InputEvent.SHIFT_DOWN_MASK, true), "releaseTShift");
b26.getActionMap().put("releaseTShift", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b26.setIcon(icon26);
    }
});

//////////////////////////////////////////////////////////////////// Button 16 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon27 = new ImageIcon("image/button2.png");
ImageIcon pressedIcon27 = new ImageIcon("image/sbutton2.png");
int imgW27 = icon27.getIconWidth();
int imgH27 = icon27.getIconHeight();

b27 = new JButton(icon27);
b27.setBounds(num1 + imgW22 + imgW24 + imgW25 / 2 + 37, screenH - imgH1, imgW27, imgH27);
b27.setBorderPainted(false);
b27.setFocusPainted(false);
b27.setContentAreaFilled(false);
add(b27);

b27.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0, false), "pressY");
b27.getActionMap().put("pressY", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("G4", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);


        b27.setIcon(pressedIcon27);
        playSound("G4.wav");
    }
});

b27.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_Y, 0, true), "releaseY");
b27.getActionMap().put("releaseY", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b27.setIcon(icon27);
    }
});


//////////////////////////////////////////////////////////////////// Button # 16 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon28 = new ImageIcon("image/button#1.png");
ImageIcon pressedIcon28 = new ImageIcon("image/sbutton#1.png");
int imgW28 = icon28.getIconWidth();
int imgH28 = icon28.getIconHeight();

b28 = new JButton(icon28);
b28.setBounds(num1 + imgW22 + imgW24 + imgW25 / 2 + 37+ imgW27/2+17, screenH - imgH1, imgW28, imgH28);
b28.setBorderPainted(false);
b28.setFocusPainted(false);
b28.setContentAreaFilled(false);
add(b28);

b28.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.SHIFT_DOWN_MASK, false), "pressYShift");
b28.getActionMap().put("pressYShift", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("G#4", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);

        b28.setIcon(pressedIcon28);
        playSound1("G#4.wav");
    }
});

b28.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_Y, InputEvent.SHIFT_DOWN_MASK, true), "releaseYShift");
b28.getActionMap().put("releaseYShift", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b28.setIcon(icon28);
    }
});


//////////////////////////////////////////////////////////////////// Button 17 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon30 = new ImageIcon("image/button3.png");
ImageIcon pressedIcon30 = new ImageIcon("image/sbutton3.png");
int imgW30 = icon30.getIconWidth();
int imgH30 = icon30.getIconHeight();

b30 = new JButton(icon30);
b30.setBounds(num1 + imgW22 + imgW24 + imgW25 / 2 + 37 + imgW27 / 2 + 39, screenH - imgH1, imgW30, imgH30);
b30.setBorderPainted(false);
b30.setFocusPainted(false);
b30.setContentAreaFilled(false);
add(b30);

b30.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_U, 0, false), "pressU");
b30.getActionMap().put("pressU", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("A4", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);

        b30.setIcon(pressedIcon30);
        playSound("A4.wav");
    }
});

b30.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_U, 0, true), "releaseU");
b30.getActionMap().put("releaseU", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b30.setIcon(icon30);
    }
});

//////////////////////////////////////////////////////////////////// Button # 17 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon31 = new ImageIcon("image/button#1.png");
ImageIcon pressedIcon31 = new ImageIcon("image/sbutton#1.png");
int imgW31 = icon31.getIconWidth();
int imgH31 = icon31.getIconHeight();

b31 = new JButton(icon31);
b31.setBounds(num1 + imgW22 + imgW24 + imgW25 / 2 + 37 + imgW27 / 2 + 39+ imgW30-11, screenH - imgH1, imgW31, imgH31);
b31.setBorderPainted(false);
b31.setFocusPainted(false);
b31.setContentAreaFilled(false);
add(b31);

b31.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.SHIFT_DOWN_MASK, false), "pressUShift");
b31.getActionMap().put("pressUShift", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("A#4", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);


        b31.setIcon(pressedIcon31);
        playSound1("A#4.wav");
    }
});

b31.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.SHIFT_DOWN_MASK, true), "releaseUShift");
b31.getActionMap().put("releaseUShift", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b31.setIcon(icon31);
    }
});


//////////////////////////////////////////////////////////////////// Button 18 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon32 = new ImageIcon("image/button4.png");
ImageIcon pressedIcon32 = new ImageIcon("image/sbutton4.png");
int imgW32 = icon32.getIconWidth();
int imgH32 = icon32.getIconHeight();

b32 = new JButton(icon32);
b32.setBounds(num1 + imgW22 + imgW24 + imgW25 / 2 + 37 + imgW27 / 2 + 39+ imgW30, screenH - imgH1, imgW32, imgH32);
b32.setBorderPainted(false);
b32.setFocusPainted(false);
b32.setContentAreaFilled(false);
add(b32);

b32.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_I, 0, false), "pressI");
b32.getActionMap().put("pressI", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("B4", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);

        b32.setIcon(pressedIcon32);
        playSound("B4.wav");
    }
});

b32.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_I, 0, true), "releaseI");
b32.getActionMap().put("releaseI", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b32.setIcon(icon32);
    }
});


//////////////////////////////////////////////////////////////////// Button 19 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon33 = new ImageIcon("image/button1.png");
ImageIcon pressedIcon33 = new ImageIcon("image/sbutton1.png");
int imgW33 = icon33.getIconWidth();
int imgH33 = icon33.getIconHeight();

b33 = new JButton(icon33);
b33.setBounds(num1 + imgW22 + imgW24 + imgW25 / 2 + 37 + imgW27 / 2 + 39+ imgW30+imgW32+2, screenH - imgH1, imgW33, imgH33);
b33.setBorderPainted(false);
b33.setFocusPainted(false);
b33.setContentAreaFilled(false);
add(b33);

b33.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_O, 0, false), "pressO");
b33.getActionMap().put("pressO", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("C5", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);

        b33.setIcon(pressedIcon33);
        playSound("C5.wav");
    }
});

b33.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_O, 0, true), "releaseO");
b33.getActionMap().put("releaseO", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b33.setIcon(icon33);
    }
});

num1=num1 + imgW22 + imgW24 + imgW25 / 2 + 37 + imgW27 / 2 + 39 + imgW30 + imgW32 + 2;

//////////////////////////////////////////////////////////////////// Button # 19 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon34 = new ImageIcon("image/button#1.png");
ImageIcon pressedIcon34 = new ImageIcon("image/sbutton#1.png");
int imgW34 = icon34.getIconWidth();
int imgH34 = icon34.getIconHeight();

b34 = new JButton(icon34);
b34.setBounds(num1 + imgW33/2+6, screenH - imgH1, imgW34, imgH34);
b34.setBorderPainted(false);
b34.setFocusPainted(false);
b34.setContentAreaFilled(false);
add(b34);

b34.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.SHIFT_DOWN_MASK, false), "pressShiftO");
b34.getActionMap().put("pressShiftO", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {


        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("C#5", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);

        b34.setIcon(pressedIcon34);
        playSound1("C#5.wav");
    }
});

b34.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.SHIFT_DOWN_MASK, true), "releaseShiftO");
b34.getActionMap().put("releaseShiftO", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b34.setIcon(icon34);
    }
});

//////////////////////////////////////////////////////////////////// Button 20 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon35 = new ImageIcon("image/mbutton.png");
ImageIcon pressedIcon35 = new ImageIcon("image/smbutton.png");
int imgW35 = icon35.getIconWidth();
int imgH35 = icon35.getIconHeight();

b35 = new JButton(icon35);
b35.setBounds(num1 + imgW33 / 2 + 26, screenH - imgH1, imgW35, imgH35);
b35.setBorderPainted(false);
b35.setFocusPainted(false);
b35.setContentAreaFilled(false);
add(b35);

b35.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, false), "pressP");
b35.getActionMap().put("pressP", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("D5", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);

        b35.setIcon(pressedIcon35);
        playSound("D5.wav");
    }
});

b35.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_P, 0, true), "releaseP");
b35.getActionMap().put("releaseP", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b35.setIcon(icon35);
    }
});


//////////////////////////////////////////////////////////////////// Button # 20 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon36 = new ImageIcon("image/button#1.png");
ImageIcon pressedIcon36 = new ImageIcon("image/sbutton#1.png");
int imgW36 = icon36.getIconWidth();
int imgH36 = icon36.getIconHeight();

b36 = new JButton(icon36);
b36.setBounds(num1 + imgW33 / 2 + 26+imgW35-15, screenH - imgH1, imgW36, imgH36);
b36.setBorderPainted(false);
b36.setFocusPainted(false);
b36.setContentAreaFilled(false);
add(b36);

b36.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_DOWN_MASK, false), "pressShiftP");
b36.getActionMap().put("pressShiftP", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("D#5", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();

        transparentPanel.add(label1);


        b36.setIcon(pressedIcon36);
        playSound1("D#5.wav");
    }
});

b36.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.SHIFT_DOWN_MASK, true), "releaseShiftP");
b36.getActionMap().put("releaseShiftP", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b36.setIcon(icon36);
    }
});


//////////////////////////////////////////////////////////////////// Button 21 \\\\\\\\\\\\\\\\\\\\\\\
ImageIcon icon37 = new ImageIcon("image/button4.png");
ImageIcon pressedIcon37 = new ImageIcon("image/sbutton4.png");
int imgW37 = icon37.getIconWidth();
int imgH37 = icon37.getIconHeight();

b37 = new JButton(icon37);
b37.setBounds(num1 + imgW33 / 2 + 26 + imgW35 - 15, screenH - imgH1, imgW37, imgH37);
b37.setBorderPainted(false);
b37.setFocusPainted(false);
b37.setContentAreaFilled(false);
add(b37);

b37.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), "pressA");
b37.getActionMap().put("pressA", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {

        if (label1 != null) {
            transparentPanel.remove(label1);
        }

        label1 = new JLabel("E5", SwingConstants.CENTER);
        label1.setForeground(Color.RED);
        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
        transparentPanel.revalidate();
        transparentPanel.repaint();
        transparentPanel.add(label1);

        b37.setIcon(pressedIcon37);
        playSound("E5.wav");
    }
});

b37.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), "releaseA");
b37.getActionMap().put("releaseA", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
        b37.setIcon(icon37);
    }
});


add(boardLabel);

///////////// Close Button
int resizedWidth = 30, resizedHeight = 30;

ImageIcon icon40 = new ImageIcon(new ImageIcon("image/close01.png")
        .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
ImageIcon pressedIcon40 = new ImageIcon(new ImageIcon("image/close0.png")
        .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

exit = new JButton(icon40);
exit.setBounds(screenW-35, 5, resizedWidth, resizedHeight);
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


resizedHeight =resizedHeight +60;
resizedWidth = resizedWidth+60;


///////////// Audio Recording Button
ImageIcon icon43 = new ImageIcon(new ImageIcon("image/Rec000.png")
        .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
         pressedIcon43 = new ImageIcon(new ImageIcon("image/REC003.png")
        .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

rec = new JButton(icon43);
rec.setBounds(screenW-180, screenH/5-customHeight/3, resizedWidth, resizedHeight);
rec.setBorderPainted(false);
rec.setFocusPainted(false);
rec.setContentAreaFilled(false);
rec.setRolloverIcon(pressedIcon43);
add(rec);

rec.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        rec.setIcon(pressedIcon43);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        rec.setIcon(icon43);
        new recording();
        setVisible(false);
    }
});

rec.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK), "ctrlR");
rec.getActionMap().put("ctrlR", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
       new recording();
       setVisible(false);
    }
});



///////////// Music Button
ImageIcon icon44 = new ImageIcon(new ImageIcon("image/Music000.png")
        .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
         pressedIcon44 = new ImageIcon(new ImageIcon("image/Music003.png")
        .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

mu = new JButton(icon44);
mu.setBounds(screenW-150-resizedWidth-100, screenH/5-customHeight/3, resizedWidth, resizedHeight);
mu.setBorderPainted(false);
mu.setFocusPainted(false);
mu.setContentAreaFilled(false);
mu.setRolloverIcon(pressedIcon44);
add(mu);

mu.addMouseListener(new MouseAdapter() {
    @Override
    public void mousePressed(MouseEvent e) {
        mu.setIcon(pressedIcon44);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mu.setIcon(icon44);
        new MusicPlayer();
        setVisible(false);
    }
});

mu.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
        KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK), "ctrlM");
mu.getActionMap().put("ctrlM", new AbstractAction() {
    public void actionPerformed(ActionEvent e) {
       new MusicPlayer();
       setVisible(false);
    }
});


        ///////////// Keys Button
        ImageIcon icon45 = new ImageIcon(new ImageIcon("image/Keys000.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        pressedIcon45 = new ImageIcon(new ImageIcon("image/Keys003.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

        ke = new JButton(icon45);
        ke.setBounds(30, screenH / 5 - customHeight / 3, resizedWidth, resizedHeight);
        ke.setBorderPainted(false);
        ke.setFocusPainted(false);
        ke.setContentAreaFilled(false);
        ke.setRolloverIcon(pressedIcon45);
        add(ke);

        ke.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                ke.setIcon(pressedIcon45);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                ke.setIcon(icon45);
                new keysIndicator();
                setVisible(false);
            }
        });

        ke.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK), "ctrlK");
        ke.getActionMap().put("ctrlK", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                new keysIndicator();
                setVisible(false);
            }
        });


        ///// Volume Button
        ImageIcon icon46 = new ImageIcon(new ImageIcon("image/Sound001.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        pressedIcon46 = new ImageIcon(new ImageIcon("image/Sound002.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

        vo = new JButton(icon46);
        vo.setBounds(resizedWidth+65, screenH / 5 - customHeight / 3, resizedWidth, resizedHeight);
        vo.setBorderPainted(false);
        vo.setFocusPainted(false);
        vo.setContentAreaFilled(false);
        vo.setRolloverIcon(pressedIcon46);
        add(vo);

        vo.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                vo.setIcon(pressedIcon46);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                vo.setIcon(icon46);
                new volume();
                setVisible(false);
            }
        });

        vo.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_V, InputEvent.CTRL_DOWN_MASK), "ctrlV");
        vo.getActionMap().put("ctrlV", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                new volume();
                setVisible(false);
            }
        });


        ///// Input File Button
        ImageIcon icon47 = new ImageIcon(new ImageIcon("image/input01.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        pressedIcon47 = new ImageIcon(new ImageIcon("image/input02.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

        in = new JButton(icon47);
        in.setBounds(resizedWidth+70+resizedWidth+30, screenH / 5 - customHeight / 3, resizedWidth, resizedHeight);
        in.setBorderPainted(false);
        in.setFocusPainted(false);
        in.setContentAreaFilled(false);
        in.setRolloverIcon(pressedIcon47);
        add(in);

        in.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                in.setIcon(pressedIcon47);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                in.setIcon(icon47);
                new inputFile();
                setVisible(false);
            }
        });

        in.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
                KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK), "ctrlI");
        in.getActionMap().put("ctrlI", new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                new inputFile();
                setVisible(false);
            }
        });



    // Frame setup
        ImageIcon originalIcon = new ImageIcon("image/gray2.jpg");
        Image scaledImage = originalIcon.getImage().getScaledInstance(screenW, 650, Image.SCALE_SMOOTH);
        ImageIcon backgroundImage = new ImageIcon(scaledImage);
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, screenW, 650);
        setLayout(null);
        getContentPane().add(backgroundLabel);

        setUndecorated(true);
        setBounds(0, 0, screenW, screenH);
        setLocationRelativeTo(null);
        setVisible(true);
    }


public void actionPerformed(ActionEvent e) {

        // Parity Bit
        if (e.getSource() == b1) {

            System.out.println("rakib");
            
            JLabel label = new JLabel("Your Text Here");
            label.setBounds(10, 10, 180, 30);
            label.setForeground(Color.pink); 
            label.setBackground(Color.red);
            transparentPanel.add(label);
            

        }
    }



  // // // Method to play .wav sound
  public static void playSound(String filename) {
        new Thread(() -> {
            try {
                File audioFile = new File("sound/Set 1/" + filename);
                if (!audioFile.exists()) return;

                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float volume = currentVolume[0]; // 0–100
                float dB = (float) (Math.log10(volume == 0 ? 0.0001 : volume / 100.0) * 20);
                gainControl.setValue(dB);

                clip.start();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }).start();
    }



   // // // Method to play .wav sound 1
  public static void playSound1(String filename) {
        new Thread(() -> {
            try {
                File audioFile = new File("sound/Set 2 #/" + filename);
                if (!audioFile.exists()) return;

                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);

                FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                float volume = currentVolume[0];
                float dB = (float) (Math.log10(volume == 0 ? 0.0001 : volume / 100.0) * 20);
                gainControl.setValue(dB);

                clip.start();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        }).start();
    }

}
