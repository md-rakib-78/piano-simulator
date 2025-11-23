import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javazoom.jlgui.basicplayer.*;

public class MusicPlayer extends PianoBoard implements BasicPlayerListener {

    private JButton playPauseButton, pageUpButton, pageDownButton, chooseFileButton, volumeButton;
    private ImageIcon playIcon, pauseIcon, upIcon, downIcon, chooseIcon, chooseIconPressed, volumeIcon, volumeIcon1;
    static int currentVolume = 50;

    private List<File> musicFiles = new ArrayList<>();
    private int currentTrackIndex = 0;

    private BasicPlayer basicPlayer = new BasicPlayer();

    private enum PlayerStatus { STOPPED, PLAYING, PAUSED }
    private PlayerStatus status = PlayerStatus.STOPPED;

    private boolean hasFilesLoaded = false;

    static int newVolume=50;

    private JLabel timeLabel;
    private Timer timer;
    private long currentMicroseconds = 0;
    private long totalMicroseconds = 0;

    public MusicPlayer() {
        super();
        mu.setIcon(pressedIcon44);
        if (label2 != null) {
            transparentPanel1.remove(label2);
        }
        basicPlayer.addBasicPlayerListener(this);
        setupButtons();
        setupTimeLabel();
        setupTimer();
        setVisible(true);
    }

