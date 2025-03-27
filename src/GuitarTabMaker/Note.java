package GuitarTabMaker;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class Note {
    private int id;
    private String name;
    private int val;
    private int oct;
    private String audio_file;
    private boolean in_scale;

    public Note(int id, String name, int val, int oct, String audio_file, boolean in_scale) {
        this.id = id;
        this.name = name;
        this.val = val;
        this.oct = oct;
        this.audio_file = audio_file;
        this.in_scale = in_scale;
    }

    @Override
    public String toString() {
        return "Notes{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", val=" + val +
                ", oct=" + oct +
                ", audio_file='" + audio_file + '\'' +
                ", in_scale=" + in_scale + '\'' +
                '}';
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getVal() {return val;}

    public void setVal(int val) {this.val = val;}

    public int getOct() {return oct;}

    public void setOct(int oct) {this.oct = oct;}

    public String getAudio_file() {return audio_file;}

    public void setAudio_file(String audio_file) {this.audio_file = audio_file;}

    public boolean getIn_scale() {return in_scale;}

    public void setIn_scale(boolean in_scale) {this.in_scale = in_scale;}
}
