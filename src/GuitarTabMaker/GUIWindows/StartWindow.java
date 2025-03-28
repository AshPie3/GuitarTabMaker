package GuitarTabMaker.GUIWindows;

import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;

public class StartWindow {
    public static void main(String[] args) {
        MyButton button = new MyButton("Hello, World!");

        JFrame frame = new JFrame("Guitar Tab Maker");
        frame.setSize((int) (Window.width*0.8), (int) (Window.height*0.8));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.add(button);
        frame.setVisible(true);
    }
}



