package GuitarTabMaker;

public class Main {
    public static void main(String[] args) {
        Fretboard fretboard1 = new Fretboard();
        fretboard1.generateTuning(1);
        // getter
        System.out.println(fretboard1.getTuning());
        // Setter
        fretboard1.generateFretboard(16);
        for (int i = 0; i<6; i++){
            System.out.println("String "+ (i+1) +": "+ fretboard1.getFretboard().get(i));
        }
        Scale scale1 = new Scale();
        scale1.createScale(1, 1);
        //System.out.println(scale1.);


    }
}