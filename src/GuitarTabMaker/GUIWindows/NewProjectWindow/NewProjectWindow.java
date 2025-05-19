package GuitarTabMaker.GUIWindows.NewProjectWindow;

import GuitarTabMaker.GUIWindows.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewProjectWindow {

    private int windowWidth = (int) (GuitarTabMaker.GUIWindows.Window.screenSize.getWidth()*0.25);
    private int windowHeight = (int) (GuitarTabMaker.GUIWindows.Window.screenSize.getHeight()*0.45);

    private NewProjectWindow(){
        // create Window
        JFrame frame = new JFrame();
        frame.setTitle("New Project Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);
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
        label.setText("New Project");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setForeground(Window.text_c); // set text color
        label.setFont(new Font(Window.font, Font.BOLD, 15));
        label.setForeground(Color.BLACK);
        int label_width = windowWidth/3;
        int label_height = windowHeight/15;
        label.setBounds(windowWidth/2-label_width/2, 0, label_width, label_height);

        return label;
    }
    private Component ProjectName(){
        JLabel label = new JLabel();

        return label;
    }





    public static void main(String[] args) {
        new NewProjectWindow();
    }


}
