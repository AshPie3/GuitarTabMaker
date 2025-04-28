package GuitarTabMaker.GUIWindows.ProjectWindow;

import GuitarTabMaker.FretboardCreator.Fretboard;
import GuitarTabMaker.FretboardCreator.Note;
import GuitarTabMaker.FretboardCreator.Scale;
import GuitarTabMaker.GUIWindows.Window;
import GuitarTabMaker.Project;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class ProjectWindow {
    private int windowWidth = (int) (Window.screenSizeWidth*0.7);
    private int windowHeight = (int) (Window.screenSizeHeight*0.7);
    private int fretboardPanelWidth = (int) (windowWidth * 0.9);
    private int fretboardPanelHeight = (int) (windowHeight * 0.25);






    public ProjectWindow(Project project){
        Fretboard fretboard = project.getFretboard();

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

        //Add buttons
        setButtons(fretboard, frame);

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



        return fretboardPanel;
    }
    private JPanel FretboardNumsPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Window.fretboard_num_c);
        int panel_height = (int) (fretboardPanelHeight*0.15);
        panel.setBounds((windowWidth/2-fretboardPanelWidth/2), (int)(windowHeight-fretboardPanelHeight-windowHeight*0.1) - panel_height, fretboardPanelWidth, panel_height);
        panel.setLayout(null);
        for(int i = 0; i<=Fretboard.getFretNum(); i++){
            int num_x = (fretboardPanelWidth/(Fretboard.getFretNum()+1)) *i;
            JLabel label = new JLabel();
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setBounds(num_x,0,  (fretboardPanelWidth/(Fretboard.getFretNum()+1)),panel_height );
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


    private void setButtons(Fretboard fretboard, JFrame frame){
        List<List<Component>> button_list = new LinkedList<>();
        int b_diamater = fretboardPanelHeight/7;
        for (int y = 0; y <6; y++) {
            int y_m = (fretboardPanelHeight/7) * y + (fretboardPanelHeight/7) - b_diamater/2;
            for (int x = 0; x < Fretboard.getFretNum(); x++) {
                JButton button = new RoundButton();
                int x_m = (fretboardPanelWidth / (Fretboard.getFretNum() + 1)) * x + (fretboardPanelWidth / (Fretboard.getFretNum() + 1)) / 2 - b_diamater/4;
                button.setBounds(x_m, y_m, b_diamater, b_diamater);
                //button.setContentAreaFilled(false);
                //Get fretboard values
                String note_name = fretboard.getFretboard().get(y).get(x).getName();
                int note_value = fretboard.getFretboard().get(y).get(x).getVal();
                String audio = fretboard.getFretboard().get(y).get(x).getAudio_file();
                button.setText(note_name);




                //Apply button
                frame.add(button);

            }
        }

    }



    private class FretboardPanel extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            int circle_w = fretboardPanelHeight/12;
            super.paintComponent(g);
            for (int h = 0; h<=Fretboard.getFretNum(); h++){
                g.setColor(Color.BLACK);
                int x = (fretboardPanelWidth/(Fretboard.getFretNum()+1)) *h;
                g.fillRect(x,0, (int) (fretboardPanelWidth*0.005), fretboardPanelHeight);
                if (h == 4 || h == 6 || h == 8 || h==10 || h ==16){
                    g.setColor(Color.WHITE);
                    g.fillOval(x -(fretboardPanelWidth/(Fretboard.getFretNum()+1))/2 - circle_w/4, fretboardPanelHeight/2 - circle_w/2, circle_w, circle_w);
                }
                if (h ==13){
                    g.setColor(Color.WHITE);
                    g.fillOval(x -(fretboardPanelWidth/(Fretboard.getFretNum()+1))/2 - circle_w/4, (int) ((fretboardPanelHeight/2 - circle_w/2) - fretboardPanelHeight/3.5), circle_w, circle_w);
                    g.fillOval(x -(fretboardPanelWidth/(Fretboard.getFretNum()+1))/2 - circle_w/4, (int) ((fretboardPanelHeight/2 - circle_w/2) + fretboardPanelHeight/3.5), circle_w, circle_w);
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
        private boolean mousePressed = false;
        private boolean inKey = false;

        public RoundButton() {
            setOpaque(false);
            setFocusPainted(false);
            setBorderPainted(false);

            MouseAdapter mouseListener = new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    if (contains(e.getX(), e.getY())) {
                        mousePressed = true;
                        repaint();
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    mousePressed = false;
                    repaint();
                }


            };
            addMouseListener(mouseListener);
            addMouseMotionListener(mouseListener);
        }
        private int getDiameter(){
            int diameter = Math.min(getWidth(), getHeight());
            return diameter;
        }
        @Override
        public void paintComponent(Graphics g){
            int radius =  getDiameter()/2;
            super.paintComponent(g);
            if(inKey == true) g.setColor(Window.button_on_c);
            else g.setColor(Window.button_off_c);
            g.fillOval(getWidth()/2-radius, getHeight()/2-radius, radius*2, radius*2);

        }

        public boolean isInKey() {return inKey;}
        public void setInKey(boolean inKey) {this.inKey = inKey;}
    }

    public static void main(String[] args) {
        Scale scale = new Scale();
        scale.createScale(1, 4);
        Project project = new Project(new Fretboard(),scale , 1);
        new ProjectWindow(project);
    }
}
