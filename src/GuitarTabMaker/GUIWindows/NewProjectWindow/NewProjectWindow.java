package GuitarTabMaker.GUIWindows.NewProjectWindow;

        import GuitarTabMaker.ConnectionSettings;
        import GuitarTabMaker.GUIWindows.ProjectWindow.ProjectWindow;
        import GuitarTabMaker.GUIWindows.StartWindow.StartWindow;
        import GuitarTabMaker.GUIWindows.Window;
        import GuitarTabMaker.ProjectManager.Project;

        import javax.swing.*;
        import java.awt.*;
        import java.awt.event.ActionEvent;
        import java.awt.event.ActionListener;
        import java.sql.Connection;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;
        import java.util.HashMap;
        import java.util.Map;

public class NewProjectWindow {

    private int windowHeight = (int) (GuitarTabMaker.GUIWindows.Window.screenSize.getHeight()*0.7);
    private int windowWidth = windowHeight;//(int) (GuitarTabMaker.GUIWindows.Window.screenSize.getWidth()*0.5);
    private JFrame frame = new JFrame();
    private JLabel projectNameL;
    private JTextField projectNameTf;
    private JLabel projectKey;
    private JComboBox keyValCb;
    private HashMap<String, Integer> keyValMap = new HashMap();
    private JComboBox keyScaleCb;
    private HashMap<String, Integer> scaleMap = new HashMap<>();
    private JLabel projectTuning;
    private JComboBox tuningCb;
    private HashMap<String, Integer> tuningMap = new HashMap<>();
    private JButton createBtn = new JButton();
    private int p_id;
    private int t_id;
    private int s_id;
    private int key_val;
    private String p_name;
    public NewProjectWindow(){
        // create Window
        frame.setTitle("Create Project Window");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);
        frame.setResizable(false);
        ImageIcon icon = new ImageIcon("Assets/Icon.png");
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(GuitarTabMaker.GUIWindows.Window.background_c);
        frame.setLayout(null);

        // Add components
        frame.add(exitBtn());
        frame.add(projectName());
        frame.add(projectNameTextField());
        frame.add(projectKey());
        frame.add(projectKeyValBox());
        frame.add(projectScaleBox());
        frame.add(projectTuning());
        frame.add(projectTuningBox());
        frame.add(createProjectBtn());

