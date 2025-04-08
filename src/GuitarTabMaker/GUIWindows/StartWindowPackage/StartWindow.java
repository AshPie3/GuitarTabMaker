package GuitarTabMaker.GUIWindows.StartWindowPackage;

import GuitarTabMaker.GUIWindows.Window;

import javax.swing.*;
import java.awt.*;

public class StartWindow {

    private JPanel panel1;
    private JTextPane textPane1;
    private int windowWidth = (int) (GuitarTabMaker.GUIWindows.Window.screenSize.getWidth()*0.5);
    private int windowHeight = (int) (GuitarTabMaker.GUIWindows.Window.screenSize.getHeight()*0.5);

    private StartWindow(){
        // create Window
        JFrame frame = new JFrame();
        frame.setTitle("Start Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight );
        frame.setResizable(false);
        ImageIcon icon = new ImageIcon("Assets/Icon.png");
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(GuitarTabMaker.GUIWindows.Window.background_c);
        frame.setLayout(null);
        frame.setVisible(true);


        // Add components
        frame.add(MainTitle());

    }
    // Create Center Label
    private Component MainTitle(){
        JLabel label = new JLabel();
        label.setText("Guitar Tab Maker");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setForeground(Window.text_c); // set text color
        label.setFont(new Font("Courier New", Font.BOLD, (int) (windowHeight*0.08)));
        int label_width = 400;
        int label_height = 100;
        label.setBounds(windowWidth/2-label_width/2, 50, label_width, label_height);

        return label;
    }





    public static void main(String[] args) {
        new StartWindow();
    }
}
