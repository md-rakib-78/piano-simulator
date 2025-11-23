import javax.swing.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.*;

public class inputFile extends PianoBoard {

    public inputFile() {
        super();

        in.setIcon(pressedIcon47);

        String filePath = chooseFileAndGetPath();
        if (filePath != null) {
            System.out.println("Selected File Path: " + filePath);
            ArrayList<String> words = readWordsFromFile(filePath);
            System.out.println("Words in file:");
            for (String word : words) {
                System.out.println(word);
            }

            if (!words.isEmpty() && !words.get(0).equals(" ")) {

                int len = words.size();

                for (int i = 0; i < len - 1; i++) {

                    String s = words.get(i);

                    if (s.equals(" ")) {
                        try {
                            Thread.sleep(200);
                        } catch (Exception e) {

                        }
                    } else if(!s.contains("#")){

                        String s1 = ".wav";
                        String s2 = s.concat(s1);
                        System.out.println("Length: " + s2);
                        playSound(s2);

                        if (label1 != null) {
                            transparentPanel.remove(label1);
                        }

                        label1 = new JLabel(s, SwingConstants.CENTER);
                        label1.setForeground(Color.RED);
                        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                        transparentPanel.revalidate();
                        transparentPanel.repaint();
                        transparentPanel.add(label1);

                    }else
                    {
                        String s1 = ".wav";
                        String s2 = s.concat(s1);
                        System.out.println("Length: " + s2);
                        playSound1(s2);
                        if (label1 != null) {
                            transparentPanel.remove(label1);
                        }

                        label1 = new JLabel(s, SwingConstants.CENTER);
                        label1.setForeground(Color.RED);
                        label1.setFont(new Font("Jokerman", Font.PLAIN, 30));
                        transparentPanel.revalidate();
                        transparentPanel.repaint();
                        transparentPanel.add(label1);
                    }
                }

                String s = "End Of sound";
                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel(s, SwingConstants.CENTER);
                label1.setForeground(Color.WHITE);
                label1.setFont(new Font("Arial", Font.PLAIN, 25));
                transparentPanel.revalidate();
                transparentPanel.repaint();
                transparentPanel.add(label1);

            } else {
                String s2="The file is empty.";
                if (label1 != null) {
                    transparentPanel.remove(label1);
                }

                label1 = new JLabel(s2, SwingConstants.CENTER);
                label1.setForeground(Color.WHITE);
                label1.setFont(new Font("Arial", Font.PLAIN, 25));
                transparentPanel.revalidate();
                transparentPanel.repaint();
                transparentPanel.add(label1);
            }
        } else {
            String s="No file selected.";
            if (label1 != null) {
                transparentPanel.remove(label1);
            }

            label1 = new JLabel(s, SwingConstants.CENTER);
            label1.setForeground(Color.WHITE);
            label1.setFont(new Font("Arial", Font.PLAIN, 25));
            transparentPanel.revalidate();
            transparentPanel.repaint();
            transparentPanel.add(label1);
        }
    }

    public String chooseFileAndGetPath() {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();
        frame.setTitle("File Chooser");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setIconImage(new ImageIcon(getClass().getResource("/image/icon1.png")).getImage());
        frame.setSize(10, 10);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Input .txt");
        fileChooser.setAcceptAllFileFilterUsed(true);
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));

        int result = fileChooser.showOpenDialog(frame);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            //JOptionPane.showMessageDialog(frame, "You selected:\n" + selectedFile.getName(), "File Chosen", JOptionPane.INFORMATION_MESSAGE);
            frame.dispose();
            return selectedFile.getAbsolutePath();
        } else {
            frame.dispose();
            return null;
        }
    }



public ArrayList<String> readWordsFromFile(String filePath) {
    ArrayList<String> wordList = new ArrayList<>();
    try {
        FileReader fileReader = new FileReader(filePath);
        StringBuilder word = new StringBuilder();
        int ch;
        while ((ch = fileReader.read()) != -1) {
            char c = (char) ch;

            if (c == '\n' || c == '\r' || c == '.') {
                c = ' ';
            }

            if (Character.isWhitespace(c)) {
                if (word.length() > 0) {
                    wordList.add(word.toString());
                    word.setLength(0);
                }
                wordList.add(" "); 
            } else {
                word.append(c);
            }
        }
        if (word.length() > 0) {
            wordList.add(word.toString());
        }
        fileReader.close();
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }
    return wordList;
}

}
