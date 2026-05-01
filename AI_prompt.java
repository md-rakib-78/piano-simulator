import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;

public class AI_prompt extends JFrame implements ActionListener{

        JButton exit, mini,re,dh,dn,in;
        JTextArea promtArea,viewText;
        private JLabel loader;

        
   
    AI_prompt(){
         Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenW = screenSize.width;
        int screenH = screenSize.height;

         int resizedWidth = 30, resizedHeight = 30;

        try {
             BufferedImage image = ImageIO.read(new File("image/gen01.png"));

            int width = image.getWidth();
            int height = image.getHeight();
            resizedWidth=width-90;
            resizedHeight=height-30;
            
        } catch (Exception e) {
         
        }



        ///////////// Generate Button
        ImageIcon icon42 = new ImageIcon(new ImageIcon("image/gen01.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        ImageIcon pressedIcon42 = new ImageIcon(new ImageIcon("image/gen03.png")
                .getImage().getScaledInstance(resizedWidth-6, resizedHeight-2, Image.SCALE_SMOOTH));

        dh = new JButton(icon42);
       dh.setBounds((screenW - screenH / 2 - 100)-115, 405, resizedWidth, resizedHeight);
        dh.setBorderPainted(false);
        dh.setFocusPainted(false);
        dh.setContentAreaFilled(false);
        dh.setRolloverIcon(pressedIcon42);
        dh.addActionListener(this);
        add(dh);



//////////// Input Prompt /////////////////////////////////////
        promtArea = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Draw placeholder only when empty and not focused
                if (getText().isEmpty() && !isFocusOwner()) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g2.setColor(new Color(0, 0, 0, 100)); // Transparent gray
                    g2.setFont(getFont().deriveFont(Font.ITALIC));
                    g2.drawString("Mention your piano melody need @ ", 10, 25); // Adjust as needed
                }
            }
        };

        promtArea.setEditable(true); // make it writeable
        promtArea.setFont(new Font("Arial", Font.PLAIN, 18));
        promtArea.setBackground(Color.decode("#fbebfc"));
        promtArea.setForeground(Color.BLACK);
        promtArea.setLineWrap(true);
        promtArea.setWrapStyleWord(true);
        promtArea.setOpaque(false);
        promtArea.setBorder(new EmptyBorder(10, 10, 10, resizedWidth+20));

        // Rounded border (inner and outer both smooth)
        JScrollPane scrollPane = new JScrollPane(promtArea) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#fbebfc")); // Fill smooth background
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            }
        };
        scrollPane.setViewportBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBounds(50, 400, screenW - screenH / 2 - 100, 60);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        // Slim GPT-like scroll bar
        scrollPane.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(180, 180, 180);
            }

            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(6, super.getPreferredSize(c).height); // slim
            }
        });

        // Right-click copy menu
        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem copyItem = new JMenuItem("Copy");
        copyItem.addActionListener(e -> promtArea.copy());
        popupMenu.add(copyItem);
        promtArea.setComponentPopupMenu(popupMenu);

        add(scrollPane);



