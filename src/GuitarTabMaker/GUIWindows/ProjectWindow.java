package GuitarTabMaker.GUIWindows;

import javax.swing.*;
import java.awt.*;

public class ProjectWindow {
    private int windowWidth = (int) (Window.screenSize.getWidth()*0.7);
    private int windowHeight = (int) (Window.screenSize.getHeight()*0.7);
    ProjectWindow(){
        JFrame frame = new JFrame();
        frame.setTitle("Project Window");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight );
        frame.setResizable(false);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon("Assets/Icon.png");
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(Window.background_c);
    }

    public static void main(String[] args) {
        new ProjectWindow();
    }
}
