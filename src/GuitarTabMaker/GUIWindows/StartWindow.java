package GuitarTabMaker.GUIWindows;

import javax.swing.*;
import java.awt.*;

public class StartWindow {
    public StartWindow(){
        JFrame frame = new JFrame();
        frame.setTitle("Start Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);

        ImageIcon icon = new ImageIcon("Assets/Icon.png");
        frame.setIconImage(icon.getImage());

        frame.getContentPane().setBackground(Window.background_c);
    }

    public static void main(String[] args) {
        new StartWindow();
    }
}