    private void setupButtons() {
        int width = 40, height = 40;
        int panelWidth = transparentPanel1.getWidth();
        int centerX = (panelWidth / 2) - (width / 2);

        playIcon = new ImageIcon(new ImageIcon("image/off.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        pauseIcon = new ImageIcon(new ImageIcon("image/play.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        upIcon = new ImageIcon(new ImageIcon("image/pageup.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        downIcon = new ImageIcon(new ImageIcon("image/pagedown.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        chooseIcon = new ImageIcon(new ImageIcon("image/file.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        chooseIconPressed = new ImageIcon(new ImageIcon("image/file1.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        volumeIcon = new ImageIcon(new ImageIcon("image/volume.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        volumeIcon1 = new ImageIcon(new ImageIcon("image/volume1.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

        playPauseButton = new JButton(playIcon);
        playPauseButton.setBounds(centerX, 60, width, height);
        styleButton(playPauseButton);
        transparentPanel1.add(playPauseButton);

        pageDownButton = new JButton(downIcon);
        pageDownButton.setBounds(centerX - 60, 60, width, height);
        styleButton(pageDownButton);
        transparentPanel1.add(pageDownButton);

        pageUpButton = new JButton(upIcon);
        pageUpButton.setBounds(centerX + 60, 60, width, height);
        styleButton(pageUpButton);
        transparentPanel1.add(pageUpButton);

        chooseFileButton = new JButton(chooseIcon);
        chooseFileButton.setBounds(centerX + 120, 60, width, height);
        chooseFileButton.setRolloverIcon(chooseIconPressed);
        styleButton(chooseFileButton);
        transparentPanel1.add(chooseFileButton);

        volumeButton = new JButton(volumeIcon);
        volumeButton.setBounds(centerX - 120, 60, width, height);
        volumeButton.setRolloverIcon(volumeIcon1);
        styleButton(volumeButton);
        transparentPanel1.add(volumeButton);

        volumeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                volumeButton.setIcon(volumeIcon1);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                volumeButton.setIcon(volumeIcon);
            }
        });

        chooseFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                chooseFileButton.setIcon(chooseIconPressed);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                chooseFileButton.setIcon(chooseIcon);
            }
        });

        playPauseButton.addActionListener(e -> {
            if (!hasFilesLoaded) return;
            try {
                if (status == PlayerStatus.PLAYING) {
                    pauseAudio();
                } else if (status == PlayerStatus.PAUSED) {
                    resumeAudio();
                } else {
                    playAudio();
                }
            } catch (BasicPlayerException ex) {
                ex.printStackTrace();
            }
        });

        pageUpButton.addActionListener(e -> {
            if (!musicFiles.isEmpty()) {
                try {
                    stopAudio();
                    nextTrack();
                } catch (BasicPlayerException ex) {
                    ex.printStackTrace();
                }
            }
        });

        pageDownButton.addActionListener(e -> {
            if (!musicFiles.isEmpty()) {
                try {
                    stopAudio();
                    previousTrack();
                } catch (BasicPlayerException ex) {
                    ex.printStackTrace();
                }
            }
        });

        chooseFileButton.addActionListener(e -> chooseFiles());
        volumeButton.addActionListener(e -> showVolumeSlider());
    }


private void chooseFiles() {
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
        e.printStackTrace();
    }

    JFrame frame = new JFrame();
    frame.setTitle("Select MP3 Files");
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    frame.setIconImage(new ImageIcon(getClass().getResource("/image/icon1.png")).getImage());
    frame.setSize(0, 0);
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Select MP3 Files");
    chooser.setMultiSelectionEnabled(true);
    chooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("MP3 Files", "mp3"));
    chooser.setAcceptAllFileFilterUsed(false);
    chooser.setCurrentDirectory(new File(System.getProperty("user.home")));

    int result = chooser.showOpenDialog(frame);

    if (result == JFileChooser.APPROVE_OPTION) {
        try {
            stopAudio();
        } catch (BasicPlayerException e) {
            e.printStackTrace();
        }

        musicFiles.clear();
        File[] selected = chooser.getSelectedFiles();
        for (File file : selected) {
            musicFiles.add(file);
        }

        if (!musicFiles.isEmpty()) {
            hasFilesLoaded = true;
            currentTrackIndex = 0;
            try {
                playAudio();
            } catch (BasicPlayerException e) {
                e.printStackTrace();
            }
            playPauseButton.setIcon(pauseIcon);
            //JOptionPane.showMessageDialog(frame, "You selected:\n" + selected.length + " file(s)", "Files Chosen", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

    frame.dispose();
}


    private void showVolumeSlider() {
        JSlider slider = new JSlider(0, 100, currentVolume);
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(false);
        slider.setPaintLabels(false);
        slider.setBackground(new Color(40, 40, 40));
        slider.setForeground(new Color(0, 200, 255));

        slider.setUI(new BasicSliderUI(slider) {
            private final int THUMB_SIZE = 16;

            @Override
            protected Dimension getThumbSize() {
                return new Dimension(THUMB_SIZE, THUMB_SIZE);
            }

            @Override
            public void paintThumb(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(0, 200, 255));
                int x = thumbRect.x;
                int y = thumbRect.y;
                g2.fillOval(x, y, THUMB_SIZE, THUMB_SIZE);
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
            public void paintFocus(Graphics g) {
            }
        });

        slider.setOpaque(true);

        Hashtable<Integer, JLabel> labelTable = new Hashtable<>();
        for (int i = 0; i <= 100; i += 10) {
            JLabel label = new JLabel(String.valueOf(i));
            label.setForeground(new Color(0, 200, 255));
            label.setFont(new Font("Jokerman", Font.PLAIN, 12));
            labelTable.put(i, label);
        }
        slider.setLabelTable(labelTable);

        JLabel volumeLabel = new JLabel(currentVolume + "%");
        volumeLabel.setForeground(new Color(0, 200, 255));
        volumeLabel.setFont(new Font("Jokerman", Font.PLAIN, 14));
        volumeLabel.setPreferredSize(new Dimension(60, 30));
        volumeLabel.setHorizontalAlignment(SwingConstants.CENTER);

        slider.addChangeListener(e -> {
            currentVolume = slider.getValue();
            volumeLabel.setText(currentVolume + "%");
            double gain = currentVolume / 100.0;
            try {
                basicPlayer.setGain(gain);
            } catch (BasicPlayerException ex) {
                ex.printStackTrace();
            }
        });

        slider.addMouseWheelListener(e -> {
            int notches = e.getWheelRotation();
            newVolume = slider.getValue() - notches * 1;
            newVolume = Math.max(0, Math.min(100, newVolume));
            slider.setValue(newVolume);
        });

        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        panel.setLayout(new BorderLayout(10, 0));
        panel.add(volumeLabel, BorderLayout.WEST);
        panel.add(slider, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(320, 50));

        JDialog dialog = new JDialog(this, false);
        dialog.setUndecorated(true);
        dialog.setAlwaysOnTop(true);
        dialog.setBackground(new Color(30, 30, 30));
        dialog.getContentPane().add(panel);
        dialog.pack();

        Point panelLocation = transparentPanel1.getLocationOnScreen();
        int x = panelLocation.x;
        int y = panelLocation.y + transparentPanel1.getHeight();
        dialog.setLocation(x + muW / 2 - panel.getWidth() / 2 - 2, y / 2 + 20);

        dialog.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowLostFocus(WindowEvent e) {
                dialog.dispose();
            }

            @Override
            public void windowGainedFocus(WindowEvent e) {
            }
        });

        dialog.setVisible(true);
        dialog.requestFocus();
    }

