import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class recording extends PianoBoard {

    private JButton recordButton, pauseResumeButton;
    private JLabel statusLabel;

    private Process ffmpegProcess;
    private BufferedReader ffmpegReader;
    private Thread logReaderThread;

    private static final String FFMPEG_PATH = new File("ffmpeg-7.1.1-essentials_build/bin/ffmpeg.exe").getAbsolutePath();

    private File outputFile;
    private List<File> tempChunks = new ArrayList<>();

    private Timer recordingTimer;
    private long startTimeMillis;
    private long pausedTimeMillis;

    private boolean isRecording = false;
    private boolean isPaused = false;
    private int chunkCounter = 0;

    private ImageIcon recIcon, stopIcon;
    private ImageIcon pauseIcon, resumeIcon;

    private String selectedInputType = "wasapi"; // try wasapi first
    private String inputDevice = "default"; // default playback device

    public recording() {
        super();
        if (label2 != null) {
            transparentPanel1.remove(label2);
        }

        detectInputDevice(); // detects best available input
        rec.setIcon(pressedIcon43);
        setupButtons();
        setupStatusLabel();
        setVisible(true);
    }

    // Detects the best audio device and input type (wasapi preferred)
    private void detectInputDevice() {
        try {
            // Try WASAPI first (records from default playback)
            ProcessBuilder wasapiPB = new ProcessBuilder(
                FFMPEG_PATH, "-list_devices", "true", "-f", "wasapi", "-i", "dummy"
            );
            wasapiPB.redirectErrorStream(true);
            Process wasapiProcess = wasapiPB.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(wasapiProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[WASAPI DEVICE] " + line);
                if (line.contains("Output devices")) {
                    selectedInputType = "wasapi";
                    inputDevice = "default"; // or use full device name
                    return;
                }
            }
        } catch (IOException e) {
            System.out.println("WASAPI not supported. Falling back to DSHOW...");
        }

        // Fallback to DSHOW (detect Stereo Mix or Microphone)
        selectedInputType = "dshow";
        try {
            ProcessBuilder dshowPB = new ProcessBuilder(
                FFMPEG_PATH, "-list_devices", "true", "-f", "dshow", "-i", "dummy"
            );
            dshowPB.redirectErrorStream(true);
            Process dshowProcess = dshowPB.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(dshowProcess.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println("[DSHOW DEVICE] " + line);
                if (line.contains("Stereo Mix")) {
                    inputDevice = "Stereo Mix (Realtek(R) Audio)";
                    return;
                } else if (line.contains("Microphone")) {
                    inputDevice = "Microphone (Realtek(R) Audio)";
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setupButtons() {
        int width = 40, height = 40;

        recIcon = new ImageIcon(new ImageIcon("image/start1.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        stopIcon = new ImageIcon(new ImageIcon("image/recstop1.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        pauseIcon = new ImageIcon(new ImageIcon("image/off.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
        resumeIcon = new ImageIcon(new ImageIcon("image/pause.png").getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));

        recordButton = new JButton(recIcon);
        recordButton.setBounds(5, 20, 40, 40);
        styleButton(recordButton);
        transparentPanel1.add(recordButton);

        pauseResumeButton = new JButton(pauseIcon);
        pauseResumeButton.setBounds(50, 20, 40, 40);
        styleButton(pauseResumeButton);
        pauseResumeButton.setEnabled(false);
        transparentPanel1.add(pauseResumeButton);

        recordButton.addActionListener(e -> {
            if (!isRecording) {
                tempChunks.clear();
                chunkCounter = 0;
                startRecording();
                isRecording = true;
                recordButton.setIcon(stopIcon);
                pauseResumeButton.setEnabled(true);
            } else {
                stopRecording();
                isRecording = false;
                recordButton.setIcon(recIcon);
                pauseResumeButton.setEnabled(false);
                pauseResumeButton.setIcon(pauseIcon);
                isPaused = false;
                new PianoBoard();
            }
        });

        pauseResumeButton.addActionListener(e -> {
            if (!isPaused) {
                pauseRecording();
                isPaused = true;
                pauseResumeButton.setIcon(resumeIcon);
            } else {
                resumeRecording();
                isPaused = false;
                pauseResumeButton.setIcon(pauseIcon);
            }
        });
    }

    private void styleButton(JButton button) {
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
    }

    private void setupStatusLabel() {
        statusLabel = new JLabel("");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 18));
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setBounds(20, 90, 400, 30);
        transparentPanel1.add(statusLabel);
    }

    private void startRecording() {
        try {
            File tempChunk = File.createTempFile("chunk_" + (++chunkCounter), ".wav");
            tempChunks.add(tempChunk);

            List<String> command = new ArrayList<>();
            command.add(FFMPEG_PATH);
            command.add("-y");

            if (selectedInputType.equals("wasapi")) {
                command.add("-f");
                command.add("wasapi");
                command.add("-i");
                command.add(inputDevice); // usually "default"
            } else {
                command.add("-f");
                command.add("dshow");
                command.add("-i");
                command.add("audio=" + inputDevice);
            }

            command.add("-acodec");
            command.add("pcm_s16le");
            command.add("-ar");
            command.add("44100");
            command.add("-ac");
            command.add("2");
            command.add(tempChunk.getAbsolutePath());

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            ffmpegProcess = pb.start();
            ffmpegReader = new BufferedReader(new InputStreamReader(ffmpegProcess.getInputStream()));

            logReaderThread = new Thread(() -> {
                try {
                    String line;
                    while ((line = ffmpegReader.readLine()) != null) {
                        System.out.println("[FFmpeg] " + line);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
            logReaderThread.start();

            startTimeMillis = System.currentTimeMillis() - pausedTimeMillis;
            recordingTimer = new Timer(1000, evt -> {
                long elapsed = System.currentTimeMillis() - startTimeMillis;
                int secs = (int)(elapsed / 1000);
                statusLabel.setText(String.format("Recording: %02d:%02d:%02d", secs/3600, (secs%3600)/60, secs%60));
            });
            recordingTimer.start();
            isPaused = false;

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "FFmpeg failed: Check path or device.");
        }
    }

    private void pauseRecording() {
        if (ffmpegProcess != null) ffmpegProcess.destroy();
        if (logReaderThread != null && logReaderThread.isAlive()) logReaderThread.interrupt();
        if (recordingTimer != null && recordingTimer.isRunning()) recordingTimer.stop();
        pausedTimeMillis = System.currentTimeMillis() - startTimeMillis;
        statusLabel.setText("  Paused");
    }

    private void resumeRecording() {
        if (isPaused) startRecording();
    }

    private void stopRecording() {
        try {
            if (ffmpegProcess != null) ffmpegProcess.destroy();
            if (logReaderThread != null && logReaderThread.isAlive()) logReaderThread.interrupt();
            if (recordingTimer != null) recordingTimer.stop();

            if (!tempChunks.isEmpty()) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Save Audio File");
                fileChooser.setSelectedFile(new File("recorded.wav"));

                int userSelection = fileChooser.showSaveDialog(this);
                if (userSelection != JFileChooser.APPROVE_OPTION) {
                    statusLabel.setText("Save cancelled.");
                    return;
                }

                outputFile = fileChooser.getSelectedFile();
                String finalPath = outputFile.getAbsolutePath();
                if (!finalPath.toLowerCase().endsWith(".wav")) finalPath += ".wav";

                File concatList = File.createTempFile("concat_list", ".txt");
                try (PrintWriter writer = new PrintWriter(concatList)) {
                    for (File chunk : tempChunks)
                        writer.println("file '" + chunk.getAbsolutePath().replace("\\", "/") + "'");
                }

                ProcessBuilder pb = new ProcessBuilder(
                    FFMPEG_PATH, "-f", "concat", "-safe", "0",
                    "-i", concatList.getAbsolutePath(), "-c", "copy", finalPath
                );
                pb.redirectErrorStream(true);
                Process concatProcess = pb.start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(concatProcess.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null)
                    System.out.println("[FFmpeg-CONCAT] " + line);

                statusLabel.setText("Saved Recording");
                Thread.sleep(100);
                new PianoBoard();

            } else {
                statusLabel.setText("No audio recorded.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
