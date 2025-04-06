package GuitarTabMaker.GUIWindows.ProjectWindow;

import GuitarTabMaker.FretboardCreator.Fretboard;
import GuitarTabMaker.GUIWindows.Window;

import javax.swing.*;
import java.awt.*;

public class ProjectWindow {
    private int windowWidth = (int) (Window.screenSizeWidth*0.7);
    private int windowHeight = (int) (Window.screenSizeHeight*0.7);
    private int fretboardPanelWidth = (int) (windowWidth * 0.9);
    private int fretboardPanelHeight = (int) (windowHeight * 0.25);
    private int fretNum = Fretboard.getFretNum();
    public ProjectWindow(){
         // Create Window
        JFrame frame = new JFrame();
        frame.setTitle("Project Window");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);
        frame.setResizable(false);
        frame.setVisible(true);
        ImageIcon icon = new ImageIcon("Assets/Icon.png");
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(Window.background_c);
        frame.setLayout(null);
        // Create Fretboard pannel
        frame.add(FretboardPanel());
        // Create Fretboard Numbers panel
        frame.add(FretboardNumsPanel());



    }
    private Component FretboardPanel(){
        JPanel fretboardPanel = new FretboardPanel();
        fretboardPanel.setBackground(Window.fretboard_c);
        fretboardPanel.setBounds(windowWidth/2-fretboardPanelWidth/2, (int)(windowHeight-fretboardPanelHeight-windowHeight*0.1), fretboardPanelWidth, fretboardPanelHeight);

        return fretboardPanel;
    }
    private Component FretboardNumsPanel(){
        JPanel panel = new FretNumPanel();
        panel.setBackground(Window.fretboard_num_c);
        int panel_height = (int) (fretboardPanelHeight*0.1);
        panel.setBounds((windowWidth/2-fretboardPanelWidth/2), (int)(windowHeight-fretboardPanelHeight-windowHeight*0.1) - panel_height, fretboardPanelWidth, panel_height);


        return panel;
    }


    public static void main(String[] args) {
        new ProjectWindow();
    }

    private class FretboardPanel extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            int circle_w = fretboardPanelHeight/12;
            super.paintComponent(g);
            for (int h = 0; h<=fretNum; h++){
                g.setColor(Color.BLACK);
                int x = (fretboardPanelWidth/(fretNum+1)) *h;
                g.fillRect(x,0, (int) (fretboardPanelWidth*0.005), fretboardPanelHeight);
                if (h == 4 || h == 6 || h == 8 || h==10 || h ==16){
                    g.setColor(Color.WHITE);
                    g.fillOval(x -(fretboardPanelWidth/(fretNum+1))/2 - circle_w/4, fretboardPanelHeight/2 - circle_w/2, circle_w, circle_w);
                }
                if (h ==13){
                    g.setColor(Color.WHITE);
                    g.fillOval(x -(fretboardPanelWidth/(fretNum+1))/2 - circle_w/4, (int) ((fretboardPanelHeight/2 - circle_w/2) - fretboardPanelHeight/3.5), circle_w, circle_w);
                    g.fillOval(x -(fretboardPanelWidth/(fretNum+1))/2 - circle_w/4, (int) ((fretboardPanelHeight/2 - circle_w/2) + fretboardPanelHeight/3.5), circle_w, circle_w);
                }

            }
            for (int i = 1; i<7; i++){
                int y = (fretboardPanelHeight/7) *i;
                g.setColor(Color.BLACK);
                g.fillRect(0,y, fretboardPanelWidth, (int) (fretboardPanelHeight*0.02));

            }

        }
    }
    private class FretNumPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);
            int panel_height = (int) (fretboardPanelHeight*0.1);
            for(int i = 0; i<=fretNum; i++){
                int num_x = (fretboardPanelWidth/(fretNum+1)) *i-(fretboardPanelWidth/(fretNum+1))/2;
                JLabel label = new JLabel();
                label.setHorizontalAlignment(SwingConstants.CENTER);
                label.setVerticalAlignment(SwingConstants.CENTER);
                label.setBounds(((fretboardPanelWidth/(fretNum+1))) +num_x,0, 20,panel_height );
                label.setFont(new Font("Courier New", Font.BOLD, (int) (panel_height*0.7))); // 16
                label.setText(String.valueOf(i ));
                label.setForeground(Color.black);
                add(label);
            }
        }
    }
}
