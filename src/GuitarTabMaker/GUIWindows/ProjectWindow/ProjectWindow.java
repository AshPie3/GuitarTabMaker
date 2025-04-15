package GuitarTabMaker.GUIWindows.ProjectWindow;

import GuitarTabMaker.FretboardCreator.Fretboard;
import GuitarTabMaker.FretboardCreator.Note;
import GuitarTabMaker.GUIWindows.Window;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

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
        //frame.setVisible(true);
        ImageIcon icon = new ImageIcon("Assets/Icon.png");
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(Window.background_c);
        frame.setLayout(null);
        // Create Fretboard pannel
        frame.add(FretboardPanel());
        // Create Fretboard Numbers panel
        frame.add(FretboardNumsPanel());

        //fretNumPanel.add(label);
        // Create exit button
        frame.add(ExitButton());

        frame.setVisible(true);



    }
    private JPanel FretboardPanel(){
        JPanel fretboardPanel = new FretboardPanel();
        fretboardPanel.setBackground(Window.fretboard_c);
        fretboardPanel.setBounds(windowWidth/2-fretboardPanelWidth/2, (int)(windowHeight-fretboardPanelHeight-windowHeight*0.1), fretboardPanelWidth, fretboardPanelHeight);
        fretboardPanel.setLayout(null);
        // Temp
        int x = 0;
        int y = 0;
        int b_diamater = fretboardPanelHeight/12;
        JButton button = new JButton();
        int y_m = (fretboardPanelHeight/7) * y + (fretboardPanelHeight/7) - b_diamater/2;
        int x_m = (fretboardPanelWidth / (fretNum + 1)) * x + (fretboardPanelWidth / (fretNum + 1)) / 2 - b_diamater/4;
        button.setBounds(x_m, y_m, b_diamater, b_diamater);
        //button.setBorder(new RoundedBorder(b_diamater/2));
        //button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setFocusPainted(false);
        button.setBorderPainted(false);

        fretboardPanel.add(button);


        return fretboardPanel;
    }
    private JPanel FretboardNumsPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Window.fretboard_num_c);
        int panel_height = (int) (fretboardPanelHeight*0.15);
        panel.setBounds((windowWidth/2-fretboardPanelWidth/2), (int)(windowHeight-fretboardPanelHeight-windowHeight*0.1) - panel_height, fretboardPanelWidth, panel_height);
        panel.setLayout(null);
        for(int i = 0; i<=fretNum; i++){
            int num_x = (fretboardPanelWidth/(fretNum+1)) *i;
            JLabel label = new JLabel();
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setBounds(num_x,0,  (fretboardPanelWidth/(fretNum+1)),panel_height );
            label.setFont(new Font("Courier New", Font.BOLD, (int) (panel_height*0.7))); // 16
            label.setText(String.valueOf(i));
            label.setForeground(Color.black);
            panel.add(label);
        }

        return panel;
    }

    private Component ExitButton(){
        JButton button = new JButton();
        int button_width = (int) (windowWidth*0.09);
        button.setBounds(windowWidth-button_width, 0, button_width, (int) (windowHeight*0.08));
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        //button.set
        JLabel label = new JLabel();
        label.setText("Exit");
        //button.setLayout(null);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD,(int) (windowHeight*0.08*0.4)));
        button.add(label);

        return button;
    }

    private List<List<Component>> setButtons(Fretboard fretboard){
        List<List<Component>> button_list = new LinkedList<>();
        int b_diamater = fretboardPanelHeight/12;
        for (int y = 0; y <6; y++) {
            int y_m = (fretboardPanelHeight/7) * y + (fretboardPanelHeight/7/2);
            for (int x = 0; x <= fretNum; x++) {
                JButton button = new JButton();
                int x_m = (fretboardPanelWidth / (fretNum + 1)) * x + (fretboardPanelWidth / (fretNum + 1)) / 2;
                button.setBounds(x_m, y_m, b_diamater, b_diamater);
                //button.setBorder(new RoundedBorder(b_diamater/2));

                //Get fretboard values
                String note_name = fretboard.getFretboard().get(y).get(x).getName();
                int note_value = fretboard.getFretboard().get(y).get(x).getVal();


            }
        }
        return button_list;

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

    private class RoundButton extends JButton{


    }


    public static void main(String[] args) {
        new ProjectWindow();
    }
}
