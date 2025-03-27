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
    private List<Integer> interval_values = new LinkedList<>();

    public Scale() {
    }

    public void createScale(int scale_id, int root_note_val){
        ConnectionSettings settings = new ConnectionSettings();
        try {
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
                this.interval_values.add(resultSet_intervals.getInt("i_val"));
            }
            //System.out.println(interval_values);
            for (int i = 0; i<7; i++){
                int note_val = root_note_val+this.interval_values.get(i);
                if (note_val>12) {note_val= note_val -12; scale_notes.add(note_val);}
                else scale_notes.add(note_val);
            }
            //System.out.println(scale_notes);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void applyScale(Fretboard fretboard) {
        for (int i1 = 0; i1<6; i1++) {
            for (int i2 = 0; i2 <fretboard.getFretNum(); i2++) {
                for(int i3 = 0; i3<7 ; i3++){
                    int n_val =  this.scale_notes.get(i3);
                    if (fretboard.getFretboard().get(i1).get(i2).getVal() == n_val){
                        fretboard.getFretboard().get(i1).get(i2).setIn_scale(true);
                        //System.out.println(fretboard.getFretboard().get(i1).get(i2));
                        break;

                    }
                }
            }
        }
    }
}
        /*for (int i1 = 0; i1<6; i1++) {
            System.out.print("String " + i1 + ", ");
            for (int i2 = 0; i2 <fretboard.getFretNum(); i2++) {
                System.out.print("Fret " + i2 + ", ");
                for(int i3 = 0; i3<7 ; i3++){
                    int n_val = this.interval_values.get(i1);
                    System.out.print("Note value " + fretboard.getFretboard().get(i1).get(i2).getVal() + "\n");
                    if (fretboard.getFretboard().get(i1).get(i2).getVal() == n_val){
                        fretboard.getFretboard().get(i1).get(i2).setIn_scale(true);
                        System.out.println(fretboard.getFretboard().get(i1).get(i2));
                        break;

                    }

                }

            }
        }*/