viewText = new JTextArea() {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (getText().isEmpty() && !isFocusOwner()) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Get dynamic font size (20% of height)
            int fontSize = Math.max(8, getHeight() / 7);
            g2.setFont(new Font("Arial", Font.ITALIC, fontSize));

            String placeholder = "Hi! what should we dive into today?";

            // Get text width & height
            FontMetrics fm = g2.getFontMetrics();
            int textWidth = fm.stringWidth(placeholder);
            int textAscent = fm.getAscent();

            // Center X & Y
            int x = (getWidth() - textWidth) / 2;
            int y = (getHeight() + textAscent) / 2 - 4;

            g2.setColor(Color.decode("#cccccc"));  // soft transparent
            g2.drawString(placeholder, x, y);
        }
    }
};


        viewText.setEditable(true); // make it writeable
        viewText.setFont(new Font("Arial", Font.PLAIN, 18));
        viewText.setBackground(Color.decode("#cccccc"));
        viewText.setForeground(Color.decode("#444444"));
        viewText.setLineWrap(true);
        viewText.setWrapStyleWord(true);
        viewText.setOpaque(false);
        viewText.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Rounded border (inner and outer both smooth)
        JScrollPane scrollPane1 = new JScrollPane(viewText) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.decode("#ffffff")); // Fill smooth background
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 25, 25);
            }
        };
        scrollPane1.setViewportBorder(null);
        scrollPane1.setOpaque(false);
        scrollPane1.getViewport().setOpaque(false);
        scrollPane1.setBounds(50, 70, screenW - screenH / 2 - 100, 350);
        scrollPane1.setBorder(BorderFactory.createEmptyBorder());

        // Slim GPT-like scroll bar
        scrollPane1.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = new Color(180, 180, 180);
            }

            @Override
            public Dimension getPreferredSize(JComponent c) {
                return new Dimension(6, super.getPreferredSize(c).height); // slim
            }
        });

        // Right-click copy menu
        JPopupMenu popupMenu1 = new JPopupMenu();
        JMenuItem copyItem1 = new JMenuItem("Copy");
        copyItem1.addActionListener(e -> viewText.copy());
        popupMenu1.add(copyItem1);
        viewText.setComponentPopupMenu(popupMenu1);
        add(scrollPane1);







          // Frame dimensions
        int frameWidth =  screenW - screenH/2;
        int frameHeight = screenH - (screenH / 2);
        setBounds(0, 0, frameWidth, frameHeight);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(null);
        topPanel.setBackground(Color.decode("#282827"));
        topPanel.setBounds(0, 0, frameWidth, 40);
        add(topPanel);

        JLabel title = new JLabel("Ai Generate Piano Melody ");
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.decode("#cccccc"));
        title.setBounds(20, 5, frameWidth, 30);
        topPanel.add(title);

     
        resizedWidth = 30;
        resizedHeight = 30;

        ///////////// Close Button
        ImageIcon icon40 = new ImageIcon(new ImageIcon("image/close01.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        ImageIcon pressedIcon40 = new ImageIcon(new ImageIcon("image/close0.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

        exit = new JButton(icon40);
        exit.setBounds(screenW - screenH/2-resizedWidth-5,5, resizedWidth, resizedHeight);
        exit.setBorderPainted(false);
        exit.setFocusPainted(false);
        exit.setContentAreaFilled(false);
        exit.setRolloverIcon(pressedIcon40);
        exit.addActionListener(this);
        topPanel.add(exit);


        ///////////// Minimize Button
        ImageIcon icon41 = new ImageIcon(new ImageIcon("image/mini0.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        ImageIcon pressedIcon41 = new ImageIcon(new ImageIcon("image/mini01.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));

        mini = new JButton(icon41);
        mini.setBounds(screenW - screenH/2-resizedWidth*2-10,5, resizedWidth, resizedHeight);
        mini.setBorderPainted(false);
        mini.setFocusPainted(false);
        mini.setContentAreaFilled(false);
        mini.setRolloverIcon(pressedIcon41);
        mini.addActionListener(this);
        topPanel.add(mini);


        try {
             BufferedImage image = ImageIO.read(new File("image/gen01.png"));

            int width = image.getWidth();
            int height = image.getHeight();
            resizedWidth=width-130;
            resizedHeight=height-45;
            
        } catch (Exception e) {
         
        }



        ///////////// Refresh Button
        ImageIcon icon43 = new ImageIcon(new ImageIcon("image/ref002.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        ImageIcon pressedIcon43 = new ImageIcon(new ImageIcon("image/ref001.png")
                .getImage().getScaledInstance(resizedWidth-6, resizedHeight-2, Image.SCALE_SMOOTH));

        re = new JButton(icon43);
        re.setBounds(50, 500, resizedWidth, resizedHeight);
        re.setBorderPainted(false);
        re.setFocusPainted(false);
        re.setContentAreaFilled(false);
        re.setRolloverIcon(pressedIcon43);
        re.addActionListener(this);
        add(re);


        ///////////// Download txt Button
        ImageIcon icon44 = new ImageIcon(new ImageIcon("image/download01.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        ImageIcon pressedIcon44 = new ImageIcon(new ImageIcon("image/download02.png")
                .getImage().getScaledInstance(resizedWidth-6, resizedHeight-2, Image.SCALE_SMOOTH));

        dn = new JButton(icon44);
        dn.setBounds(80+resizedWidth, 500, resizedWidth, resizedHeight);
        dn.setBorderPainted(false);
        dn.setFocusPainted(false);
        dn.setContentAreaFilled(false);
        dn.setRolloverIcon(pressedIcon44);
        dn.addActionListener(this);
        add(dn);

        ///////////// Input Sequence in pianoboard Button
        ImageIcon icon45 = new ImageIcon(new ImageIcon("image/input piano01.png")
                .getImage().getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH));
        ImageIcon pressedIcon45 = new ImageIcon(new ImageIcon("image/input piano02.png")
                .getImage().getScaledInstance(resizedWidth-6, resizedHeight-2, Image.SCALE_SMOOTH));

        in = new JButton(icon45);
        in.setBounds(115+resizedWidth+resizedWidth, 500, resizedWidth, resizedHeight);
        in.setBorderPainted(false);
        in.setFocusPainted(false);
        in.setContentAreaFilled(false);
        in.setRolloverIcon(pressedIcon45);
        in.addActionListener(this);
        add(in);

        /// Dot Typing after click generate
        loader = new JLabel(
                "<html><img src='file:image/typing.gif' width='60' height='45'></html>");
        loader.setBounds(10, 10, 60, 45);
        loader.setOpaque(false);
        loader.setVisible(false);
        scrollPane1.add(loader);




        setUndecorated(true);
        getContentPane().setBackground(Color.decode("#900C3F"));
        setLayout(null);
        setBounds(0, 0, screenW - screenH/2, screenH - (screenH / 3));
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        // Close tab
        if (e.getSource() == exit) {
            setVisible(false);
            
        }
        // generate text
        else if (e.getSource() == dh) {
            generateText();
  
        }
        //Sequence put the piano board
        else if(e.getSource()==in)
        {
            setVisible(false);
            String s=viewText.getText();

            if (s != "") {
                ArrayList<String> list = new ArrayList<>();

                // Regex: (Note pattern) OR (single dot)
                Matcher matcher = Pattern.compile("([A-G]#?\\d)|\\.").matcher(s);

                while (matcher.find()) {
                    list.add(matcher.group());
                }
                new inputFile(list);
                

            }

        }

        // Refresh
        else if (e.getSource() == re) {
            setVisible(false);
            new AI_prompt();
            
        }

    }



private void generateText() {

    loader.setVisible(true);
    viewText.setText(""); 
    String prompt = promtArea.getText();
    promtArea.setText("");

    SwingWorker<String, Void> worker = new SwingWorker<>() {
        @Override
        protected String doInBackground() {
            // heavy task runs in background
            String emotion = "happy motivational";
            String duration = "60-second sequence";

            PianoSequenceGenerator obj = new PianoSequenceGenerator();
            return obj.generatePianoSequence(prompt, emotion, duration);
        }

        @Override
        protected void done() {
            try {
                String result = get();
                loader.setVisible(false);  // hide GIF
                viewText.setText(result);  // show result
            } catch (Exception ex) {
                loader.setVisible(false);
                viewText.setText("Error generating text!");
            }
        }
    };

    worker.execute();
}




    public static void main(String[] args) {
        new AI_prompt();
    }


}
