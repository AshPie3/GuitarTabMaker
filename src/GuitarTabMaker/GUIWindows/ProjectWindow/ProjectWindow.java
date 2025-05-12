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
    private final int windowWidth = (int) (Window.screenSizeWidth*0.8);
    private final int windowHeight = (int) (Window.screenSizeHeight*0.8);
    private final int fretboardPanelWidth = (int) (windowWidth * 0.95);
    private final int fretboardPanelHeight = (int) (windowHeight * 0.3);
    private final int top_margin = (int) (windowHeight*0.05);
    private final List<List<String>> tab;
    private String str_tab;
    private int currently_edited = 2;


    public ProjectWindow(Project project){
        Fretboard fretboard = project.getFretboard();
        tab = project.getTab();
        str_tab = TabListToString();

        // Create Window frame
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

        //Adding components to the frame
        JPanel fretpanel = FretboardPanel();
        frame.add(fretpanel);
        setButtons(fretboard,fretpanel);
        frame.add(FretboardNumsPanel());// Create Fretboard Numbers panel
        frame.add(ExitButton(frame)); // Create exit button
        frame.add(functions_panel()); // add panel with functions like next/previous line
        /*
        //Add next buttons button
        frame.add(insert_line()); // Insert line button
        frame.add(previous_line()); // Edit previous line button
        frame.add(next_line()); // edit next line button
        */
        if (tab.isEmpty()) {
            //Initialize list
            for (int i = 0; i < 3; i++) {
                tab.add(i, new ArrayList<>(6));
            }
            for (int i = 0; i < 6; i++) {
                tab.get(0).add(i, fretboard.getTuning().get(i).getName().substring(0, 1));
            }
            for (int i = 0; i < 6; i++) {
                tab.get(1).add(i, "|");
            }
            for (int i = 0; i < 6; i++) {
                tab.get(2).add(i, "--");
            }
            str_tab = TabListToString();
        }
        System.out.println(str_tab); // Temporary for debugging
        //Add tablature button

        //Set frame visible
        frame.setVisible(true);
    }
    //Components
    private JPanel FretboardPanel(){
        JPanel fretboardPanel = new FretboardPanel();
        fretboardPanel.setBackground(Window.fretboard_c);
        int x = (windowWidth-fretboardPanelWidth) /3;
        fretboardPanel.setBounds(x, (int)(windowHeight-fretboardPanelHeight-windowHeight*0.15), fretboardPanelWidth, fretboardPanelHeight);
        fretboardPanel.setLayout(null);
        return fretboardPanel;
    }
    private JPanel FretboardNumsPanel(){
        JPanel panel = new JPanel();
        panel.setBackground(Window.fretboard_num_c);
        int panel_height = (int) (fretboardPanelHeight*0.15);
        int x = (windowWidth-fretboardPanelWidth) /3;
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
        JLabel label = new JLabel();
        label.setText("Exit");
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

    private void add_line(){ // function
        List<String> temp_list = new ArrayList<>(6);
        for (int i = 0; i<6; i++){temp_list.add("--");}
        //currently_edited++;
        tab.add(currently_edited+1, temp_list);
        str_tab = TabListToString();

    }

    private JButton insert_line(int width, int height, int x){
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (width*0.08);
        int button_height = (int) (height*0.8);
        int y = (height-button_height)/2;
        button.setBounds(x, y ,button_width,button_height);
        //button.setSize(button_width,button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        //button.setLayout(null);
        //button.set
        JLabel label = new JLabel();
        label.setText("Insert");
        label.setBounds(0,0,button_width,button_height);

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD,(int) (button_height*0.4)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> temp_list = new ArrayList<>(6);
                for (int i = 0; i<6; i++){temp_list.add("--");}
                currently_edited++;
                tab.add(currently_edited, temp_list);
                str_tab = TabListToString();

                //System.out.println(tab);
            }
        });

        return button;
    }

    private JButton insert_bar_line(int width, int height, int x){
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (width*0.04);
        int button_height = (int) (height*0.8);
        int y = (height-button_height)/2;
        button.setBounds(x, y ,button_width,button_height);
        //button.setSize(button_width,button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        //button.setLayout(null);
        //button.set
        JLabel label = new JLabel();
        label.setText(" | ");
        label.setBounds(0,0,button_width,button_height);

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD,(int) (button_height*0.6)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> temp_list = new ArrayList<>(6);
                for (int i = 0; i<6; i++){temp_list.add("|");}
                currently_edited++;

                tab.add(currently_edited, temp_list);
                str_tab = TabListToString();

                //System.out.println(tab);
            }
        });

        return button;
    }

    private JButton next_line(int width, int height, int x){
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (width*0.04);
        int button_height = (int) (height*0.8);
        int y = (height-button_height)/2;
        button.setBounds(x, y ,button_width,button_height);
        //button.setSize(button_width,button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        //button.setLayout(null);
        //button.set
        JLabel label = new JLabel();
        label.setText(">");
        label.setBounds(0,0,button_width,button_height);

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD,(int) (button_height*0.4)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(tab.size());
                if (currently_edited== tab.size()-1){ add_line(); currently_edited++; }
                else if (tab.get(currently_edited).get(0) == "|") currently_edited++ ;
                else currently_edited++;
                //System.out.println(tab);
            }
        });
        return button;
    }

    private JButton previous_line(int width, int height, int x){
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (width*0.04);
        int button_height = (int) (height*0.8);
        int y = (height-button_height)/2;
        button.setBounds(x, y ,button_width,button_height);
        //button.setSize(button_width,button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        //button.setLayout(null);
        //button.set
        JLabel label = new JLabel();
        label.setText("<");
        label.setBounds(0,0,button_width,button_height);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD,(int) (button_height*0.4)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currently_edited--;
                if (currently_edited<2 ) currently_edited++;
                else if (tab.get(currently_edited).get(0) == "|") currently_edited--;
                //System.out.println(tab);
            }
        });
        return button;
    }

    private Component functions_panel(){
        JPanel panel = new JPanel();
        //panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        //panel.add(Box.createHorizontalGlue());
        panel.setLayout(null);
        int width = (int) (windowWidth*0.7);
        int height = (int) (fretboardPanelHeight*0.23);
        int x = (windowWidth-width)/2;
        int y = windowHeight- 2*height;
        int button_width = (int) (width*0.08);
        panel.setBounds(x, y, width, height);
        panel.setBackground(Window.fretboard_c);
        int func_num = 4;
        int[] func_x = new int[func_num];
        for (int i = 0; i<func_num; i++){
            func_x[i] = (width/func_num)*i + width/func_num/2;
        }
        //next line button
        panel.add(next_line(width,height, func_x[3]));
        panel.add(previous_line(width, height, func_x[2]));
        panel.add(insert_bar_line(width,height, func_x[1]));
        panel.add(insert_line(width,height, func_x[0]));
        return panel;
    }

    private Component TabWindow(){
        JPanel panel = new JPanel();
        panel.setLayout(null);
        int y = top_margin + 5;
        int width = (int) (windowWidth*0.9);
        int height = (int) (windowHeight/2.3);
        panel.setBounds((int) (windowWidth*0.05), y, width, height);
        panel.setBackground(Window.fretboard_c);
        int label_width = (int) (width*0.01);
        int label_height = (int) (height*0.01);
        int row = 0;
        panel.setLayout(null);
        for (int x_m = 0; x_m< tab.size(); x_m++) { // creating a list of lables which will have all the string values still it would not update whenever there is a change based on the current way its written
            int x_value = width/50;
            JLabel label = new JLabel();
            if(x_m == currently_edited)
            label.setBackground(Window.button_on_c);
            else label.setBackground(Window.button_off_c);
            StringBuffer string_val = new StringBuffer();
            for (int i = 0; i<6; i++){
                string_val.append(tab.get(x_m).get(i));
            }
            label.setText(string_val.toString());
        }


        //panel.setFont(new Font(Window.font, Font.BOLD, (int)(height*0.05)));
        //panel.setEditable(false);

        //Visible window
        JScrollPane scrollFrame = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollFrame.setBounds((int) (windowWidth*0.05), y,width ,height );
        scrollFrame.setBorder(null);

        // Scroll bar
        JScrollBar scrollBar =scrollFrame.getVerticalScrollBar();
        scrollBar.setOrientation(JScrollBar.VERTICAL);

        return scrollFrame;
    }

    private String TabListToString(){ //Temporary use conversion from a list to string it doesn't work perfectly tho
        StringBuffer str_tab = new StringBuffer();
        for(int y = 0; y< 6; y++){
            for(int x = 0; x<tab.size(); x++) {
                String current_val = tab.get(x).get(y);
                str_tab.append(current_val);
            }
            str_tab.append("\n");
        }
        System.out.println(str_tab); // Temporary for debugging
        //System.out.println(tab); // Temporary for debugging
        return str_tab.toString();
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

    private class NoteLabel extends JLabel{
        private boolean selected = false;
        private String value;
        public NoteLabel(String value, boolean selected){
            this.selected = selected;
            this.value = value;

            setText(value);


        }


        public boolean isSelected() {return selected;}
        public void setSelected(boolean selected) {this.selected = selected;}

        public String getValue() {return value;}
        public void setValue(String value) {this.value = value;}

    }

    private class RoundButton extends JLabel{
        private boolean mousePressed = false;
        private boolean inKey = false;
        private int fret;
        private final int string;
        private final String audio_file;

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
                int current_val_int;
                String appended_val;
                try {
                    current_val_int = Integer.parseInt( tab.get(currently_edited).get(string).replaceAll("-", ""));
                } catch (NumberFormatException e) {
                    current_val_int = -1;
                }
                g.setColor(Window.button_hover);

                if(fret>=10){
                    appended_val = String.valueOf(fret);
                } else appended_val = String.valueOf(fret) + "-";

                if (current_val_int != fret){
                    tab.get(currently_edited).set(string, "");
                    tab.get(currently_edited).set(string, appended_val);
                } else { //(current_val == String.valueOf(fret))
                    tab.get(currently_edited).set(string, "--");
                }
                str_tab = TabListToString();

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
        Project project = new Project(new Fretboard(),scale , 1, new LinkedList<>());
        new ProjectWindow(project);
    }
}
