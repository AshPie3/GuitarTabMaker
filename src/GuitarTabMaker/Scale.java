package GuitarTabMaker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class Scale {
    private String scale_name;
    private List<List<Note>> fretboard = new LinkedList<>();
    private List<Integer> scale_notes = new LinkedList<>();
    private int scale_id;

    public Scale() {
    }

    public void createScale(int scale_id, int root_note_val){
        ConnectionSettings settings = new ConnectionSettings();
        List<Integer> interval_values = new LinkedList<>();
        try {
            /* Add root note

            String sql = "SELECT n_name, n_val, n_oct, n_audio FROM notes WHERE n_id = "+root_note_id;
            Statement statement_scale_notes = conn.createStatement();
            ResultSet resultSet_scale_notes = statement_scale_notes.executeQuery(sql);
            resultSet_scale_notes.next();
            //Note note = new Note(root_note_id ,resultSet_scale_notes.getString("n_name"), resultSet_scale_notes.getInt("n_val"), resultSet_scale_notes.getInt("n_oct"), resultSet_scale_notes.getString("n_audio"), true );

            //this.scale_notes.add(resultSet_scale_notes.getInt("n_val"));*/
            Connection conn  = settings.getDatabaseConnection();
            String sql = "SELECT s_i1, s_i2, s_i3, s_i4, s_i5, s_i6, s_i7 FROM scale WHERE s_id = "+scale_id;
            Statement statement_scale_intervals = conn.createStatement();
            ResultSet resultSet_scale_intervals = statement_scale_intervals.executeQuery(sql);
            resultSet_scale_intervals.next();
            for(int i =1; i <=7; i++){
                sql = "SELECT i_val FROM intervals WHERE i_id = "+resultSet_scale_intervals.getInt(i);
                statement_scale_intervals = conn.createStatement();
                ResultSet resultSet_intervals = statement_scale_intervals.executeQuery(sql);
                resultSet_intervals.next();
                interval_values.add(resultSet_intervals.getInt("i_val"));
            }
            System.out.println(interval_values);


            for (int i = 0; i<7; i++){
                int note_val = interval_values.get(i);
                //sql = "SELECT i_val FROM notes WHERE i_id = "+ note_id ;
                Statement statement_note = conn.createStatement();
                ResultSet resultSet_note = statement_note.executeQuery(sql);
                resultSet_note.next();
                //note = new Note(root_note_id ,resultSet_scale_notes.getString("n_name"), resultSet_scale_notes.getInt("n_val"), resultSet_scale_notes.getInt("n_oct"), resultSet_scale_notes.getString("n_audio"), true );
                //this.scale_notes.add(note);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public void applyScale(Scale scale, List<List<Note>> fretboard) {
        for (int y = 0; y<=7; y++) {
            //int n_id = this.scale_notes.get(y).getVal();
            for (int i = 0; i < 6; i++) {

            }
        }

    }

}