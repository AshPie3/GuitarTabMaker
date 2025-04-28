package GuitarTabMaker;

import GuitarTabMaker.FretboardCreator.Fretboard;
import GuitarTabMaker.FretboardCreator.Scale;

import java.util.Scanner;

public class Project {
    private Fretboard fretboard = new Fretboard();
    private int t_id;
    private Scale scale = new Scale();

    public Project(Fretboard fretboard, Scale scale, int t_id){
        this.fretboard = fretboard;
        this.scale = scale;
        this.t_id = t_id;

        fretboard.generateTuning(t_id);
        fretboard.generateFretboard();
        scale.applyScale(fretboard);
    }

    public Fretboard getFretboard() {return fretboard;}

    public int getT_id() {return t_id;}
    public void setT_id(int t_id) {this.t_id = t_id;}

    public Scale getScale() {return scale;}
    public void setScale(Scale scale) {this.scale = scale;}
}