        frame.setVisible(true);
    }
    private void populateKeyValMap(){
        keyValMap.put( "A", 1);
        keyValMap.put("A#", 2);
        keyValMap.put("B", 3);
        keyValMap.put("C", 4);
        keyValMap.put("C#", 5);
        keyValMap.put("D", 6);
        keyValMap.put("D#", 7);
        keyValMap.put("E", 8);
        keyValMap.put("F", 9);
        keyValMap.put("F#",10);
        keyValMap.put("G",11);
        keyValMap.put("G#",12);
    }
    private void populateScaleMap() {
        ConnectionSettings settings = new ConnectionSettings();
        try {
            Connection conn = settings.getDatabaseConnection();
            String sql = "SELECT s_id, s_name FROM scale";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                scaleMap.put(resultSet.getString("s_name"), resultSet.getInt("s_id"));
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void populateTuningMap(){
        ConnectionSettings settings = new ConnectionSettings();
        try {
            Connection conn = settings.getDatabaseConnection();
            String sql = "SELECT t_id, t_name FROM tunings";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                tuningMap.put(resultSet.getString("t_name"),resultSet.getInt("t_id"));
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private void createProject() {
        Project project = new Project(this.t_id, this.s_id, this.key_val, this.p_name);
        
        new ProjectWindow(project);
        frame.dispose();

    }
    private Component exitBtn(){
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (windowWidth * 0.1);
        int button_height = (int) (windowHeight*0.045);
        int y = 0;
        button.setBounds(0, y, button_width, button_height);
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
                new StartWindow();
                frame.dispose();
            }
        });
        return button;
    }
    private Component projectName(){
        projectNameL = new JLabel();
        projectNameL.setText("Name");
        projectNameL.setHorizontalAlignment(SwingConstants.CENTER);
        projectNameL.setVerticalAlignment(SwingConstants.CENTER);
        projectNameL.setForeground(Window.text_c); // set text color
        projectNameL.setFont(new Font(Window.font, Font.BOLD, (int) (windowWidth*0.04)));
        int label_width = (int) (windowWidth/4);
        int label_height = windowHeight/20;
        projectNameL.setBounds(0, (int) (windowHeight*0.05), label_width, label_height);
        return projectNameL;
    }
    private Component projectNameTextField(){
        projectNameTf = new JTextField();
        projectNameTf.setBounds((int) (windowWidth/2.5),  (int) (windowHeight*0.05), windowWidth/2, windowHeight/20);
        projectNameTf.setFont(new Font(Window.font, Font.PLAIN, (int) (windowWidth*0.03)));
        projectNameTf.setHorizontalAlignment(SwingConstants.CENTER);
        projectNameTf.setBackground(Window.menu_item_c);
        projectNameTf.setBorder(BorderFactory.createLineBorder(Color.black));
        projectNameTf.setText("New Project");
        return projectNameTf;
    }
    private Component projectKey(){
        projectKey = new JLabel();
        projectKey.setText("Key");
        projectKey.setHorizontalAlignment(SwingConstants.CENTER);
        projectKey.setVerticalAlignment(SwingConstants.CENTER);
        projectKey.setForeground(Window.text_c); // set text color
        projectKey.setFont(new Font(Window.font, Font.BOLD, (int) (windowWidth*0.04)));
        int label_width = (int) (windowWidth/4);
        int label_height = windowHeight/20;
        projectKey.setBounds(0, (int) (windowHeight*0.05)*3, label_width, label_height);
        return projectKey;
    }
    private JComboBox projectKeyValBox(){
         keyValCb = new JComboBox();
         keyValCb.setBounds((int) (windowWidth/2.5), (int) (windowHeight*0.05)*3, windowWidth/4, windowHeight/20);
         keyValCb.setBackground(Window.menu_item_c);
         fillKeyValComboBox();
         keyValCb.setBorder(BorderFactory.createLineBorder(Color.black));
         return keyValCb;
    }
    private void fillKeyValComboBox(){
        populateKeyValMap();

        for(Map.Entry entry: keyValMap.entrySet()){
            Object Items = entry.getKey();
            keyValCb.addItem(Items);
        }
    }

    private JComboBox projectScaleBox(){
        keyScaleCb = new JComboBox<>();
        keyScaleCb.setBounds((int) (windowWidth/2.5)+windowWidth/4, (int) (windowHeight*0.05)*3, windowWidth/4, windowHeight/20);
        keyScaleCb.setBackground(Window.menu_item_c);
        fillScaleComboBox();
        keyScaleCb.setBorder(BorderFactory.createLineBorder(Color.black));
        return keyScaleCb;
    }

    private void fillScaleComboBox(){
        populateScaleMap();
        for(Map.Entry entry: scaleMap.entrySet()){
            Object Items = entry.getKey();
            keyScaleCb.addItem(Items);
        }
    }
    private JLabel projectTuning(){
        projectTuning = new JLabel();
        projectTuning.setText("Tuning");
        projectTuning.setHorizontalAlignment(SwingConstants.CENTER);
        projectTuning.setVerticalAlignment(SwingConstants.CENTER);
        projectTuning.setForeground(Window.text_c); // set text color
        projectTuning.setFont(new Font(Window.font, Font.BOLD, (int) (windowWidth*0.04)));
        int label_width = (int) (windowWidth/4);
        int label_height = windowHeight/20;
        projectTuning.setBounds(0, (int) (windowHeight*0.05)*5, label_width, label_height);
        return projectTuning;
    }
    private JComboBox projectTuningBox(){
        tuningCb = new JComboBox<>();
        tuningCb.setBounds((int) (windowWidth/2.5), (int) (windowHeight*0.05)*5, windowWidth/2, windowHeight/20);
        tuningCb.setBackground(Window.menu_item_c);
        fillTuningBox();
        tuningCb.setBorder(BorderFactory.createLineBorder(Color.black));
        return tuningCb;
    }
    private void fillTuningBox(){
        populateTuningMap();
        for(Map.Entry entry: tuningMap.entrySet()){
            Object Items = entry.getKey();
            tuningCb.addItem(Items);
        }
    }

    private JButton createProjectBtn(){
        int btn_w = windowWidth/3;
        int btn_h = windowHeight/10;
        createBtn.setBounds(windowWidth/2 - btn_w/2, (int) (windowHeight*0.05)*7, btn_w, btn_h);
        createBtn.setLayout(null);
        createBtn.setBackground(Window.button_off_c);
        JLabel label = new JLabel();
        label.setBounds(0,0, btn_w, btn_h);
        label.setText("Create");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        label.setFont(new Font(Window.font, Font.BOLD, (int) (btn_h*0.5)));
        createBtn.add(label);

        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t_id = tuningMap.get(tuningCb.getSelectedItem().toString());
                s_id = scaleMap.get(keyScaleCb.getSelectedItem().toString());
                key_val = keyValMap.get(keyValCb.getSelectedItem().toString());
                p_name = projectNameTf.getText();
                createProject();
                //System.out.println(key_val);
            }
        });
        return createBtn;
    }

    public static void main(String[] args) {
        new NewProjectWindow();
    }


}

