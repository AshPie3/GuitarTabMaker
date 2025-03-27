package GuitarTabMaker.FretboardCreator;

import GuitarTabMaker.ConnectionSettings;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Fretboard {
    private int fretNum;

    private List<Note> tuning = new LinkedList<>();

    private List<List<Note>> fretboard = new LinkedList<>();

    public Fretboard(){}

    @Override
    public String toString() {
        return "Fretboard{" +
                "FretNum=" + fretNum +
                ", tuning=" + tuning +
                ", fretboard=" + fretboard +
                '}';
    }

    public List<Note> getTuning(){
        return tuning;
    }

    public void setFretNum(int fretNum) {this.fretNum = fretNum;}

    public void setTuning(List<Note> tuning) {this.tuning = tuning;}

    public void setFretboard(List<List<Note>> fretboard) {this.fretboard = fretboard;}

    public List<List<Note>> getFretboard(){
        return fretboard;
    }

    public int getFretNum() {return this.fretNum;}

    public void generateTuning(int t_id){
        ConnectionSettings settings = new ConnectionSettings();
            try {
                Connection conn  = settings.getDatabaseConnection();
                String sql = "SELECT t_s1_id,t_s2_id,t_s3_id,t_s4_id,t_s5_id,t_s6_id FROM tunings WHERE t_id = "+t_id;
                Statement statement_notes_id = conn.createStatement();
                ResultSet resultSet_notes_id = statement_notes_id.executeQuery(sql);
                resultSet_notes_id.next();
                for (int i = 1; i<=6; i++){
                    int string_id = resultSet_notes_id.getInt(i);
                    sql = "SELECT n_name, n_val, n_oct, n_audio FROM notes WHERE n_id = "+string_id;
                    Statement statement_notes = conn.createStatement();
                    ResultSet resultSet_notes = statement_notes.executeQuery(sql);
                    resultSet_notes.next();
                    Note note = new Note(string_id ,resultSet_notes.getString("n_name"), resultSet_notes.getInt("n_val"), resultSet_notes.getInt("n_oct"), resultSet_notes.getString("n_audio"), false );
                    statement_notes.close();
                    this.tuning.add(note);
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
        }
    }

    public void generateFretboard(int fretNum){
        ConnectionSettings settings = new ConnectionSettings();
        this.fretNum = fretNum;
        try{
            Connection conn  = settings.getDatabaseConnection();
            String sql;
            Statement statement_notes = conn.createStatement();
            for(int n = 0; n<6; n++) {
                List<Note> temp_string_notes = new LinkedList<>();
                int start_note_id = this.tuning.get(n).getId();
                for (int i = 0; i < fretNum; i++) {
                    int n_id = start_note_id + i;
                    sql = "SELECT n_name, n_val, n_oct, n_audio FROM notes WHERE n_id = " + n_id;
                    ResultSet resultSet_notes = statement_notes.executeQuery(sql);
                    resultSet_notes.next();
                    Note note = new Note(n_id, resultSet_notes.getString("n_name"), resultSet_notes.getInt("n_val"), resultSet_notes.getInt("n_oct"), resultSet_notes.getString("n_audio"), false);
                    temp_string_notes.add(note);
                }
                this.fretboard.add(n, temp_string_notes);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
