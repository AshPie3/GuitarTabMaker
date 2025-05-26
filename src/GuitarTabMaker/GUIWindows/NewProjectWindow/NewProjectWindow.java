package GuitarTabMaker.GUIWindows.NewProjectWindow;

        import GuitarTabMaker.ConnectionSettings;
        import GuitarTabMaker.GUIWindows.ProjectWindow.ProjectWindow;
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
    private HashMap<Integer, String> keyValMap = new HashMap();
    private JComboBox keyScaleCb;
    private HashMap<Integer, String> scaleMap = new HashMap<>();
    private JLabel projectTunning;
    private JComboBox tunningCb;
    private HashMap<Integer, String> tuningMap = new HashMap<>();
    private JButton createBtn = new JButton();
    private int p_id;
    private int t_id;
    private int s_id;
    private int key_val;
    private String p_name;
    private void populateKeyValMap(){
        keyValMap.put(1, "A");
        keyValMap.put(2, "A#");
        keyValMap.put(3, "B");
        keyValMap.put(4, "C");
        keyValMap.put(5, "C#");
        keyValMap.put(6, "D");
        keyValMap.put(7, "D#");
        keyValMap.put(8, "E");
        keyValMap.put(9, "F");
        keyValMap.put(10, "F#");
        keyValMap.put(11, "G");
        keyValMap.put(12, "G#");
    }

    private void populateScaleMap() {
        ConnectionSettings settings = new ConnectionSettings();
        try {
            Connection conn = settings.getDatabaseConnection();
            String sql = "SELECT s_id, s_name FROM scale";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                scaleMap.put(resultSet.getInt("s_id"), resultSet.getString("s_name"));
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
                tuningMap.put(resultSet.getInt("t_id"), resultSet.getString("t_name"));
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }


    public NewProjectWindow(){
        // create Window
        frame.setTitle("Create Project Window");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);
        frame.setResizable(false);
        ImageIcon icon = new ImageIcon("Assets/Icon.png");
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(GuitarTabMaker.GUIWindows.Window.background_c);
        frame.setLayout(null);

        // Add components
        frame.add(projectName());
        frame.add(projectNameTextField());
        frame.add(projectKey());
        frame.add(projectKeyValBox());
        frame.add(projectScaleBox());
        frame.add(projectTunning());
        frame.add(projectTuningBox());
        frame.add(createProjectBtn());

        frame.setVisible(true);
    }
    private void createProject() {
        Project project = new Project(this.t_id, this.s_id, this.key_val, this.p_name);
        new ProjectWindow(project);
        frame.dispose();

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
        projectNameTf.setFont(new Font(Window.font, Font.PLAIN, (int) (windowWidth*0.04)));
        projectNameTf.setHorizontalAlignment(SwingConstants.CENTER);
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
         fillKeyValComboBox();
         return keyValCb;
    }
    private void fillKeyValComboBox(){
        populateKeyValMap();

        for(Map.Entry entry: keyValMap.entrySet()){
            Object Items = entry.getValue();
            keyValCb.addItem(Items);
        }
    }

    private JComboBox projectScaleBox(){
        keyScaleCb = new JComboBox<>();
        keyScaleCb.setBounds((int) (windowWidth/2.5)+windowWidth/4, (int) (windowHeight*0.05)*3, windowWidth/4, windowHeight/20);
        fillScaleComboBox();
        return keyScaleCb;
    }

    private void fillScaleComboBox(){
        populateScaleMap();
        for(Map.Entry entry: scaleMap.entrySet()){
            Object Items = entry.getValue();
            keyScaleCb.addItem(Items);
        }
    }
    private JLabel projectTunning(){
        projectTunning = new JLabel();
        projectTunning.setText("Tuning");
        projectTunning.setHorizontalAlignment(SwingConstants.CENTER);
        projectTunning.setVerticalAlignment(SwingConstants.CENTER);
        projectTunning.setForeground(Window.text_c); // set text color
        projectTunning.setFont(new Font(Window.font, Font.BOLD, (int) (windowWidth*0.04)));
        int label_width = (int) (windowWidth/4);
        int label_height = windowHeight/20;
        projectTunning.setBounds(0, (int) (windowHeight*0.05)*5, label_width, label_height);
        return projectTunning;
    }
    private JComboBox projectTuningBox(){
        tunningCb = new JComboBox<>();
        tunningCb.setBounds((int) (windowWidth/2.5), (int) (windowHeight*0.05)*5, windowWidth/2, windowHeight/20);
        fillTuningBox();
        return tunningCb;
    }
    private void fillTuningBox(){
        populateTuningMap();
        for(Map.Entry entry: tuningMap.entrySet()){
            Object Items = entry.getValue();
            tunningCb.addItem(Items);
        }
    }
    private JButton createProjectBtn(){
        int btn_w = windowWidth/3;
        int btn_h = windowHeight/10;
        createBtn.setBounds(windowWidth/2 - btn_w, (int) (windowHeight*0.05)*7, btn_w, btn_h);
        createBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < keyValMap.size(); i ++){
                    //if(keyValMap.get())
                }
                p_name = projectNameTf.getText();
                //key_val = keyValMap.get(keyValCb.getSelectedItem().toString());
                System.out.println(key_val);
            }
        });
        return createBtn;
    }









    public static void main(String[] args) {
        new NewProjectWindow();
    }


}

