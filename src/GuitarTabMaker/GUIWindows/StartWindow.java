package GuitarTabMaker.GUIWindows;

import javax.swing.*;
import java.awt.*;

public class StartWindow {

    private JPanel panel1;
    private JTextPane textPane1;
    private int windowWidth = (int) (Window.screenSize.getWidth()*0.5);
    private int windowHeight = (int) (Window.screenSize.getHeight()*0.5);

    private StartWindow(){
        // create Window
        JFrame frame = new JFrame();
        frame.setTitle("Start Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight );
        frame.setResizable(false);
        ImageIcon icon = new ImageIcon("Assets/Icon.png");
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(Window.background_c);
        frame.setLayout(null);
        frame.setVisible(true);

        // Create Center Label
        JLabel label = new JLabel();
        label.setText("Guitar Tab Maker");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setForeground(Window.text_c); // set text color
        label.setFont(new Font("Courier New", Font.BOLD, 42));
        int label_width = 400;
        int label_height = 100;
        label.setBounds(windowWidth/2-label_width/2, 50, label_width, label_height);

        // Add components
        frame.add(label);

    }


    public static void main(String[] args) {
        new StartWindow();
    }
}
