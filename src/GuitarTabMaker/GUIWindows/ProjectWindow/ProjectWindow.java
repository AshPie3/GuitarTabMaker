package GuitarTabMaker.GUIWindows.ProjectWindow;

import GuitarTabMaker.ConnectionSettings;
import GuitarTabMaker.FretboardCreator.Fretboard;
import GuitarTabMaker.GUIWindows.Window;
import GuitarTabMaker.ProjectManager.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ProjectWindow {
    private final int windowWidth = (int) (Window.screenSizeWidth * 0.8);
    private final int windowHeight = (int) (Window.screenSizeHeight * 0.8);
    private final int fretboardPanelWidth = (int) (windowWidth * 0.95);
    private final int fretboardPanelHeight = (int) (windowHeight * 0.25);
    private final int top_margin = (int) (windowHeight * 0.05);
    private final List<List<String>> tab;
    private int p_id;
    private boolean auto_next_line = true;
    private String str_tab;
    private int currently_edited = 2;
    private JFrame frame = new JFrame(); //new window
    private JTextArea textArea = new JTextArea(); // tablature text area panel
    private JPanel fretboardPanel = new FretboardPanel();
    private JScrollPane scrollFrame = new JScrollPane(); // tablature text area scroll frame tablatureTextArea(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
    private JPanel fretboardNumsPanel = new JPanel();
    private JPanel functionsPanel = new JPanel();
    
    public ProjectWindow(Project project) {
        Fretboard fretboard = project.getFretboard();
        p_id = project.getP_id();
        this.tab = project.getTab();

        // Create Window frame
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
        setButtons(fretboard, fretpanel);
        frame.add(FretboardNumsPanel());// Create Fretboard Numbers panel
        frame.add(MenuPanel()); // Create menu panel
        frame.add(functionsPanel()); // add panel with functions like next/previous line
        TabListToString();
        validateText();
        tablatureTextArea();
        System.out.println(tab);
        frame.add(TabScrollFramePanel());
        //Set frame visible
        frame.setVisible(true);
    }
    //Panels
    private JPanel FretboardPanel() {
        fretboardPanel.setBackground(Window.fretboard_c);
        int x = (windowWidth - fretboardPanelWidth) / 3;
        fretboardPanel.setBounds(x, (int) (windowHeight - fretboardPanelHeight - windowHeight * 0.15), fretboardPanelWidth, fretboardPanelHeight);
        fretboardPanel.setLayout(null);

        return fretboardPanel;
    }
    private JPanel FretboardNumsPanel() {
        fretboardNumsPanel.setBackground(Window.fretboard_num_c);
        int panel_height = (int) (fretboardPanelHeight * 0.15);
        int x = (windowWidth - fretboardPanelWidth) / 3;
        fretboardNumsPanel.setBounds(x, (int) (windowHeight - fretboardPanelHeight - windowHeight * 0.15) - panel_height, fretboardPanelWidth, panel_height);
        fretboardNumsPanel.setLayout(null);
        for (int i = 0; i < Fretboard.getFretNum(); i++) {
            int num_x = (fretboardPanelWidth / (Fretboard.getFretNum())) * i;
            JLabel label = new JLabel();
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setBounds(num_x, 0, (fretboardPanelWidth / (Fretboard.getFretNum())), panel_height);
            label.setFont(new Font("Courier New", Font.BOLD, (int) (panel_height * 0.7))); // 16
            label.setText(String.valueOf(i));
            label.setForeground(Color.black);
            fretboardNumsPanel.add(label);
        }
        return fretboardNumsPanel;
    }
    private Component TabScrollFramePanel() {
        int y = (int) (windowHeight*0.15);
        int width = (int) (windowWidth * 0.9);
        int height = (int) (windowHeight /3.5);
        scrollFrame = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_NEVER, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); // tablature text area scroll frame
        scrollFrame.setBounds((int) (windowWidth * 0.05), y, width, height);
        scrollFrame.setBorder(null);

        JScrollBar scrollBar = scrollFrame.getVerticalScrollBar();
        scrollBar.setOrientation(JScrollBar.VERTICAL);

        return scrollFrame;
    }
    private JTextArea tablatureTextArea() {

        textArea.setLayout(null);
        int width = (int) (windowWidth * 0.9);
        int height = (int) (windowHeight / 2.3);
        textArea.setBounds(0, 0, width, height);
        textArea.setBackground(Window.fretboard_c);
        textArea.setLayout(null);
        textArea.setEditable(false);
        textArea.setText(str_tab);
        textArea.setFont(new Font(Window.font, Font.BOLD, (int) (width * 0.02)));
        //textArea.setLineWrap(true);

        return textArea;
    }

    private JPanel tablatureTextPanel(){
        JPanel panel = new JPanel();
        int width = (int) (windowWidth * 0.9);
        int height = (int) (windowHeight / 2.3) * 100;
        panel.setBounds(0, 0, width, height);
        panel.setBackground(Window.fretboard_c);
        panel.setLayout(null);


        return panel;
    }
    private Component MenuPanel() {
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(null);
        int width = windowWidth;
        int height = (int) (windowHeight * 0.05);
        menuPanel.setBounds(0, 0, width, height);
        menuPanel.setBackground(Window.text_c);
        int btn_num = 6;
        int[] func_x = new int[btn_num];
        for (int i = 0; i < btn_num; i++) {
            func_x[i] = (width / btn_num) * i + width / btn_num;
        }
        menuPanel.add(exitButton(width, height, func_x[5]));
        menuPanel.add(saveButton(width,height,func_x[4]));

        return menuPanel;
    }
    private Component exitButton(int width, int height, int x){
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (width * 0.08);
        int button_height = height;
        int y = 0;
        button.setBounds(windowWidth-button_width, y, button_width, button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        JLabel label = new JLabel();
        label.setText("Exit");
        label.setBounds(0, 0, button_width, button_height);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD, (int) (button_height * 0.6)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTabToDatabase();
                frame.dispose();
            }
        });
        return button;
    }
    private JButton saveButton(int width, int height, int x) {
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (width * 0.06);
        int button_height = height;
        int y = (height - button_height) / 2;
        button.setBounds(x, y, button_width, button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        JLabel label = new JLabel();
        label.setText("Save");
        label.setBounds(0, 0, button_width, button_height);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD, (int) (button_height * 0.4)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveTabToDatabase();
            }
        });
        return button;
    }
    private Component functionsPanel() {
        functionsPanel.setLayout(null);
        int width = (int) (windowWidth * 0.7);
        int height = (int) (fretboardPanelHeight * 0.23);
        int x = (windowWidth - width) / 2;
        int y = windowHeight - 2 * height;
        functionsPanel.setBounds(x, y, width, height);
        functionsPanel.setBackground(Window.function_panel_c);
        int func_num = 6;
        int[] func_x = new int[func_num];
        for (int i = 0; i < func_num; i++) {
            func_x[i] = (width / func_num) * i + width / func_num / 2;
        }
        //next line button
        functionsPanel.add(autoNextLineButton(width, height, func_x[5]));
        functionsPanel.add(nextLineButton(width, height, func_x[4]));
        functionsPanel.add(previousLineButton(width, height, func_x[3]));
        functionsPanel.add(insertBarLineButton(width, height, func_x[2]));
        functionsPanel.add(insertLineButton(width, height, func_x[1]));
        functionsPanel.add(deleteLineButton(width, height, func_x[0]));
        return functionsPanel;
    }
    // Elements of function panel
    private JButton autoNextLineButton(int width, int height, int x) {
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (width * 0.15);
        int button_height = (int) (height * 0.8);
        int y = (height - button_height) / 2;
        button.setBounds(x - button_width/2, y, button_width, button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        JLabel label = new JLabel();
        label.setText("Auto next");
        label.setBounds(0, 0, button_width, button_height);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD, (int) (button_height * 0.4)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(auto_next_line) auto_next_line= false;
                else auto_next_line= true;
                System.out.println("Auto next line is:" + auto_next_line);
            }
        });
        return button;
    }
    private void addLine() {
        List<String> temp_list = new ArrayList<>(6);
        for (int i = 0; i < 6; i++) {
            temp_list.add("--");
        }
        temp_list.add(" ");
        tab.add(currently_edited, temp_list);
        //TabListToString();
        validateText();

    }
    private JButton deleteLineButton(int width, int height, int x) {
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (width * 0.08);
        int button_height = (int) (height * 0.8);
        int y = (height - button_height) / 2;
        button.setBounds(x - button_width/2, y, button_width, button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        JLabel label = new JLabel();
        label.setText("Delete");
        label.setBounds(0, 0, button_width, button_height);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD, (int) (button_height * 0.4)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currently_edited > 2) {
                    tab.remove(currently_edited);
                    currently_edited--;
                } else {
                    for(int i = 0; i<6; i++) {
                        tab.get(currently_edited).set(i, "--");
                    }
                }
                validateText();

            }
        });

        return button;
    }
    private JButton insertLineButton(int width, int height, int x) {
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (width * 0.08);
        int button_height = (int) (height * 0.8);
        int y = (height - button_height) / 2;
        button.setBounds(x - button_width/2, y, button_width, button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        JLabel label = new JLabel();
        label.setText("Insert");
        label.setBounds(0, 0, button_width, button_height);

        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD, (int) (button_height * 0.4)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> temp_list = new ArrayList<>(6);
                for (int i = 0; i < 6; i++) {
                    temp_list.add("--");
                }
                temp_list.add("  ");
                currently_edited++;
                tab.add(currently_edited, temp_list);
                //TabListToString();
                validateText();
            }
        });
        return button;
    }
    private JButton insertBarLineButton(int width, int height, int x) {
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (width * 0.04);
        int button_height = (int) (height * 0.8);
        int y = (height - button_height) / 2;
        button.setBounds(x - button_width/2, y, button_width, button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        JLabel label = new JLabel();
        label.setText("|");
        label.setBounds(0, 0, button_width, button_height);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD, (int) (button_height * 0.5)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<String> temp_list = new ArrayList<>(6);
                for (int i = 0; i < 6; i++) {
                    temp_list.add("|");
                }
                temp_list.add(" ");
                currently_edited++;
                tab.add(currently_edited, temp_list);
                validateText();
            }
        });
        return button;
    }
    private JButton nextLineButton(int width, int height, int x) {
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (width * 0.04);
        int button_height = (int) (height * 0.8);
        int y = (height - button_height) / 2;
        button.setBounds(x, y, button_width, button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        JLabel label = new JLabel();
        label.setText(">");
        label.setBounds(0, 0, button_width, button_height);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD, (int) (button_height * 0.4)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currently_edited++;
                if (currently_edited >= tab.size() - 1) {
                    currently_edited = tab.size();
                    addLine();
                } else {}
                validateText();

            }
        });
        return button;
    }
    private JButton previousLineButton(int width, int height, int x) {
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (width * 0.04);
        int button_height = (int) (height * 0.8);
        int y = (height - button_height) / 2;
        button.setBounds(x - button_width/2, y, button_width, button_height);
        button.setBackground(Window.button_off_c);
        button.setBorderPainted(true);
        JLabel label = new JLabel();
        label.setText("<");
        label.setBounds(0, 0, button_width, button_height);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD, (int) (button_height * 0.4)));
        button.add(label);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currently_edited > 2) currently_edited--;
                validateText();

            }
        });
        return button;
    }
    private void TabListToString() {
        StringBuffer str_tab_buff = new StringBuffer();
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < tab.size(); x++) {
                String current_val = tab.get(x).get(y);
                str_tab_buff.append(current_val);
            }
            str_tab_buff.append("\n");
        }
        //System.out.println(str_tab_buff); // for debugging
        str_tab = str_tab_buff.toString();
    } //Function used to display the Tablature in the frame
    private String LimitedTabListToString(int begin_col, int end_col) {
        StringBuffer str_tab_buff = new StringBuffer();
        List<List<String>> limited_tab = new LinkedList<>();
        for (int i = begin_col; i<= end_col; i++){
            limited_tab.add(tab.get(i));
        }
        for (int y = 0; y < 7; y++) {
            for (int x = 0; x < limited_tab.size(); x++) {
                String current_val = limited_tab.get(x).get(y);
                str_tab_buff.append(current_val);
            }
            str_tab_buff.append("\n");
        }
        //System.out.println(str_tab_buff); // for debugging
        return str_tab_buff.toString();
    } //Used to get one panel row of tab
    private void setButtons(Fretboard fretboard, JPanel panel) {
        List<List<Component>> button_list = new LinkedList<>();
        List<Component> button_temp = new LinkedList<>();
        int b_diamater = (int) (fretboardPanelHeight / 7.3);
        for (int y = 0; y < 6; y++) {
            int y_m = (fretboardPanelHeight / 7) * y + (fretboardPanelHeight / 7) - b_diamater / 2;
            for (int x = 0; x < Fretboard.getFretNum(); x++) {
                int x_m = (fretboardPanelWidth / (Fretboard.getFretNum())) * x + (fretboardPanelWidth / (Fretboard.getFretNum() + 1)) / 2 - b_diamater / 4;
                JLabel mainLabel = new RoundButton(x, y, fretboard.getFretboard().get(y).get(x).getIn_scale(), fretboard.getFretboard().get(y).get(x).getAudio_file());
                mainLabel.setBounds(x_m, y_m, b_diamater, b_diamater);
                JLabel textLabel = new JLabel();
                textLabel.setBounds(x_m, y_m, b_diamater, b_diamater);
                textLabel.setText(fretboard.getFretboard().get(y).get(x).getName());
                textLabel.setFont(new Font(Window.font, Font.BOLD, (int) (fretboardPanelHeight * 0.07)));
                textLabel.setHorizontalAlignment(SwingConstants.CENTER);
                button_temp.add(mainLabel);
                panel.add(textLabel);
                panel.add(mainLabel);

            }
            button_list.add(button_temp);
            button_temp.clear();
        }
    }
    private void validateText(){
        Runnable runnable = new ValidateThread();
        Thread thread = new Thread(runnable);
        thread.start();
    }
    private void validatePointer(){
        for(int i = 0; i < tab.size(); i++){
            if (tab.get(i).get(0).equals("|"))  tab.get(i).set(6, " "); // tab.get(currently_edited).get(1).equals("|") tab.get(currently_edited).get(1).compareTo("|")==0
            else tab.get(i).set(6, "  ");
        }
        tab.get(0).set(6, "  ");
        tab.get(1).set(6, " ");
        if (tab.get(currently_edited).get(1).equals("|")){ tab.get(currently_edited).set(6, "^");} //tab.get(currently_edited).get(1) == "|"
        else tab.get(currently_edited).set(6, "^^");
        TabListToString();
    }
    //Modified Classes
    private class FretboardPanel extends JPanel {
        @Override
        public void paintComponent(Graphics g) {
            int circle_w = fretboardPanelHeight / 12;
            super.paintComponent(g);
            for (int h = 0; h < Fretboard.getFretNum(); h++) {
                g.setColor(Color.BLACK);
                int x = (fretboardPanelWidth / (Fretboard.getFretNum())) * h;
                g.fillRect(x, 0, (int) (fretboardPanelWidth * 0.005), fretboardPanelHeight);
                if (h == 4 || h == 6 || h == 8 || h == 10 || h == 16) {
                    g.setColor(Color.WHITE);
                    g.fillOval(x - (fretboardPanelWidth / (Fretboard.getFretNum())) / 2 - circle_w / 4, fretboardPanelHeight / 2 - circle_w / 2, circle_w, circle_w);
                }
                if (h == 13) {
                    g.setColor(Color.WHITE);
                    g.fillOval(x - (fretboardPanelWidth / (Fretboard.getFretNum())) / 2 - circle_w / 4, (int) ((fretboardPanelHeight / 2 - circle_w / 2) - fretboardPanelHeight / 3.5), circle_w, circle_w);
                    g.fillOval(x - (fretboardPanelWidth / (Fretboard.getFretNum())) / 2 - circle_w / 4, (int) ((fretboardPanelHeight / 2 - circle_w / 2) + fretboardPanelHeight / 3.5), circle_w, circle_w);
                }
            }
            for (int i = 1; i < 7; i++) {
                int y = (fretboardPanelHeight / 7) * i;
                g.setColor(Color.BLACK);
                g.fillRect(0, y, fretboardPanelWidth, (int) (fretboardPanelHeight * 0.02));

            }
        }
    }
    private class NoteLabel extends JLabel {
        private boolean selected = false;
        private String value;

        public NoteLabel(String value, boolean selected) {
            this.selected = selected;
            this.value = value;

            setText(value);


        }


        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

    }
    private class RoundButton extends JLabel {
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
                        validateText();
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

        private int getDiameter() {
            int diameter = Math.min(getWidth(), getHeight());
            return diameter;
        }

        @Override
        public void paintComponent(Graphics g) {
            int radius = getDiameter() / 2;
            super.paintComponent(g);
            if (mousePressed == true) {
                int current_val_int;
                String appended_val;
                g.setColor(Window.button_hover);
                try {
                    current_val_int = Integer.parseInt(tab.get(currently_edited).get(string).replaceAll("-", ""));
                } catch (NumberFormatException e) {
                    current_val_int = -1;
                }

                if (tab.get(currently_edited).get(1).equals("|")) return;

                else if (fret >= 10) {
                    appended_val = String.valueOf(fret);
                } else appended_val = fret + "-";

                if (current_val_int != fret) {
                    tab.get(currently_edited).set(string, "");
                    tab.get(currently_edited).set(string, appended_val);
                } else {
                    tab.get(currently_edited).set(string, "--");
                }
                if(auto_next_line){
                    currently_edited++;
                    if (currently_edited >= tab.size() - 1) {
                        currently_edited = tab.size();
                        addLine();
                    } else {}
                    validateText();
                }
                validateText();

            } else if (inKey == true) g.setColor(Window.button_on_c);
            else g.setColor(Window.button_off_c);
            g.fillOval(getWidth() / 2 - radius, getHeight() / 2 - radius, radius * 2, radius * 2);
        }
        public boolean isInKey() {return inKey;}
        public void setInKey(boolean inKey) {this.inKey = inKey;}

        public int getFret() {return fret;}
        public void setFret(int fret) {this.fret = fret;}

    }
    class ValidateThread implements Runnable {
        @Override
        public void run() {
            TabListToString();
                if (tablatureTextArea().getText() != str_tab) {
                    validatePointer();
                    tablatureTextArea().setText(str_tab);
                    tablatureTextArea().repaint();
            }
        }
    } // interface runnable include in Crit C

    public String convertTabToDatabaseString(){
        StringBuffer stringBuffer = new StringBuffer();
        for(int x = 0; x<tab.size(); x++){
            for(int y = 0; y<7; y++){
                stringBuffer.append(tab.get(x).get(y) + ",");
            }
        }
        System.out.println(stringBuffer);
        return String.valueOf(stringBuffer);
    }
    public void saveTabToDatabase(){
        ConnectionSettings settings = new ConnectionSettings();
        try {
            Connection conn = settings.getDatabaseConnection();
            PreparedStatement statement = conn.prepareStatement("UPDATE `guitartab`.`projects` SET `p_cvs_val`= ? WHERE `p_id` = ?");
            statement.setString(1, convertTabToDatabaseString());
            statement.setString(2, String.valueOf(p_id));
            statement.executeUpdate();
            System.out.println("Update executed: " + statement);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        Project project = new Project(1);
        new ProjectWindow(project);
    }
}
