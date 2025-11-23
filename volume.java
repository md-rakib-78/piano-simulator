import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import javazoom.jlgui.basicplayer.BasicController;
import javazoom.jlgui.basicplayer.BasicPlayerEvent;
import javazoom.jlgui.basicplayer.BasicPlayerListener;
import java.awt.*;
import java.awt.event.*;
import java.util.Hashtable;
import java.util.Map;

public class volume extends PianoBoard implements BasicPlayerListener {

    public volume() {
        vo.setIcon(pressedIcon46);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenH = screenSize.height;
        ImageIcon boardImg = new ImageIcon("image/board.png");
        Image img = boardImg.getImage();
        int height = img.getHeight(null);
        JPanel volumePanel = getVolumeSliderPanel();
        volumePanel.setBounds(20, transparentPanel.getHeight() + (screenH / 5) - (height / 2) / 2 - 75, 360, 100);
        volumePanel.setForeground(Color.decode("#262626"));
        add(volumePanel);
    }

    public JPanel getVolumeSliderPanel() {
        JSlider slider = new JSlider(0, 100, currentVolume[0]);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(false);
        slider.setBackground(new Color(30, 30, 30));
        slider.setForeground(Color.PINK);
    
        slider.setUI(new BasicSliderUI(slider) {
            private final int LINE_WIDTH = 8;
            private final int LINE_HEIGHT = 20;

            @Override
            protected Dimension getThumbSize() {
                return new Dimension(LINE_WIDTH, LINE_HEIGHT);
            }

            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.RED);
                int x = thumbRect.x + thumbRect.width / 2 - LINE_WIDTH / 2;
                int y = thumbRect.y;
                g2.fillRect(x, y, LINE_WIDTH, LINE_HEIGHT);
                g2.dispose();
            }

            @Override
            public void paintTrack(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(Color.GRAY);
                int trackY = trackRect.y + trackRect.height / 2 - 2;
                g2.fillRoundRect(trackRect.x, trackY, trackRect.width, 5, 4, 4);
                g2.dispose();
            }

            @Override
            public void paintTicks(Graphics g) {
                g.setColor(Color.white);
                int yPos = trackRect.y + trackRect.height + 5;
                for (int i = slider.getMinimum(); i <= slider.getMaximum(); i += slider.getMinorTickSpacing()) {
                    int x = xPositionForValue(i);
                    g.drawLine(x, yPos, x, yPos + 5);
                }
                for (int i = slider.getMinimum(); i <= slider.getMaximum(); i += slider.getMajorTickSpacing()) {
                    int x = xPositionForValue(i);
                    g.drawLine(x, yPos, x, yPos + 10);
                }
            }

            @Override
            public void paintFocus(Graphics g) {
            }
        });

        slider.setOpaque(true);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        for (int i = 0; i <= 100; i += 10) {
            JLabel label = new JLabel(String.valueOf(i));
            label.setForeground(Color.lightGray);
            label.setFont(new Font("Dutch801 Rm BT", Font.PLAIN, 12));
            labelTable.put(i, label);
        }
        slider.setLabelTable(labelTable);

        JTextField volumeLabel = new JTextField(currentVolume[0] + "%");
        volumeLabel.setForeground(Color.lightGray);
        volumeLabel.setFont(new Font("Dutch801 Rm BT", Font.PLAIN, 19));
        volumeLabel.setPreferredSize(new Dimension(60, 30));
        volumeLabel.setBackground(new Color(30, 30, 30));
        volumeLabel.setBorder((BorderFactory.createMatteBorder(0, 0, 0, 0, Color.lightGray)));
        volumeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        int s = Integer.parseInt(volumeLabel.getText().replace("%", ""));
        currentVolume[0] = s;

        volumeLabel.addActionListener(e -> {
            try {
                int enteredVolume = Integer.parseInt(volumeLabel.getText().replace("%", ""));
                enteredVolume = Math.max(0, Math.min(100, enteredVolume));
                slider.setValue(enteredVolume);
            } catch (NumberFormatException ex) {
                volumeLabel.setText(currentVolume[0] + "%");
            }
        });

        slider.addChangeListener(e -> {
            currentVolume[0] = slider.getValue();
            volumeLabel.setText(currentVolume[0] + "%");
        });

        slider.addMouseWheelListener(e -> {
            int notches = e.getWheelRotation();
            int newVolume = slider.getValue() - notches * 1;
            newVolume = Math.max(0, Math.min(100, newVolume));
            slider.setValue(newVolume);
        });

        JLabel titleLabel = new JLabel("Volume");
        titleLabel.setForeground(Color.LIGHT_GRAY);
        titleLabel.setFont(new Font("Dutch801 Rm BT", Font.BOLD, 20));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);

        JPanel sliderPanel = new JPanel(new BorderLayout(10, 0));
        sliderPanel.setBackground(new Color(30, 30, 30));
        sliderPanel.add(volumeLabel, BorderLayout.WEST);
        sliderPanel.add(slider, BorderLayout.CENTER);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 10));
        panel.setBackground(new Color(30, 30, 30));
        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(sliderPanel, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(370, 100));

        JDialog dialog = new JDialog(this, false);
        dialog.setBackground(new Color(30, 30, 30));
        dialog.setForeground(Color.CYAN);
        dialog.getContentPane().add(panel);
        dialog.pack();

        dialog.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                dialog.dispose();
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
            }
        });

        dialog.setLocationRelativeTo(this);
        dialog.setVisible(false);
        dialog.requestFocus();

        return panel;
    }

    public static void main(String[] args) {
        new volume();
    }

    @Override
    public void opened(Object arg0, Map arg1) {
        throw new UnsupportedOperationException("Unimplemented method 'opened'");
    }

    @Override
    public void progress(int arg0, long arg1, byte[] arg2, Map arg3) {
        throw new UnsupportedOperationException("Unimplemented method 'progress'");
    }

    @Override
    public void setController(BasicController arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'setController'");
    }

    @Override
    public void stateUpdated(BasicPlayerEvent arg0) {
        throw new UnsupportedOperationException("Unimplemented method 'stateUpdated'");
    }
}
