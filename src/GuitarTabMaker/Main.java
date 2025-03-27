package GuitarTabMaker;

public class Main {
    public static void main(String[] args) {
        Fretboard fretboard1 = new Fretboard();
        fretboard1.generateTuning(1);
        // getter
        System.out.println(fretboard1.getTuning());
        // Setter
        fretboard1.generateFretboard(16);

        Scale scale1 = new Scale();
        scale1.createScale(1, 4);
        scale1.applyScale(fretboard1);
        for (int i = 0; i<6; i++){
            System.out.println("String "+ (i+1) +": "+ fretboard1.getFretboard().get(i));
        }
        //System.out.println(scale1.);


    }
}