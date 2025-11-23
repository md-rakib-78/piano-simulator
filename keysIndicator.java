import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class keysIndicator extends PianoBoard {

    JPanel t1;

    public keysIndicator()
    {
         ke.setIcon(pressedIcon45);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenW = screenSize.width;
        int screenH = screenSize.height;

        ImageIcon icon0 = new ImageIcon("image/button1.png");
        int imgH0 = icon0.getIconHeight();

        int customWidth = screenW; 
        int customHeight = 28;

        JLabel boardLabel = new JLabel("  F2    F#2   G2   G#2    A2   A#2    B2     C3    C#3   D3    D#3    E3    F3    F#3    G3    G#3   A3   A#3    B3     C4    C#4   D4    D#4    E4     F4    F#4   G4    G#4   A4    A#4   B4    C5    C#5  D5  D#5  E5");
        boardLabel.setFont(new Font("Dutch801 Rm BT", Font.BOLD, 17));
        boardLabel.setForeground(Color.LIGHT_GRAY);
        boardLabel.setBounds(5,screenH - imgH0, customWidth+10, customHeight);

        transparentPanel2.setLayout(null);
        transparentPanel2.add(boardLabel);

        JLabel boardLabel1 = new JLabel("  1     2     3      4      5     6     7     8     9     0      q     w    e      r     t      y      u     i      o    p   a ");
        boardLabel1.setFont(new Font("Dutch801 Rm BT", Font.BOLD, 37));
        boardLabel1.setForeground(Color.decode("#272728"));
        boardLabel1.setBounds(5,screenH - 100, customWidth, customHeight+15);
         transparentPanel2.add(boardLabel1);

        JLabel boardLabel2 = new JLabel("       1           2          3                5           6                8          9          0"+      
              "                w           e                t           y          u               o          p");
        boardLabel2.setFont(new Font("Dutch801 Rm BT", Font.BOLD, 25));
        boardLabel2.setForeground(Color.decode("#ffffff"));
        boardLabel2.setBounds(5,screenH - 280, customWidth, customHeight+15);
         transparentPanel2.add(boardLabel2);

                 JLabel boardLabel3 = new JLabel("       +          +          +                 +          +                +          +           +"+      
              "                +           +                +          +         +                +         +");
        boardLabel3.setFont(new Font("Dutch801 Rm BT", Font.BOLD, 25)); 
        boardLabel3.setForeground(Color.decode("#ffffff"));
        boardLabel3.setBounds(5,screenH - 280-40, customWidth, customHeight+15);
         transparentPanel2.add(boardLabel3);

         JLabel boardLabel4 = new JLabel("       sft          sft         sft               sft          sft                sft         sft         sft"+      
              "                sft          sft                sft         sft         sft               sft        sft");
        boardLabel4.setFont(new Font("Dutch801 Rm BT", Font.BOLD, 23));
        boardLabel4.setForeground(Color.decode("#ffffff"));
        boardLabel4.setBounds(5,screenH - 280-40-40, customWidth, customHeight+15);
         transparentPanel2.add(boardLabel4);
    }

    
}
