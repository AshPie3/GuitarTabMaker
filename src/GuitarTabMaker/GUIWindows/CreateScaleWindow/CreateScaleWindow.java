package GuitarTabMaker.GUIWindows.CreateScaleWindow;

import GuitarTabMaker.ConnectionSettings;
import GuitarTabMaker.GUIWindows.StartWindow.StartWindow;
import GuitarTabMaker.GUIWindows.Window;

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

public class CreateScaleWindow {
    private int windowHeight = (int) (GuitarTabMaker.GUIWindows.Window.screenSize.getHeight()*0.7);
    private int windowWidth = windowHeight;//(int) (GuitarTabMaker.GUIWindows.Window.screenSize.getWidth()*0.5);
    private JFrame frame = new JFrame();
    private int id;
    private String name;
    private int s_i1;
    private int s_i2;
    private int s_i3;
    private int s_i4;
    private int s_i5;
    private int s_i6;
    private int s_i7;
    private HashMap<String, Integer> notesMap = new HashMap<>();
    private JLabel scaleNameL;
    private JTextField scaleNameTf;
    private JComboBox intervalsCb;
    private JLabel scaleIntervalOne;

    public CreateScaleWindow(){
        frame.setTitle("Create Scale");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setSize(windowWidth, windowHeight);
        frame.setResizable(false);
        ImageIcon icon = new ImageIcon("Assets/Icon.png");
        frame.setIconImage(icon.getImage());
        frame.getContentPane().setBackground(GuitarTabMaker.GUIWindows.Window.background_c);
        frame.setLayout(null);

        // Add components
        frame.add(exitBtn());
        frame.add(scaleName());
        frame.add(scaleNameTextField());
        frame.add(firstIntervalLabel());
        frame.add(scaleIntervalValBox());


        frame.setVisible(true);
    }

    private Component exitBtn(){
        JButton button = new JButton();
        button.setLayout(null);
        int button_width = (int) (windowWidth * 0.1);
        int button_height = (int) (windowHeight*0.045);
        int y = 0;
        button.setBounds(0, y, button_width, button_height);
        button.setBackground(GuitarTabMaker.GUIWindows.Window.button_off_c);
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

    private Component scaleName(){
        scaleNameL = new JLabel();
        scaleNameL.setText("Name");
        scaleNameL.setHorizontalAlignment(SwingConstants.CENTER);
        scaleNameL.setVerticalAlignment(SwingConstants.CENTER);
        scaleNameL.setForeground(Window.text_c); // set text color
        scaleNameL.setFont(new Font(Window.font, Font.BOLD, (int) (windowWidth*0.03)));
        scaleNameL.setHorizontalTextPosition(0);
        int label_width = (int) (windowWidth/5);
        int label_height = windowHeight/20;
        scaleNameL.setBounds(0, (int) (windowHeight*0.06), label_width, label_height);
        return scaleNameL;
    }
    private Component scaleNameTextField(){
        scaleNameTf = new JTextField();
        scaleNameTf.setBounds((int) (windowWidth/4),  (int) (windowHeight*0.06), windowWidth/2, windowHeight/20);
        scaleNameTf.setFont(new Font(Window.font, Font.PLAIN, (int) (windowWidth*0.04)));
        scaleNameTf.setHorizontalAlignment(SwingConstants.CENTER);
        scaleNameTf.setBackground(Window.menu_item_c);
        scaleNameTf.setBorder(BorderFactory.createLineBorder(Color.black));
        return scaleNameTf;
    }

    private void populateIntervalMap(){
        ConnectionSettings settings = new ConnectionSettings();
        try {
            Connection conn = settings.getDatabaseConnection();
            String sql = "SELECT i_id, i_name FROM intervals";
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()){
                notesMap.put(resultSet.getString("i_name"), resultSet.getInt("i_id"));
            }
        } catch (SQLException e){
            throw new RuntimeException();
        }
    }

    private Component firstIntervalLabel(){
        scaleIntervalOne = new JLabel();
        scaleIntervalOne.setText("First Interval");
        scaleIntervalOne.setHorizontalAlignment(SwingConstants.CENTER);
        scaleIntervalOne.setVerticalAlignment(SwingConstants.CENTER);
        scaleIntervalOne.setForeground(Window.text_c); // set text color
        scaleIntervalOne.setFont(new Font(Window.font, Font.BOLD, (int) (windowWidth*0.03)));
        scaleIntervalOne.setHorizontalTextPosition(0);
        int label_width = (int) (windowWidth/3);
        int label_height = windowHeight/20;
        scaleIntervalOne.setBounds(0, (int) (windowHeight*0.05)*3, label_width, label_height);
        return scaleIntervalOne;
    }

    private JComboBox scaleIntervalValBox(){
        intervalsCb = new JComboBox();
        intervalsCb.setBounds((int) (windowWidth/2.5), (int) (windowHeight*0.05)*3, windowWidth/4, windowHeight/20);
        intervalsCb.setBackground(Window.menu_item_c);
        fillScaleNotesComboBox();
        intervalsCb.setBorder(BorderFactory.createLineBorder(Color.black));
        return intervalsCb;
    }
    private void fillScaleNotesComboBox(){
        populateIntervalMap();

        for(Map.Entry entry: notesMap.entrySet()){
            Object Items = entry.getKey();
            intervalsCb.addItem(Items);
        }
    }

}
