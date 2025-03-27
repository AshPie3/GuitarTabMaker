package GuitarTabMaker.GUIWindows;

import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;

public class TestPage {
    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    static int width = (int) (screenSize.getWidth()*0.8);
    static int height = (int) (screenSize.getHeight()*0.8);
    public static void main(String[] args) {
        JFrame frame = new JFrame("My first JFrame");
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().add(new ShapeDrawing ());
        frame.setVisible(true);
    }
}