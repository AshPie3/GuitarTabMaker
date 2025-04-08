package GuitarTabMaker.GUIWindows.StartWindow;

import GuitarTabMaker.GUIWindows.Window;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindow {

    private JPanel panel1;

    private int windowWidth = (int) (GuitarTabMaker.GUIWindows.Window.screenSize.getWidth()*0.5);
    private int windowHeight = (int) (GuitarTabMaker.GUIWindows.Window.screenSize.getHeight()*0.5);

    private StartWindow(){
        // create Window
        JFrame frame = new JFrame();
        frame.setTitle("Start Window");
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
        frame.add(NewProjectButton());



    }
    // Create Center Label
    private Component MainTitle(){
        JLabel label = new JLabel();
        label.setText("Guitar Tab Maker");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setForeground(Window.text_c); // set text color
        label.setFont(new Font(Window.font, Font.BOLD, (int) (windowHeight*0.08)));
        int label_width = 400;
        int label_height = 100;
        label.setBounds(windowWidth/2-label_width/2, 50, label_width, label_height);

        return label;
    }

    private Component NewProjectButton(){
        JButton button = new JButton();
        int width = (int) (windowWidth/4.5);
        int height = windowHeight/7;
        button.setBounds(windowWidth - windowWidth/3 - width/2, windowHeight/2 - height/2, width, height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(false);
        JLabel label = new JLabel();
        label.setText("New Project");
        label.setFont(new Font(Window.font, Font.BOLD, height/3));

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });


        button.add(label);

        return button;
    }






    public static void main(String[] args) {
        new StartWindow();
    }


}
