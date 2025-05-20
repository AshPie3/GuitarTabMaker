package GuitarTabMaker.GUIWindows.StartWindow;

import GuitarTabMaker.ConnectionSettings;
import GuitarTabMaker.GUIWindows.ProjectWindow.ProjectWindow;
import GuitarTabMaker.GUIWindows.Window;
import GuitarTabMaker.ProjectManager.Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class StartWindow {

    private int windowWidth = (int) (GuitarTabMaker.GUIWindows.Window.screenSize.getWidth()*0.7);
    private int windowHeight = (int) (GuitarTabMaker.GUIWindows.Window.screenSize.getHeight()*0.7);
    private JScrollPane existingProjectScrollPane = new JScrollPane();
    private JPanel existingProjectPanel = new JPanel();
    private HashMap<Integer, String> projectMap = new HashMap<>();
    private JFrame frame = new JFrame();

    public StartWindow(){
        // create Window
        frame.setTitle("Start Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);
        frame.setResizable(false);
        ImageIcon icon = new ImageIcon("Assets/Icon.png");
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(GuitarTabMaker.GUIWindows.Window.background_c);
        frame.setLayout(null);

        generateHashMap();
        ExistingProjectScrollPane();

        // Add components
        frame.add(MainTitle());
        frame.add(ExistingProjectPanel());

        frame.setVisible(true);
    }
    // Create Center Label
    private Component MainTitle(){
        JLabel label = new JLabel();
        label.setText("Tablature Maker");
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

    private Component ExistingProjectScrollPane(){
        int width = windowWidth/3;
        int height = (int) ( windowHeight*0.7);
        int x = width/4;
        int y = windowHeight/2-height/2;
        existingProjectScrollPane.setBorder(null);
        existingProjectScrollPane.setBounds(0,0, width,height);

        existingProjectScrollPane = new JScrollPane(ExistingProjectPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        JScrollBar scrollBar = existingProjectScrollPane.getVerticalScrollBar();
        scrollBar.setOrientation(JScrollBar.VERTICAL);
        return existingProjectScrollPane;

    }

    private JPanel ExistingProjectPanel(){
        existingProjectPanel.setLayout(null);
        existingProjectPanel.setBounds(0,0, existingProjectScrollPane.getWidth(), existingProjectScrollPane.getHeight()*10);
        existingProjectPanel.setBackground(Window.function_panel_c);
        createProjectLabels();
        return existingProjectPanel;
    }
    private void generateHashMap(){
        ConnectionSettings settings = new ConnectionSettings();
        try {
            Connection conn = settings.getDatabaseConnection();
            String sql = "SELECT p_id, p_name FROM projects ";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while(resultSet.next()){
                projectMap.put(resultSet.getInt(1),resultSet.getString(2) );
            }
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    private void openProject(int p_id){
        new ProjectWindow(new Project(p_id));
        frame.dispose();

    }
    private void createProjectLabels(){
        int height = existingProjectScrollPane.getHeight()/20;
        for(int i = 0; i < projectMap.size(); i++){
            int key = (int) projectMap.keySet().toArray()[i];
            int y = height*i;
            String name = projectMap.get(key);
            JLabel label = new ProjectLabel(key);
            label.setBounds(0,y,existingProjectPanel.getWidth(), height);
            label.setText(name);
            existingProjectPanel.add(label);
        }
    }

    private class ProjectLabel extends JLabel{
        private boolean mousePressed = false;
        private int p_id;
        private String p_name;
        ProjectLabel(int p_id){
            this.p_id = p_id;
            MouseAdapter mouseListener = new MouseAdapter() {

                @Override
                public void mousePressed(MouseEvent e) {
                    if (contains(e.getX(), e.getY())) {
                        mousePressed = true;
                        openProject(p_id);
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
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (mousePressed == true) {
                g.setColor(Window.button_hover);
            } else g.setColor(Window.button_off_c);
        }

    }




    public static void main(String[] args) {
        new StartWindow();
    }

}