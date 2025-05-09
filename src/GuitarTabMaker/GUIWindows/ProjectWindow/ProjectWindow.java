package GuitarTabMaker.GUIWindows.ProjectWindow;

import GuitarTabMaker.FretboardCreator.Fretboard;
import GuitarTabMaker.FretboardCreator.Scale;
import GuitarTabMaker.GUIWindows.Window;
import GuitarTabMaker.ProjectManager.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProjectWindow {
    private int windowWidth = (int) (Window.screenSizeWidth*0.8);
    private int windowHeight = (int) (Window.screenSizeHeight*0.8);
    private int fretboardPanelWidth = (int) (windowWidth * 0.95);
    private int fretboardPanelHeight = (int) (windowHeight * 0.3);
    private int top_margin = (int) (windowHeight*0.05);

    private List<List<String>> tab = new LinkedList();


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
        // Create Fretboard panel
        JPanel fretpanel = FretboardPanel();
        frame.add(fretpanel);
        setButtons(fretboard,fretpanel);
        // Create Fretboard Numbers panel
        frame.add(FretboardNumsPanel());

        // Create exit button
        frame.add(ExitButton(frame));

        //Add tablature button
        frame.add(TabWindow());

        //Add next line button
        frame.add(next_line());
        frame.setVisible(true);

        //Initialize list
        List<String> temp_list = new ArrayList<>(6);
        for (int i = 0; i<6; i++){
            String note = fretboard.getTuning().get(i).getName().substring(0,1);
            temp_list.add(note);
        }
        tab.add(temp_list);
        System.out.println(tab);


    }
    //Components
    private JPanel FretboardPanel(){
        JPanel fretboardPanel = new FretboardPanel();
        fretboardPanel.setBackground(Window.fretboard_c);
        int x = (int) (windowWidth-fretboardPanelWidth)/3;
        fretboardPanel.setBounds(x, (int)(windowHeight-fretboardPanelHeight-windowHeight*0.15), fretboardPanelWidth, fretboardPanelHeight);
        fretboardPanel.setLayout(null);
        return fretboardPanel;
    }
    private JPanel FretboardNumsPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Window.fretboard_num_c);
        int panel_height = (int) (fretboardPanelHeight*0.15);
        int x = (int) (windowWidth-fretboardPanelWidth)/3;
        panel.setBounds(x, (int)(windowHeight-fretboardPanelHeight-windowHeight*0.15) - panel_height, fretboardPanelWidth, panel_height);
        panel.setLayout(null);
        for(int i = 0; i<Fretboard.getFretNum(); i++){
            int num_x = (fretboardPanelWidth/(Fretboard.getFretNum())) *i;
            JLabel label = new JLabel();
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setBounds(num_x,0,  (fretboardPanelWidth/(Fretboard.getFretNum())),panel_height );
            label.setFont(new Font("Courier New", Font.BOLD, (int) (panel_height*0.7))); // 16
            label.setText(String.valueOf(i));
            label.setForeground(Color.black);
            panel.add(label);
        }

        return panel;
    }
    private Component ExitButton(JFrame frame){
        JButton button = new JButton();
        int button_width = (int) (windowWidth*0.08);
        int button_height = top_margin;
        button.setBounds(windowWidth-button_width, 0, button_width,button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        //button.set
        JLabel label = new JLabel();
        label.setText("Exit");
        //button.setLayout(null);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD,(int) (button_height*0.6)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });


        return button;
    }

    private JButton next_line(){
        JButton button = new JButton();
        int button_width = (int) (windowWidth*0.08);
        int button_height = top_margin*2;
        button.setBounds(windowWidth/2, 0, button_width,button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        //button.set
        JLabel label = new JLabel();
        label.setText("next line");
        //button.setLayout(null);
        button.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD,(int) (button_height*0.6)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> temp_list = new ArrayList<>(6);
                for (int i = 0; i<6; i++){temp_list.add("-");}
                tab.add(temp_list);
                System.out.println(tab);
            }
        });

        return button;
    }

    private JScrollPane TabWindow(){
        //Whole panel
        JTextArea panel = new JTextArea();
        panel.setLayout(null);
        int y = top_margin + 5;
        int width = (int) (windowWidth*0.9);
        int height = (int) (windowHeight/2.3);
        panel.setBounds((int) (windowWidth*0.05), y, width, height*100);
        panel.setLineWrap(true);

        //Visible window
        JScrollPane scrollFrame = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFrame.setBounds((int) (windowWidth*0.05), y,width ,height );

        // Scroll bar
        JScrollBar scrollBar =scrollFrame.getVerticalScrollBar();
        scrollBar.setOrientation(JScrollBar.VERTICAL);

        // Add components
        return scrollFrame;
    }

    //Modified Classes
    private List<List<Component>> setButtons(Fretboard fretboard, JPanel panel){
        List<List<Component>> button_list = new LinkedList<>();
        List<Component> button_temp = new LinkedList<>();
        int b_diamater = (int) (fretboardPanelHeight/7.3);
        for (int y = 0; y <6; y++) {
            int y_m = (fretboardPanelHeight/7) * y + (fretboardPanelHeight/7) - b_diamater/2;
            for (int x = 0; x < Fretboard.getFretNum(); x++) {
                int x_m = (fretboardPanelWidth / (Fretboard.getFretNum())) * x + (fretboardPanelWidth / (Fretboard.getFretNum() + 1)) / 2 - b_diamater/4;
                JLabel mainLabel = new RoundButton(x, y, fretboard.getFretboard().get(y).get(x).getIn_scale(), fretboard.getFretboard().get(y).get(x).getAudio_file());
                mainLabel.setBounds(x_m, y_m, b_diamater, b_diamater);
                JLabel textLabel = new JLabel();
                textLabel.setBounds(x_m, y_m, b_diamater, b_diamater);
                textLabel.setText(fretboard.getFretboard().get(y).get(x).getName());
                textLabel.setFont(new Font(Window.font, Font.BOLD,(int) (fretboardPanelHeight*0.07)));
                textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                button_temp.add(mainLabel);
                panel.add(textLabel);
                panel.add(mainLabel);

            }
            button_list.add(button_temp);
            button_temp.clear();
        }
        return button_list;

    }

    private class FretboardPanel extends JPanel{
        @Override
        public void paintComponent(Graphics g){
            int circle_w = fretboardPanelHeight/12;
            super.paintComponent(g);
            for (int h = 0; h<Fretboard.getFretNum(); h++){
                g.setColor(Color.BLACK);
                int x = (fretboardPanelWidth/(Fretboard.getFretNum())) *h;
                g.fillRect(x,0, (int) (fretboardPanelWidth*0.005), fretboardPanelHeight);
                if (h == 4 || h == 6 || h == 8 || h==10 || h ==16){
                    g.setColor(Color.WHITE);
                    g.fillOval(x -(fretboardPanelWidth/(Fretboard.getFretNum()))/2 - circle_w/4, fretboardPanelHeight/2 - circle_w/2, circle_w, circle_w);
                }
                if (h ==13){
                    g.setColor(Color.WHITE);
                    g.fillOval(x -(fretboardPanelWidth/(Fretboard.getFretNum()))/2 - circle_w/4, (int) ((fretboardPanelHeight/2 - circle_w/2) - fretboardPanelHeight/3.5), circle_w, circle_w);
                    g.fillOval(x -(fretboardPanelWidth/(Fretboard.getFretNum()))/2 - circle_w/4, (int) ((fretboardPanelHeight/2 - circle_w/2) + fretboardPanelHeight/3.5), circle_w, circle_w);
                }
            }
            for (int i = 1; i<7; i++){
                int y = (fretboardPanelHeight/7) *i;
                g.setColor(Color.BLACK);
                g.fillRect(0,y, fretboardPanelWidth, (int) (fretboardPanelHeight*0.02));

            }
        }
    }


    private class RoundButton extends JLabel{
        private boolean mousePressed = false;
        private boolean inKey = false;
        private int fret;
        private int string;
        private String audio_file;

        public RoundButton(int fret, int string, boolean inKey, String audio_file) {
            this.inKey = inKey;
            this.fret = fret;
            this.string = string;
            this.audio_file = audio_file;
            setOpaque(false);


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
            if(mousePressed == true) {
                g.setColor(Window.button_hover);
                System.out.println(getFret());
            }else if(inKey == true) g.setColor(Window.button_on_c);
            else g.setColor(Window.button_off_c);
            g.fillOval(getWidth()/2-radius, getHeight()/2-radius, radius*2, radius*2);



        }

        public boolean isInKey() {return inKey;}
        public void setInKey(boolean inKey) {this.inKey = inKey;}

        public int getFret() {return fret;}

        public void setFret(int fret) {this.fret = fret;}
    }

    //Test method
    public static void main(String[] args) {
        Scale scale = new Scale();
        scale.createScale(1, 4);
        Project project = new Project(new Fretboard(),scale , 1);
        new ProjectWindow(project);
    }
}
