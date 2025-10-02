package GuitarTabMaker.ProjectManager;

import GuitarTabMaker.ConnectionSettings;
import GuitarTabMaker.FretboardCreator.Fretboard;
import GuitarTabMaker.FretboardCreator.Scale;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class Project {
    private Fretboard fretboard = new Fretboard();
    private int p_id;
    private String cvs_value;
    private int t_id;
    private int s_id;
    private int key_val;
    private String p_name;
    private Scale scale = new Scale();
    private List<List<String>> tab = new LinkedList<>();

    public Project(int p_id){
        this.p_id = p_id;
        retriveDatabaseData();
    } // Acces project constructor
    public Project(int t_id, int s_id, int key_val, String p_name){
        this.t_id = t_id;
        this.s_id = s_id;
        this.key_val = key_val;
        this.p_name= p_name;
        this.fretboard.generateTuning(t_id);
        this.fretboard.generateFretboard();
        this.scale = new Scale();
        this.scale.createScale(s_id, key_val);
        this.scale.applyScale(this.fretboard);
        initilizeTab();
        ConnectionSettings settings = new ConnectionSettings();
        try {
            Connection conn = settings.getDatabaseConnection();
            String sql = "INSERT INTO `guitartab`.`projects` (`p_t_id`, `p_s_id`, `p_key_val`, `p_name`) VALUES ("+ t_id +", "+s_id+", "+key_val+", '"+p_name+"')";
            Statement statement_project = conn.createStatement();
            statement_project.executeQuery(sql);
            statement_project.close();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    } // New Project constructor
    private void retriveDatabaseData(){
        ConnectionSettings settings = new ConnectionSettings();
        try {
            Connection conn = settings.getDatabaseConnection();
            String sql = "SELECT p_t_id, p_s_id, p_key_val, p_name, p_cvs_val FROM projects WHERE p_id = " + p_id;
            Statement statement_project = conn.createStatement();
            ResultSet resultSet_project = statement_project.executeQuery(sql);
            resultSet_project.next();
            //System.out.println(resultSet_project.toString());
            //System.out.println();
            this.t_id = resultSet_project.getInt("p_t_id");
            this.fretboard.generateTuning(t_id);
            this.fretboard.generateFretboard();
            this.s_id = resultSet_project.getInt(2);
            this.key_val = resultSet_project.getInt(3);
            this.scale = new Scale();
            this.scale.createScale(s_id, key_val);
            this.scale.applyScale(this.fretboard);
            this.p_name = resultSet_project.getString(4);
            if (resultSet_project.getString(5) == null){
                initilizeTab();
            } else {
                this.cvs_value = resultSet_project.getString(5);
                retriveTab();
            }

            statement_project.close();
            resultSet_project.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void retriveTab(){
        List<String> values = Arrays.asList(cvs_value.split(","));
        int i = 0;
        for (int x =0; x< (values.size())/7; x++){
            List<String> temp_list = new ArrayList<>();
            for (int y = 0; y<7; y++){
                int index = y+i;
                if(values.get(index) == "|"){values.set(index, "|"); } //System.out.println("Changed");
                temp_list.add(values.get(index));
            }
            tab.add(temp_list);
            i = i +7;
        }
        //System.out.println(tab);
    }

    public void initilizeTab(){
        if (tab.isEmpty()) {
            for (int i = 0; i < 3; i++) {
                tab.add(i, new ArrayList<>(7));
            }
            for (int i = 0; i < 6; i++) {
                if (fretboard.getTuning().get(i).getName().length()-1 == 1){
                    tab.get(0).add(i, fretboard.getTuning().get(i).getName().substring(0, fretboard.getTuning().get(i).getName().length()-1) + " ");
                }else  tab.get(0).add(i, fretboard.getTuning().get(i).getName().substring(0, fretboard.getTuning().get(i).getName().length()-1));
            }
            tab.get(0).add(6, "  ");
            for (int i = 0; i < 6; i++) {
                tab.get(1).add(i, "|");
            }
            tab.get(1).add(6, " ");
            for (int i = 0; i < 6; i++) {
                tab.get(2).add(i, "--");
            }
            tab.get(2).add(6, "^^");
        } else{


        }
    }

    public Fretboard getFretboard() {return fretboard;}

    public int getT_id() {return t_id;}
    public void setT_id(int t_id) {this.t_id = t_id;}

    public Scale getScale() {return scale;}
    public void setScale(Scale scale) {this.scale = scale;}

    public List<List<String>> getTab() {return tab;}
    public void setTab(List<List<String>> tab) {this.tab = tab;}

    public int getP_id() {return p_id;}
    public void setP_id(int p_id) {this.p_id = p_id;}

    public String getCvs_value() {return cvs_value;}
    public void setCvs_value(String cvs_value) {this.cvs_value = cvs_value;}

    public int getS_id() {return s_id;}
    public void setS_id(int s_id) {this.s_id = s_id;}

    public int getKey_val() {return key_val;}
    public void setKey_val(int key_val) {this.key_val = key_val;}

    public String getP_name() {return p_name;}
    public void setP_name(String p_name) {this.p_name = p_name;}

}
