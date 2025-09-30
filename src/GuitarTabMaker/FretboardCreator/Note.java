package GuitarTabMaker.FretboardCreator;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Note {
    private int id;
    private String name;
    private int val;
    private int oct;
    private boolean in_scale;

    public Note(int id, String name, int val, int oct, boolean in_scale) {
        this.id = id;
        this.name = name;
        this.val = val;
        this.oct = oct;
        this.in_scale = in_scale;
    }
    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", val=" + val +
                ", oct=" + oct +
                ", in_scale=" + in_scale + '\'' +
                '}';
    }

    public int getId() {return id;}

    public String getName() {return name;}

    public int getVal() {return val;}

    public boolean getIn_scale() {return in_scale;}
    public void setIn_scale(boolean in_scale) {this.in_scale = in_scale;}
}
