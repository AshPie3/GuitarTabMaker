package GuitarTabMaker.GUIWindows.NewProjectWindow;

        import GuitarTabMaker.ConnectionSettings;
        import GuitarTabMaker.GUIWindows.Window;

        import javax.swing.*;
        import java.awt.*;
        import java.sql.Connection;
        import java.sql.PreparedStatement;
        import java.sql.SQLException;

public class NewProjectWindow {

    private int windowHeight = (int) (GuitarTabMaker.GUIWindows.Window.screenSize.getHeight()*0.7);
    private int windowWidth = windowHeight;//(int) (GuitarTabMaker.GUIWindows.Window.screenSize.getWidth()*0.5);
    private JFrame frame = new JFrame();
    private JLabel projectNameL;
    private JTextField projectNameTf;
    private JLabel projectKey;
    private JComboBox keyValCb;
    private JCheckBox keyScaleCb;
    private JLabel projectTunning;
    private int p_id;
    private int t_id;
    private int s_id;
    private int key_val;


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


        frame.setVisible(true);
    }
    private void DumpDataToDatabase() {
        ConnectionSettings settings = new ConnectionSettings();
        try {
            Connection conn = settings.getDatabaseConnection();
            PreparedStatement statement = conn.prepareStatement("INSERT INTO `projects` SET `p_cvs_val`= ? WHERE `p_id` = ?");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


        // Create Center Label
    private Component projectName(){
        JLabel label = new JLabel();
        label.setText("Project Name");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setForeground(Window.text_c); // set text color
        label.setFont(new Font(Window.font, Font.PLAIN, (int) (windowHeight*0.06)));
        int label_width = windowWidth;
        int label_height = windowHeight/10;
        label.setBounds(windowWidth/2-label_width/2, 0, label_width, label_height);
        return label;
    }
    private Component projectNameTextField(){
        projectNameTf.setBounds(0,  windowHeight/10, windowWidth, windowHeight/10);

        return projectNameTf;
    }
    private JComboBox projectBtnBox(){
         keyValCb = new JComboBox();


         return keyValCb;
    }









    public static void main(String[] args) {
        new NewProjectWindow();
    }


}

