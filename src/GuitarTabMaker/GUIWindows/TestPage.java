package GuitarTabMaker.GUIWindows;

import javax.swing.*;
import java.awt.*;
import javax.swing.JComponent;

public class TestPage {
    //static int height = (int) (screenSize.getHeight()*0.8);
    public static void main(String[] args) {
        JFrame frame = new JFrame("My first JFrame");
        frame.setSize((int) (Window.width*0.8), (int) (Window.height*0.8));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //frame.getContentPane().add(new ShapeDrawing ());
        frame.setVisible(true);
    }
}