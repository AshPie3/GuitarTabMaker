package GuitarTabMaker.FretboardCreator;

public class Main {
    public static void main(String[] args) {
        // Create new fretboard
        Fretboard fretboard1 = new Fretboard();
        fretboard1.generateTuning(1);
        // Print out the tuning
        System.out.print("Tuning: ");
        for (int y = 0; y<6; y++){
            System.out.print(fretboard1.getTuning().get(y).getName()+ ", ");
        }
        System.out.println();

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