    private void setupTimeLabel() {
        timeLabel = new JLabel("00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Jokerman", Font.PLAIN, 16));
        timeLabel.setForeground(new Color(0, 200, 255));
        int labelWidth = 200;
        int labelHeight = 20;
        int centerX = (transparentPanel1.getWidth() / 2) - (labelWidth / 2);
        timeLabel.setBounds(centerX, 20, labelWidth, labelHeight);
        transparentPanel1.setLayout(null);
        transparentPanel1.add(timeLabel);
    }

    private void setupTimer() {
        timer = new Timer(1000, e -> updateTimeLabel());
        timer.start();
    }

    private void updateTimeLabel() {
        if (status == PlayerStatus.PLAYING || status == PlayerStatus.PAUSED) {
            long currentSec = currentMicroseconds / 1_000_000;
            String current = String.format("%02d:%02d", currentSec / 60, currentSec % 60);
            timeLabel.setText(current);
        } else {
            timeLabel.setText("00:00");
        }
    }

    private void styleButton(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    private void playAudio() throws BasicPlayerException {
        if (musicFiles.isEmpty()) return;
        File audioFile = musicFiles.get(currentTrackIndex);
        basicPlayer.open(audioFile);
        basicPlayer.play();
        status = PlayerStatus.PLAYING;
        currentMicroseconds = 0;
        totalMicroseconds = 0;
        playPauseButton.setIcon(pauseIcon);
    }

    private void pauseAudio() throws BasicPlayerException {
        basicPlayer.pause();
        status = PlayerStatus.PAUSED;
        playPauseButton.setIcon(playIcon);
    }

    private void resumeAudio() throws BasicPlayerException {
        basicPlayer.resume();
        status = PlayerStatus.PLAYING;
        playPauseButton.setIcon(pauseIcon);
    }

    private void stopAudio() throws BasicPlayerException {
        basicPlayer.stop();
        status = PlayerStatus.STOPPED;
        playPauseButton.setIcon(playIcon);
    }

    private void nextTrack() {
        if (!musicFiles.isEmpty()) {
            currentTrackIndex = (currentTrackIndex + 1) % musicFiles.size();
            try {
                playAudio();
            } catch (BasicPlayerException e) {
                e.printStackTrace();
            }
        }
    }

    private void previousTrack() {
        if (!musicFiles.isEmpty()) {
            currentTrackIndex = (currentTrackIndex - 1 + musicFiles.size()) % musicFiles.size();
            try {
                playAudio();
            } catch (BasicPlayerException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void stateUpdated(BasicPlayerEvent event) {
        if (event.getCode() == BasicPlayerEvent.EOM) {
            status = PlayerStatus.STOPPED;
            SwingUtilities.invokeLater(() -> playPauseButton.setIcon(playIcon));
        }
    }

    @Override
    public void opened(Object stream, java.util.Map properties) {
        if (properties.containsKey("duration")) {
            Object durationObj = properties.get("duration");
            if (durationObj instanceof Long) {
                totalMicroseconds = (Long) durationObj;
            }
        }
    }

    @Override
    public void progress(int bytesread, long microseconds, byte[] pcmdata, java.util.Map properties) {
        currentMicroseconds = microseconds;
        if (totalMicroseconds == 0 && properties.containsKey("mp3.duration.microseconds")) {
            totalMicroseconds = (Long) properties.get("mp3.duration.microseconds");
        }
    }

    @Override
    public void setController(BasicController controller) {
    }
}
